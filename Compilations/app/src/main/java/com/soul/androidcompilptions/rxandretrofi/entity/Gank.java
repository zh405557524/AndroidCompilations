package com.soul.androidcompilptions.rxandretrofi.entity;

import com.litesuits.orm.db.annotation.Column;

import java.util.Date;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.entity
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/18 20:09
 */

public class GanK extends Soul {


    @Column("url") public String url;
    @Column("type") public String type;
    @Column("desc") public String desc;
    @Column("who") public String who;
    @Column("used") public boolean used;
    @Column("createdAt") public Date createdAt;
    @Column("updatedAt") public Date updatedAt;
    @Column("publishedAt") public Date publishedAt;


}
