//
// Created by andyzhao on 2019/8/24.
//

#ifndef CARASSISTANT_AUTOAI_LOG_H
#define CARASSISTANT_AUTOAI_LOG_H

#ifdef ANDROID

#include <string.h> //strrchr()函数所需头文件
#include <string>
#include <android/log.h>

using  namespace std;

#define  LOG_TAG ("NativeClient")

#define FILENAME(x) strrchr(x,'\\')?strrchr(x,'\\')+1:x

#define  LOG_PRINT(level, format, ...)  \
do  \
{   \
    std::string tag; \
    tag.append(LOG_TAG); \
    tag.append("-"); \
    tag.append(__FUNCTION__); \
    \
    std::string s; \
    s.append(format); \
    s.append("[%s:%d]"); \
    __android_log_print(level, tag.c_str(), s.c_str(), ##__VA_ARGS__, FILENAME(__FILE__), __LINE__); \
}   \
while (0);  \

#define  LOGD(format, ...)  LOG_PRINT(ANDROID_LOG_DEBUG, format, ##__VA_ARGS__)
#define  LOGE(format, ...)   LOG_PRINT(ANDROID_LOG_ERROR, format, ##__VA_ARGS__)
#define  LOGI(format, ...)    LOG_PRINT(ANDROID_LOG_INFO, format, ##__VA_ARGS__)
#define  LOGW(format, ...) LOG_PRINT(ANDROID_LOG_WARN, format, ##__VA_ARGS__)

#else
#include <stdio.h>

#endif


#endif //CARASSISTANT_AUTOAI_LOG_H
