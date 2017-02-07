package com.soul.androidcompilptions.rxandretrofi.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 18:23
 */

public class Soul implements Serializable {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    public long id;
}
