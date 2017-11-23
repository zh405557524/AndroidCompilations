package com.soul.library;

import com.soul.library.design.builder.Director;
import com.soul.library.design.builder.MacBookBuilder;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/11/23 9:32
 */

public class Test {


    public static void main(String[] args) {
        MacBookBuilder macBookBuilder = new MacBookBuilder();
        Director director = new Director(macBookBuilder);
        director.construct("因特尔主板", "Retina 显示器");
        System.out.println("Computer Info :" + macBookBuilder.create().toString());

    }
}
