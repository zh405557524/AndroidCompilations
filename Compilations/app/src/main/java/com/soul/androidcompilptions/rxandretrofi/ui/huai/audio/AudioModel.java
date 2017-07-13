package com.soul.androidcompilptions.rxandretrofi.ui.huai.audio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.api.DrakeetFactory;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioBean;

import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/4 18:56
 */

public class AudioModel implements AudioContract.Model {

    @Override
    public Observable<AudioBean> loadVoiceData(String date) {
        return DrakeetFactory.getBadBoy().getAudioData(
                date,
                "355457084869117",
                "1499050382581deecae55de47b434791",
                "3355169",
                "4.2.21",
                ".other",
                ".1c92f2179c86cbdf22856444822b0c87"
        );
    }

    @Override
    public void saveVoiceLocalData(AudioBean audioBean, String date) {
        Gson gson = new Gson();
        String s = gson.toJson(audioBean);
        DBHelper.getInstance().insertOrUpdateValue(AudioBean.class.getName() + date, s);
    }

    @Override
    public AudioBean loadLocalData(String s) {
        Gson gson = new Gson();
        String jsonString = DBHelper.getInstance().queryValue(AudioBean.class.getName() + s);
        AudioBean o = gson.fromJson(jsonString, new TypeToken<AudioBean>() {
        }.getType());
        return o;
    }
}
