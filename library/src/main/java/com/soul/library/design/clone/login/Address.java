package com.soul.library.design.clone.login;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:AndroidCompilations
 * @创建时间：2018/4/16 10:57
 */

public class Address {

    public String city;

    public String district;

    public String street;

    public Address(String city, String district, String street) {
        this.city = city;
        this.district = district;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
