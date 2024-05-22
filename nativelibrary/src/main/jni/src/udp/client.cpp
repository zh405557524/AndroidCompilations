//
// Created by soul on 2020/2/20.
//

#include <sys/socket.h>
#include <unistd.h>
#include "client.h"
#include "server.h"
#include "autoai_log.h"

#define N 64
#define UNIX_DOMAIN "./udp_tmp"

int sockfd;

client::client() {
    init();
}

void client::init() {

}

void client::sendData(std::string string) {
    char buf[N];

    struct sockaddr_un serveraddr, clientaddr;

    sockfd = socket(AF_LOCAL, SOCK_DGRAM, 0);
    if (sockfd < 0) {
        LOGI("fail to sockfd");
    }

    serveraddr.sun_family = AF_LOCAL;

    const char name[] = "\0your.local.socket.address";
    const char str[] = "123456";

    memcpy(serveraddr.sun_path, name, sizeof(name) - 1);

//    const char clientName[] = "\0socket";
//    clientaddr.sun_family = AF_LOCAL;
//    memcpy(clientaddr.sun_path, clientName, sizeof(clientName) - 1);

//    if (bind(sockfd, (struct sockaddr*) &clientaddr, sizeof(clientaddr)) < 0) {
//        LOGI("fail to bind");
//    }

    int ret = sendto(sockfd, str, sizeof(str), 0,
                     (struct sockaddr const*) &serveraddr,
                     sizeof(serveraddr.sun_family) + sizeof(name) - 1);

//    int ret = sendto(sockfd, string.c_str(), string.size(), 0, (struct sockaddr*) &serveraddr,
//                     sizeof(serveraddr));
    if (ret < 0) {
        LOGI("ret = %d, errno = %d, %s\n", ret, errno, strerror(errno));
    }
    close(sockfd);


}
