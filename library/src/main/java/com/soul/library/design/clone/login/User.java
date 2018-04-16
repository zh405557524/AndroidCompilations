package com.soul.library.design.clone.login;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:AndroidCompilations.git
 * @创建时间：2018/4/16 10:39
 */

public class User {

    public int age;
    public String name;
    public String phoneNum;
    public Address mAddress;


    @Override
    protected User clone() {
        User user = null;
        try {
            user = (User) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", mAddress=" + mAddress +
                '}';
    }
}
