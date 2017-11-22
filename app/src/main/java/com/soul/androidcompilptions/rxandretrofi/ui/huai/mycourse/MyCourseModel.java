package com.soul.androidcompilptions.rxandretrofi.ui.huai.mycourse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.api.DrakeetFactory;
import com.soul.androidcompilptions.rxandretrofi.bean.MyCourseBean;
import com.soul.androidcompilptions.rxandretrofi.ui.huai.Constants;

import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 0:23
 */

public class MyCourseModel implements MyCourseContract.Model {

    @Override
    public Observable<MyCourseBean> loadMyCourseData() {

        return DrakeetFactory.getBadBoyResource().getMyCourseData(
                Constants.PHONE_SN,
                Constants.SESSION_ID,
                Constants.LOGIN_USER_ID,
                Constants.VERSION,
                Constants.APP_CHANNEL,
                Constants.PCID
        );
    }

    @Override
    public MyCourseBean loadMyCourseLocalData() {
        Gson gson = new Gson();
        String jsonString = DBHelper.getInstance().queryValue(MyCourseBean.class.getName());
        MyCourseBean o = gson.fromJson(jsonString, new TypeToken<MyCourseBean>() {
        }.getType());
        return o;
    }

    @Override
    public void saveMyCourse(MyCourseBean detailBean) {
        Gson gson = new Gson();
        String s = gson.toJson(detailBean);
        DBHelper.getInstance().insertOrUpdateValue(MyCourseBean.class.getName(), s);

    }
}
