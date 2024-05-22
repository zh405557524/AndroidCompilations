package com.soul.androidcontent.binder.proxy;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.soul.androidcontent.binder.Book;
import com.soul.androidcontent.binder.server.BookManger;
import com.soul.androidcontent.binder.server.Stub;

import java.util.List;

/**
 * @Description：TODO
 * @Author：祝明
 * @ProjectName:binder
 * @CreateData：2020/6/8 17:50
 */
public class Proxy implements BookManger {

    private static final String DESCRIPTOR = "com.soul.binder.server.BookManger";

    public IBinder remote;


    public Proxy(IBinder remote) {
        this.remote = remote;
    }


    public String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public List<Book> getBooks() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();
        List<Book> result = null;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            remote.transact(Stub.TRANSAVTION_getBooks, data, replay, 0);
            replay.readException();
            result = replay.createTypedArrayList(Book.CREATOR);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            data.recycle();
            replay.recycle();
        }
        return result;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();

        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if (book != null) {
                data.writeInt(1);
                book.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            remote.transact(Stub.TRANSAVTION_addBook, data, replay, 0);
            replay.readException();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            replay.recycle();
            data.recycle();
        }


    }

    @Override
    public IBinder asBinder() {
        return remote;
    }
}
