package com.soul.library.design.builder;

/**
 * @描述：斧子构造C
 * @作者：祝明
 * @创建时间：2017/11/23 9:22
 */

public class Director {

    Builder mBuilder = null;


    public Director(Builder builder) {
        mBuilder = builder;
    }


    public void construct(String board, String display) {
        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.BuildOS();
    }
}
