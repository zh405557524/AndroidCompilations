//
// Created by andyzhao on 2019/8/25.
//

#ifndef CARASSISTANT_MSG_QUEUE_H
#define CARASSISTANT_MSG_QUEUE_H

#include <list>
#include <thread>
#include <mutex>
#include <condition_variable>

#include "autoai_log.h"

#include "msg_queue.h"

using namespace std;

template<typename T>
class MsgLooper;

template<typename T>
class MsgDispatcher {
public:
    virtual  ~MsgDispatcher() {};

    virtual bool dealMsg(T *msg) = 0;

    /*
 * 1、优先级高的放置在前面;
 * 2、优先级一样的，前入队列的，需要放在前面;
     * return
     * 0,
     * <0,
     * >0
 */

    virtual int compareMsg(T* it, T* insertedMsg){
        return 0;
    }

    virtual  bool  isReady(const MsgLooper<T>&){
        return  true;
    }

    virtual  bool equal(T* it, T* rmMsg){
        return  it == rmMsg;
    }
};

template<typename T>
class MsgLooper {
public:
    MsgLooper();

    MsgLooper(MsgDispatcher<T> *);

    ~MsgLooper();

private:
    void run();

    void init(MsgDispatcher<T> *dispatcher);

    void destroy();

protected:
    virtual bool prefixCallMsgDispatcher(T *msg);

    virtual bool suffixCallMsgDispatcher(T *msg, bool bHandled);

    /*
     * 1、优先级高放到前面；
     * 2、优先级一样的，isTop=true时，msg插入到相同优先级的消息最前面
     */
    virtual bool addMsgWithPriority(T *msg, bool isTop);

    bool addMsg(T *msg, bool isTop);

public:
    /***可以重载的接口*/

    virtual bool isReady();

    /***可以重载的接口*/

    bool sendMsg(T *msg);

    /*
 * 1、优先级高放到前面；
 * 2、优先级一样的，isTop=true时，msg插入到相同优先级的消息最前面
 */
    bool sendMsg(T *msg, bool);


    void notify();

    void removeMsg(T* msg);

protected:
    typedef   list<T*> MsgQueueData;

    MsgDispatcher<T> *getDispatcher() {
        return m_dispatcher;
    }

    MsgQueueData&  getMsgQueue() {
        return m_msg_queue;
    }

private:
    thread *m_ptr_msg_thread;
    list<T *> m_msg_queue;
    mutex m_mutex;
    condition_variable m_condVar;

    MsgDispatcher<T> *m_dispatcher;
    bool m_exited;
};


/*非C++ 11和pthread结合的用法
void* _msg_proc(void* arg)
{
    SocketClient* client = (SocketClient*)arg;
    client->runMsg();
}
类的方法()
 {
   ....
  pthread_t  p;
 pthread_create(&p, NULL, _msg_proc, this);
 ....
 }

 */
template<typename T>
MsgLooper<T>::MsgLooper() {
    init(nullptr);
}

template<typename T>
MsgLooper<T>::MsgLooper(MsgDispatcher<T> *dispatcher) {
    init(dispatcher);
}

template<typename T>
MsgLooper<T>::~MsgLooper() {
    destroy();
}

template<typename T>
void MsgLooper<T>::init(MsgDispatcher<T> *dispatcher) {
    m_exited = false;

    m_dispatcher = dispatcher;

    m_ptr_msg_thread = new thread(&MsgLooper<T>::run, this);//析构方法中, 必须销毁
    m_ptr_msg_thread->detach();
}

template<typename T>
void MsgLooper<T>::destroy() {
    if (nullptr != m_ptr_msg_thread) {

        /****唤醒线程，如果线程正在等待新的任务***/
        {
            std::unique_lock<std::mutex> lock(m_mutex);
            m_exited = true;
            m_condVar.notify_all();
        }

        //等待线程退出
        m_ptr_msg_thread->join();

        /****释放队列中的消息资源***/
        {
            std::unique_lock<std::mutex> lock(m_mutex);
            //list<T*>::iterator it = m_msg_queue.begin();
            auto it = m_msg_queue.begin();

            for (; m_msg_queue.end() != it; it++) {
                T *msg = *it;
                if (nullptr != msg) {
                    delete msg;
                }
            }
        }

        delete m_ptr_msg_thread;
    }
}

template<typename T>
void MsgLooper<T>::run() {
    while (true) {
        T *msg;
        {
            /*****操作消息队列的地方需要锁起来,但是需要用{}缩小锁定区域*****/

            std::unique_lock<std::mutex> lock(m_mutex);
            while ((m_msg_queue.empty() && !m_exited) || (!isReady() && !m_exited)) {
                cv_status ret = m_condVar.wait_for(lock, std::chrono::milliseconds(1000));
//                LOGD("wait_for ret : %d", cv_status::timeout == ret ? 0 : 1 );
            }

            //退出线程
            if (m_exited) {
                break;
            }

            msg = m_msg_queue.front();
            m_msg_queue.pop_front();

            /*****操作消息队列的地方需要锁起来,但是需要用{}缩小锁定区域*****/

        }

        if (nullptr == msg) {
            break;
        }

        bool  bHandled = false;
        //处理消息
        if (nullptr != m_dispatcher)
        {
            bHandled = m_dispatcher->dealMsg(msg);//监听器用法
        }

        suffixCallMsgDispatcher(msg, bHandled);
    }

    LOGD("queue thread end");
}

template <typename  T>
bool MsgLooper<T>::isReady() {
    return true;
}

template <typename  T>
void MsgLooper<T>::notify() {
    {
        std::unique_lock<std::mutex> lock(m_mutex);
        //唤醒线程
        m_condVar.notify_all();
    }
}

template <typename  T>
bool MsgLooper<T>::prefixCallMsgDispatcher(T *msg) {
    return true;
}

template <typename  T>
bool MsgLooper<T>::suffixCallMsgDispatcher(T *msg, bool  bHandled) {
    //销毁消息，释放资源
    delete msg;
    return true;
}

template <typename  T>
bool MsgLooper<T>::addMsgWithPriority(T* msg, bool isTop){
    return false;
}

template <typename  T>
bool MsgLooper<T>::addMsg(T* msg, bool isTop){
    {
        std::unique_lock<std::mutex> lock(m_mutex);
        //退出
        if (m_exited) {
            return false;
        }

        do
        {
            /******自定义添加方式,为优先级队列预留接口****/
            bool  ret = addMsgWithPriority(msg, isTop);
            if (ret)
            {
                break;
            }
            /******自定义添加方式,为优先级队列预留接口****/
            if (isTop)
            {
                m_msg_queue.push_front(msg);//添加到队头
                break;
            }

            m_msg_queue.push_back(msg);//添加到队尾

        }while(false);

        //唤醒线程
        m_condVar.notify_all();
    }

    return true;
}

template<typename T>
void MsgLooper<T>::removeMsg(T *msg)
{
    std::unique_lock<std::mutex> lock(m_mutex);

    list<T*>& msgl_list = MsgLooper<T>::getMsgQueue();
    auto it = msgl_list.begin();
    for (; it != msgl_list.end();)
    {
        T* tmpMsg = *it;
        MsgDispatcher<T> * msgDispatcher = MsgLooper<T>::getDispatcher();

        bool  ret = false;

        do
        {
            //地址一样一定是相同的元素
            if (tmpMsg == msg)
            {
                ret = true;
                break;
            }

            if (msgDispatcher == nullptr)
            {
                break;
            }

            ret = msgDispatcher->equal(tmpMsg, msg);
        }while(false);

        //相同元素则移除
        if (ret)
        {
            it = msgl_list.erase(it);
            delete tmpMsg;//释放内存
            continue;
        }

        ++it;
    }

}


template<typename T>
bool MsgLooper<T>::sendMsg(T *msg) {
    return sendMsg(msg, false);
}

template<typename T>
bool MsgLooper<T>::sendMsg(T *msg, bool isTop) {
    return addMsg(msg, isTop);
}

#endif //CARASSISTANT_MSG_QUEUE_H
