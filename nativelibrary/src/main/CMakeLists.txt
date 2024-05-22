# 默认命令
cmake_minimum_required(VERSION 3.4.1)

#工程名
project(Lib)

include_directories(${CMAKE_SOURCE_DIR}/./jni/src/udp/)
include_directories(${CMAKE_SOURCE_DIR}/./jni/thirdparty/cutils/include/)

#print cmake build log
message(STATUS " CMAKE_ANDROID_ARCH_ABI : ${CMAKE_ANDROID_ARCH_ABI}")
MESSAGE("----NATIVE_CLIENT_VERSION_INFO=" ${NATIVE_CLIENT_VERSION_INFO})
add_compile_options(-DNATIVE_CLIENT_VERSION_INFO="${NATIVE_CLIENT_VERSION_INFO}")


#递归的方式，添加所有的源文件到变量中
file(GLOB_RECURSE glob_source_files "${CMAKE_CURRENT_SOURCE_DIR}/./jni/src/*.c" "${CMAKE_CURRENT_SOURCE_DIR}/./jni/*.c" "${CMAKE_CURRENT_SOURCE_DIR}/./jni/src/*.cpp" "${CMAKE_CURRENT_SOURCE_DIR}/./jni/src/*.cc")

#设置lib的生成路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI})


#添加需要链接的第三库的搜索路径，链接第三方库的时候，需要添加库的搜索路径,系统预置的库，不需要添加搜索路径ee
LINK_DIRECTORIES(${CMAKE_SOURCE_DIR}/jni/thirdparty/cutils/lib/)


add_library(cutils
        SHARED
        IMPORTED)

set_target_properties(cutils
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/./jni/thirdparty/cutils/lib/libcutils.so)

#编译动态库
add_library(Lib SHARED

        ${glob_source_files})

#链接第三方库以及系统库(该命令需要放置在add_XXX之后，否则会报错)
target_link_libraries(Lib cutils -llog -lz
        -utils
        )
