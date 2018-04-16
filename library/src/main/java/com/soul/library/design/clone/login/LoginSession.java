package com.soul.library.design.clone.login;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:AndroidCompilations
 * @创建时间：2018/4/16 10:58
 */

public class LoginSession {

    static LoginSession sLoginSession = null;

    private User loginEdUser;

    public LoginSession() {

    }


    public static LoginSession getLoginSession() {
        if (sLoginSession == null) {
            sLoginSession = new LoginSession();
        }
        return sLoginSession;
    }


    public User getLoginEdUser() {
        return loginEdUser;
    }

    void setLoginEdUser(User loginEdUser) {
        this.loginEdUser = loginEdUser.clone();
    }
}
