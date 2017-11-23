package com.soul.library.design.builder;

/**
 * @描述:具体的computer类
 * @作者：祝明
 * @创建时间：2017/11/23 9:19
 */

public class MacBook extends Computer {

    public MacBook() {
    }

    @Override
    public void setOS() {
        mOS = "Mac OS X 10.10";
    }
}
