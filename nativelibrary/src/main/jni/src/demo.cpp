//
// Created by soul on 2020/2/19.
//

#include <jni.h>
#include <string>
#include <server.h>
#include <client.h>
#include <sys/socket.h>
#include <autoai_log.h>
#include <unistd.h>


extern "C" {
JNIEXPORT jstring JNICALL
Java_com_soul_lib_jni_JniTest_testDemo_1c(JNIEnv* env, jclass clazz);


JNIEXPORT jstring JNICALL
Java_com_soul_lib_jni_JniTest_startUPDServer_1c(JNIEnv* env, jclass clazz);


JNIEXPORT void JNICALL
Java_com_soul_lib_jni_JniTest_sendClientData_1c(JNIEnv* env, jclass clazz, jstring str);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_soul_lib_jni_JniTest_testDemo_1c(JNIEnv* env, jclass clazz) {

    std::string str = "adbc";
    return env->NewStringUTF(str.c_str());
}


#define N 64

int send() {
    int sockfd;
    char buf[N] = "hello";
    struct sockaddr_un serveraddr, clientaddr;

    sockfd = socket(AF_LOCAL, SOCK_DGRAM, 0);
    if (sockfd < 0) {
        LOGE("fail to sockfd");
        return -1;
    }

    serveraddr.sun_family = AF_LOCAL;
    const char name[] = "\0your.local.socket.address";
    memcpy(serveraddr.sun_path, name, sizeof(name) - 1);

    int ret = sendto(sockfd, buf, N, 0, (struct sockaddr*) &serveraddr,
                     sizeof(serveraddr.sun_family) + sizeof(name) - 1);
    if (ret < 0) {
        LOGI("ret = %d, errno = %d, %s\n", ret, errno, strerror(errno));
    }
    LOGE("fail to bind : % d", ret);
    close(sockfd);

    return 0;
}

int udp() {
    int sockfd;
    struct sockaddr_un serveraddr, clientaddr;
    char buf[N];
    socklen_t len = sizeof(clientaddr);

    sockfd = socket(AF_LOCAL, SOCK_DGRAM, 0);
    if (sockfd < 0) {
        LOGE("fail to sockfd");
        return -1;
    }

    serveraddr.sun_family = AF_LOCAL;
    const char name[] = "\0your.local.socket.address";
    memcpy(serveraddr.sun_path, name, sizeof(name) - 1);
    //strcpy(serveraddr.sun_path, "mysocket");

    int ret = 0;


    ret = ::bind(sockfd, (struct sockaddr*) &serveraddr,
                 sizeof(serveraddr.sun_family) + sizeof(name) - 1);
    //bind(sockfd, (struct sockaddr*)&serveraddr, (socklen_t)sizeof(serveraddr));
    if (ret < 0) {
        LOGE("fail to bind : % d", ret);
        return -1;
    }

    while (1) {
        LOGE("waiting...");
        int ret = recvfrom(sockfd, buf, N, 0, (struct sockaddr*) &clientaddr, &len);
        if (ret < 0) {
            LOGE("fail to recvfrom");
            return -1;
        }

        buf[len - 1] = '\0';
        LOGD("buf:%s, %d", buf, len);
//        strcat(buf, "++++----");
//        if (sendto(sockfd, buf, N, 0, (struct sockaddr*) &clientaddr, len) < 0) {
//            LOGE("fail to sendto");
//            return -1;
//        }
    }
    close(sockfd);
    return 0;
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_soul_lib_jni_JniTest_startUPDServer_1c(JNIEnv* env, jclass clazz) {
    //启动服务端
    server server;
    server.init();

    udp();
    return NULL;

}


extern "C"
JNIEXPORT void JNICALL
Java_com_soul_lib_jni_JniTest_sendClientData_1c(JNIEnv* env, jclass clazz, jstring str) {
    client client;
    const char* string = env->GetStringUTFChars(str, NULL);
    client.sendData(string);

    env->ReleaseStringUTFChars(str, string);
//    send();
}


/**
 * 获取int的值
 */
void call_java_int(JNIEnv* env, jclass clazz, jobject test) {
    //获取方法id
    jmethodID pId = env->GetMethodID(clazz, "getDataSize", "()I");
    if (pId == 0) {
        LOGI("find method1 error");
        return;
    }
    LOGI("find method1 ");
    // 1 获取到int 值
    jint i = env->CallIntMethod(test, pId);
    LOGI("获取到的值:%d", i);
}

/**
 * 获取int的值
 */
void call_java_byte(JNIEnv* env, jclass clazz, jobject test) {
    //获取方法id
    jmethodID pId = env->GetMethodID(clazz, "getByteData", "()[B");
    if (pId == 0) {
        LOGI("find method1 error");
        return;
    }
    LOGI("find method1 ");
    // 1 获取到int 值
    jbyteArray jbyteArray1 = (jbyteArray) env->CallObjectMethod(test, pId);
    char* elements = (char*) env->GetByteArrayElements(jbyteArray1, NULL);
    LOGI("获取到的值:%s", elements);
    env->ReleaseByteArrayElements(jbyteArray1, (jbyte*) elements, 0);
}

/**
 * 设置java 数值
 * @param env
 * @param clazz
 * @param test
 */
void set_java_int(JNIEnv* env, jclass clazz, jobject test) {
    jmethodID pId = env->GetMethodID(clazz, "setDataSize", "(I)V");
    if (pId != 0) {
        env->CallVoidMethod(test, pId, 123);
    } else {
        LOGE("can't find method setAge");
    }
}

/**
 * 设置java 数值
 * @param env
 * @param clazz
 * @param test
 */
void set_java_string(JNIEnv* env, jclass clazz, jobject test) {
    jmethodID pId = env->GetMethodID(clazz, "setData", "(Ljava/lang/String;)V");
    if (pId != 0) {
        const char* strCont = "me to";
        env->CallVoidMethod(test, pId, env->NewStringUTF(strCont));
    } else {
        LOGE("can't find method setAge");
    }
}


/**
 * 设置String的值
 */
void call_java_string(JNIEnv* env, jclass clazz, jobject test) {
    //获取方法id
    jmethodID pId = env->GetMethodID(clazz, "getData", "()Ljava/lang/String;");
    if (pId == 0) {
        LOGI("find method1 error");
        return;
    }
    LOGI("find method1 ");
    // 1 获取到int 值
    jstring result = (jstring) env->CallObjectMethod(test, pId);
    const char* chars = env->GetStringUTFChars(result, NULL);
    LOGI("获取到的值:%s", chars);
    env->ReleaseStringUTFChars(result, chars);
}


/**
 * 获取java 层的数据
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_soul_lib_jni_JniTest_sendCallJavaTest(JNIEnv* env, jclass clazz, jobject test) {
    //获取到class 对象
    jclass pClass = env->FindClass("com/soul/libs/jni/CallJavaTest");
    if (pClass == 0) {
        LOGI("find class error");
        return;
    }
    LOGI("find class ");
//    call_java_int(env, pClass, test);
//    set_java_int(env, pClass, test);

//    call_java_string(env, pClass, test);

//    set_java_string(env, pClass, test);

//    call_java_byte(env, pClass, test);
//    set_java_byte(env, pClass, test);
}


