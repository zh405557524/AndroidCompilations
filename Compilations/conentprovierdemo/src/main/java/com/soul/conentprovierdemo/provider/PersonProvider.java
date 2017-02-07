package com.soul.conentprovierdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.soul.conentprovierdemo.db.PersonOpenHelper;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.conentprovierdemo.provider
 * @作者：祝明
 * @描述：数据库内容提供者
 * @创建时间：2017/2/7 17:21
 */

public class PersonProvider extends ContentProvider {

    public static final int SUCCESS = 1;

    //创建一个uri路径匹配器
    static UriMatcher uriMatcher = new UriMatcher(-1);

    //添加默认的匹配规则(口令)
    static {
        //authority 主机名
        //path 路劲(写要查询的表名 )
        uriMatcher.addURI("com.soul.conentprovierdemo.provider", "person", 1);
    }

    private PersonOpenHelper mHelper;


    @Override
    public boolean onCreate() {
        mHelper = new PersonOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


    /**
     * 内容提供者的查询数据库方法
     *
     * @param uri      统一资源标识符，  url：同意资源定位符
     * @param strings  要查询的字段
     * @param s        查询条件
     * @param strings1 条件的参数
     * @param s1       排序
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        int code = uriMatcher.match(uri);
        switch (code){
            case SUCCESS:
                //数据库库查询操作，将结果返回给调用者
                SQLiteDatabase db = mHelper.getReadableDatabase();
                return  db.query("person",strings,s,strings1,null,null,s1);

            default:
                throw  new IllegalArgumentException("uri 匹配失败。");
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
