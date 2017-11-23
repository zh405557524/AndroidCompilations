package com.soul.library.design.builder;

/**
 * @描述：具体的builder类
 * @作者：祝明
 * @创建时间：2017/11/23 9:21
 */

public class MacBookBuilder extends Builder {
    private Computer mComputer = new MacBook();

    @Override
    public void buildBoard(String board) {
        mComputer.setBoard(board);
    }

    @Override
    public void buildDisplay(String display) {
        mComputer.setDisplay(display);
    }

    @Override
    public void BuildOS() {
        mComputer.setOS();
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
