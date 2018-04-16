package com.soul.library.design.clone.login;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:AndroidCompilations
 * @创建时间：2018/4/16 10:58
 */

public class LoginImpl implements Login {

    @Override
    public void login() {
        User user = new User();
        user.age = 22;
        user.name = "Mr.Simple";
        user.mAddress = new Address("北京市", "海定区", "花园东路");
        LoginSession.getLoginSession().setLoginEdUser(user);
    }
}
