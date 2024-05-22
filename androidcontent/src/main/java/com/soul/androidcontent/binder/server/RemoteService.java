package com.soul.androidcontent.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.soul.androidcontent.binder.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Description：TODO
 * @Author：祝明
 * @ProjectName:binder
 * @CreateData：2020/6/8 20:24
 */
public class RemoteService extends Service {
    private List<Book> mBooks = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();

        Book book = new Book();
        book.setName("三体");
        book.setPrice(88);
        mBooks.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return bookManger;
    }

    private final Stub bookManger = new Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }

                if (book == null)
                    return;

                book.setPrice(book.getPrice() * 2);
                mBooks.add(book);

                Log.e("Server", "books: " + book.toString());
            }
        }
    };


}
