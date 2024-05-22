package com.soul.androidcontent.binder.server;

import android.os.IInterface;
import android.os.RemoteException;

import com.soul.androidcontent.binder.Book;

import java.util.List;

/**
 * @Description：这个类用来定义服务端 remoteService具备什么样的能力
 * @Author：祝明
 * @ProjectName:binder
 * @CreateData：2020/6/8 17:21
 */
public interface BookManger extends IInterface {



    List<Book> getBooks() throws RemoteException;

    void addBook(Book book) throws RemoteException;

}
