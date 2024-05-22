//
// Created by andyzhao on 2019/9/19.
//

#ifndef CARASSISTANT_JNI_HELPER_CLASS_H
#define CARASSISTANT_JNI_HELPER_CLASS_H

#include <jni.h>

#include "autoai_log.h"


class JNIJavaVM
{
public:
    static  void setJVM(JavaVM* jvm)
    {
        s_jvm = jvm;
    }

    static JavaVM* getJVM()
    {
        return  s_jvm;
    }

private:
    static  JavaVM* s_jvm;
};

class JNIString {
public:
    JNIString(JNIEnv* env, jstring strString):m_jstring(strString), m_ptrString(env->GetStringUTFChars(strString, NULL))
    {
        m_env = env;
    }

    virtual  ~JNIString()
    {
        if (nullptr != m_env && nullptr != m_ptrString)
        {
            m_env->ReleaseStringUTFChars(m_jstring, m_ptrString);
            LOGD("%s", m_ptrString);
        }

    }
    //返回jstring对应的const char*类型数据
    const  char*  c_str()
    {
        return m_ptrString;
    }


private:
    JNIEnv *m_env;
    const char* m_ptrString;
    jstring m_jstring;
};

class JNIExtEnv {
public:
    JNIExtEnv()
    {
        m_attached = false;

        m_jvm = JNIJavaVM::getJVM();

        m_env = NULL;
        if (m_jvm->GetEnv((void**)&m_env, JNI_VERSION_1_6) != JNI_OK) {
            m_jvm->AttachCurrentThread(&m_env, nullptr);
            m_attached = true;
        }

    }

    virtual  ~JNIExtEnv() {
        if (m_attached)
        {
            m_jvm->DetachCurrentThread();
        }
    }

    JNIEnv* operator -> () const
    {
        return m_env;
    }

private:
    JNIEnv *m_env;
    JavaVM* m_jvm;
    bool m_attached;
};

class JNIExtByteArray
{
public:
    JNIExtByteArray(JNIEnv* env, jbyteArray  byteArray):m_size(0), m_ptrByteArray(nullptr)
    {
        m_env = env;
        m_jbyteArray = byteArray;
        if (nullptr != m_jbyteArray)
        {
            m_ptrByteArray = (char*)m_env->GetByteArrayElements(m_jbyteArray, nullptr);
            m_size = m_env->GetArrayLength(m_jbyteArray);
        }
    }

    ~JNIExtByteArray()
    {
        if (nullptr != m_jbyteArray)
        {
            m_env->ReleaseByteArrayElements(m_jbyteArray, (jbyte *) m_ptrByteArray, 0);
        }
    }

    const  char * data (){
        return m_ptrByteArray;
    }

    int size(){
        return m_size;
    }

private:
    JNIEnv* m_env;
    jbyteArray  m_jbyteArray;
    const char* m_ptrByteArray;
    int m_size;
};

#endif //CARASSISTANT_JNI_HELPER_CLASS_H
