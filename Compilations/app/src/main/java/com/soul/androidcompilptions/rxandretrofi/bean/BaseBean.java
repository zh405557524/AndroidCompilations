package com.soul.androidcompilptions.rxandretrofi.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.bean
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 18:21
 */

public class BaseBean {


    public static final String _ID = "_id";


    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    public long _id;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public boolean error;
}
