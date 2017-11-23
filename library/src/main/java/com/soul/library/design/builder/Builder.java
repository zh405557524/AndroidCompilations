package com.soul.library.design.builder;

/**
 * @描述：抽象的builder类
 * @作者：祝明
 * @创建时间：2017/11/23 9:21
 */

public abstract class Builder {

    public abstract void buildBoard(String board);

    public abstract void buildDisplay(String display);

    public abstract void BuildOS();

    public abstract Computer create();

}
