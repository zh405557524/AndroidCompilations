//
// Created by soul on 2020/2/20.
//

#ifndef LIBS_SERVER_H
#define LIBS_SERVER_H

struct sockaddr_un {
    unsigned short sun_family; /*协议类型*/
    char sun_path[108];           /*套接字文件路径*/
};

class server {

public:
    server();

public:
    /**
     * 初始化
     */
    void init();

    /**
     * 启动服务端监听
     */
    void startListener();
};


#endif //LIBS_SERVER_H
