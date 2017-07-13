package com.soul.conentprovierdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.conentprovierdemo.db
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/2/7 17:10
 */

public class PersonOpenHelper extends SQLiteOpenHelper {

    public static final String T_PERSON = "t_person";

    public class personTable implements BaseColumns {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";

    }

    public PersonOpenHelper(Context context) {
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + T_PERSON + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                personTable.ID + " TEXT," +
                personTable.NAME + " TEXT," +
                personTable.AGE + " TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
