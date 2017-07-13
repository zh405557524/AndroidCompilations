package com.soul.androidcompilptions.rxandretrofi;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.soul.androidcompilptions.rxandretrofi.bean.BaseBean;
import com.soul.androidcompilptions.rxandretrofi.bean.KeyValueBean;
import com.soul.library.BaseApplication;

import java.util.ArrayList;

/**
 * 数据库操作类
 * Created by MEI on 2017/4/10.
 */
public class DBHelper {

    public static final String TAG = "DBHelper";
    public static final String COLLECT_COUNT = "collect_count";

    private static DBHelper sHelper;
    private final LiteOrm mLiteOrm;

    private DBHelper() {
        mLiteOrm = BaseApplication.GetOrmInstance();
    }

    public static DBHelper getInstance() {
        if (sHelper == null) {
            synchronized (DBHelper.class) {
                if (sHelper == null) {
                    sHelper = new DBHelper();
                }
            }
        }
        return sHelper;
    }


    /**
     * 更新默认配置
     *
     * @param key
     * @param val
     * @return
     */
    public long insertOrUpdateValue(String key, String val) {
        ArrayList<KeyValueBean> query = mLiteOrm.query(new QueryBuilder<>(KeyValueBean.class)
                .where("key=?", key));
        KeyValueBean bean;
        long result;
        if (!query.isEmpty()) {
            bean = query.get(0);
            bean.setValue(val);
            result = mLiteOrm.update(bean, ConflictAlgorithm.Abort);
        } else {
            bean = new KeyValueBean(key, val);
            result = mLiteOrm.insert(bean, ConflictAlgorithm.Ignore);
        }
        return result;
    }

    /**
     * 获取默认配置
     *
     * @param key
     * @return
     */
    public String queryValue(String key) {
        ArrayList<KeyValueBean> query = mLiteOrm.query(new QueryBuilder<>(KeyValueBean.class)
                .where("key=?", key)
                .appendOrderAscBy(BaseBean._ID));
        if (query != null && !query.isEmpty()) {
            return query.get(0).getValue();
        }
        return "";
    }


    /**
     * 存储数据
     *
     * @param tClass        类的字节码
     * @param t             类的实体
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     */
    public <T> void save(Class<T> tClass, T t, String selection, String selectionArgs) {
        ArrayList<T> query = query(tClass, selection, selectionArgs);
        if (query == null || query.isEmpty()) {
            mLiteOrm.save(t);
        } else {
            mLiteOrm.update(t);
        }
    }

    /**
     * 删除数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     */
    public <T> void delete(Class<T> tClass, String selection, String selectionArgs) {
        mLiteOrm.delete(getWhereBuilder(tClass, selection, selectionArgs));
    }

    /**
     * 修改数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     */
    public <T> void upData(Class<T> tClass, String selection, String selectionArgs, ColumnsValue columnsValue) {
        mLiteOrm.update(getQueryBuilder(tClass, selection, selectionArgs), columnsValue, ConflictAlgorithm.Abort);
    }

    /**
     * 查询数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return 当前类的list集合
     */
    public <T> ArrayList<T> query(Class<T> tClass, String selection, String selectionArgs) {
        return mLiteOrm.query(getQueryBuilder(tClass, selection, selectionArgs));
    }

    /**
     * 查询数据
     *
     * @param tClass 类的字节码
     * @param <T>
     * @return 所有类的集合
     */
    public <T> ArrayList<T> query(Class<T> tClass) {
        return mLiteOrm.query(tClass);
    }

    /**
     * 获取查询参数
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return QueryBuilder
     */
    public <T> QueryBuilder<T> getQueryBuilder(Class<T> tClass, String selection, String selectionArgs) {

        return new QueryBuilder<>(tClass).where(selection + "=?", selectionArgs);
    }

    /**
     * 获取查询参数
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return WhereBuilder
     */
    public <T> WhereBuilder getWhereBuilder(Class<T> tClass, String selection, String selectionArgs) {
        return new WhereBuilder(tClass).where(selection + " = ?", selectionArgs);
    }

}
