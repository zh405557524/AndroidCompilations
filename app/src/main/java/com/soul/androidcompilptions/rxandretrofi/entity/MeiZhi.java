package com.soul.androidcompilptions.rxandretrofi.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Table;

import java.util.Date;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 18:22
 */
@Table("meiZhiS")
public class MeiZhi extends Soul {

    @Column("url")
    public String url;
    @Column("type")
    public String type;
    @Column("desc")
    public String desc;
    @Column("who")
    public String who;
    @Column("used")
    public boolean used;
    @Column("createdAt")
    public Date createdAt;
    @Column("updatedAt")
    public Date updatedAt;
    @Column("publishedAt")
    public Date publishedAt;
    @Column("imageWidth")
    public int imageWidth;
    @Column("imageHeight")
    public int imageHeight;


}
