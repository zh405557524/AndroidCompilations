//
// Created by soul on 2020/2/20.
//

#include "autoai_log.h"
#include <stdio.h>
#include "server.h"
#include <sys/socket.h>
#include <string.h>
#include <unistd.h>

#define N 64


#define UNIX_DOMAIN "./udp_tmp"


#define PATH "com.penghui.localsocket"

void* connectThread(void* arg);

server::server() {

}

void server::init() {

    int sockfd;
    struct sockaddr_un serveraddr, clientaddr;
    char buf[N];
    socklen_t len = sizeof(clientaddr);

    sockfd = socket(AF_LOCAL, SOCK_DGRAM, 0);
    if (sockfd < 0) {
        LOGE("fail to sockfd");
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
    }

    while (1) {
        LOGE("waiting...");
        int ret = recvfrom(sockfd, buf, N, 0, (struct sockaddr*) &clientaddr, &len);
        if (ret < 0) {
            LOGE("fail to recvfrom");
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


}


void server::startListener() {

}


