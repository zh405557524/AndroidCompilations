package com.soul.androidcontent.binder.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.soul.androidcontent.binder.Book;
import com.soul.androidcontent.binder.server.BookManger;
import com.soul.androidcontent.binder.server.RemoteService;
import com.soul.androidcontent.binder.server.Stub;

import java.util.List;

/**
 * Description: TODO
 * Author: zhuMing
 * CreateDate: 2020/7/7 17:22
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/7/7 17:22
 * UpdateRemark:
 */
public class Client {


    private boolean isConnection;

    public void attemptToBindService(Context context) {

        Intent intent = new Intent(context, RemoteService.class);
        intent.setAction("com.soul.ipc.service");
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void stopService(Context context) {
        context.unbindService(serviceConnection);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        private BookManger bookManager;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            bookManager = Stub.asInterface(service);
            if (bookManager != null) {
                try {
                    List<Book> books = bookManager.getBooks();
                    Log.d("Client", books.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnection = false;
        }
    };


}
