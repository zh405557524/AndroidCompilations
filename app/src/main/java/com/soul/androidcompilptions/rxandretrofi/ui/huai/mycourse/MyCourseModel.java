package com.soul.androidcompilptions.rxandretrofi.ui.huai.mycourse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.api.DrakeetFactory;
import com.soul.androidcompilptions.rxandretrofi.bean.MyCourseBean;

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
                "355457084869117",
                "1499050382581deecae55de47b434791",
                "3355169",
                "4.2.21",
                ".other",
                ".1c92f2179c86cbdf22856444822b0c87"
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
