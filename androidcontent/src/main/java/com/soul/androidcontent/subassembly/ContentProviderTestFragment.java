package com.soul.androidcontent.subassembly;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import com.soul.lib.test.ButtonTextFragment;

/**
 * Description: 内容提供者测试类
 * Author: zhuMing
 * CreateDate: 2020/6/29 14:53
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/29 14:53
 * UpdateRemark:
 */
public class ContentProviderTestFragment extends ButtonTextFragment implements View.OnClickListener {
    @Override
    public String getClassName() {
        return "内容提供者demo演示";
    }

    @Override
    protected void initEvent() {
        addTextName("添加数据", this);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        switch (tag) {
            case "添加数据":
                /**
                 * 对user表进行操作
                 */

                // 设置URI
                Uri uri_user = Uri.parse("content://scut.carson_ho.myprovider/user");

                // 插入表中数据
                ContentValues values = new ContentValues();
                values.put("_id", 4);
                values.put("name", "Jordan");


                // 获取ContentResolver
                ContentResolver resolver = getContext().getContentResolver();
                // 通过ContentResolver 根据URI 向ContentProvider中插入数据
                resolver.insert(uri_user, values);

                // 通过ContentResolver 向ContentProvider中查询数据
                Cursor cursor = resolver.query(uri_user, new String[]{"_id", "name"}, null, null, null);
                while (cursor.moveToNext()) {
                    System.out.println("query book:" + cursor.getInt(0) + " " + cursor.getString(1));
                    // 将表中数据全部输出
                }
                cursor.close();
                // 关闭游标

                /**
                 * 对job表进行操作
                 */
                // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
                Uri uri_job = Uri.parse("content://scut.carson_ho.myprovider/job");

                // 插入表中数据
                ContentValues values2 = new ContentValues();
                values2.put("_id", 4);
                values2.put("job", "NBA Player");

                // 获取ContentResolver
                ContentResolver resolver2 = getContext().getContentResolver();
                // 通过ContentResolver 根据URI 向ContentProvider中插入数据
                resolver2.insert(uri_job, values2);

                // 通过ContentResolver 向ContentProvider中查询数据
                Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id", "job"}, null, null, null);
                while (cursor2.moveToNext()) {
                    System.out.println("query job:" + cursor2.getInt(0) + " " + cursor2.getString(1));
                    // 将表中数据全部输出
                }
                cursor2.close();
                break;
        }
    }
}
