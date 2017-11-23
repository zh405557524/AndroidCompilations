package com.soul.library.design.builder;

/**
 * @描述：计算机抽象类
 * @作者：祝明
 * @创建时间：2017/11/23 9:18
 */

public abstract class Computer {
    protected String mBoard;//主板
    protected String mDisplay;//显示器
    protected String mOS;

    public String getBoard() {
        return mBoard;
    }

    public void setBoard(String board) {
        mBoard = board;
    }

    public String getDisplay() {
        return mDisplay;
    }

    public void setDisplay(String display) {
        mDisplay = display;
    }

    public String getOS() {
        return mOS;
    }

    public abstract void setOS();

}
