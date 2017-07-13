package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.api.DrakeetFactory;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DetailBean;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;
import com.soul.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/6 22:22
 */

public class DetailModel implements DetailContract.Model {


    /**
     * 网络获取详情界面
     *
     * @param broadcast_id
     * @return
     */
    @Override
    public Observable<DetailBean> loadDetailData(String broadcast_id) {

        return DrakeetFactory.getBadBoy().getDetailData("",
                broadcast_id,
                "0",
                "0",
                "355457084869117",
                "1499050382581deecae55de47b434791",
                "3355169",
                "4.2.21",
                ".other",
                ".1c92f2179c86cbdf22856444822b0c87"
        );
    }

    /**
     * 从网络获取音频集合数据
     *
     * @param audio_id
     * @return
     */
    @Override
    public Observable<AudioListBean> loadAudioListData(String audio_id) {

        return DrakeetFactory.getBadBoyResource().getAudioListData(
                audio_id,
                "broadcast_history",
                "",
                "0",
                "355457084869117",
                "1499050382581deecae55de47b434791",
                "3355169",
                "4.2.21",
                ".other",
                ".1c92f2179c86cbdf22856444822b0c87"
        );
    }

    /**
     * 保存详情数据到本地
     *
     * @param detailBean
     * @param broadcast_id
     */
    @Override
    public void saveDetail(DetailBean detailBean, String broadcast_id) {
        Gson gson = new Gson();
        String s = gson.toJson(detailBean);
        DBHelper.getInstance().insertOrUpdateValue(DetailBean.class.getName() + broadcast_id, s);
    }

    /**
     * 获取详情界面的本地数据
     *
     * @param broadcast_id
     * @return
     */
    @Override
    public DetailBean loadDetailLocalData(String broadcast_id) {
        Gson gson = new Gson();
        String jsonString = DBHelper.getInstance().queryValue(DetailBean.class.getName() + broadcast_id);
        DetailBean o = gson.fromJson(jsonString, new TypeToken<DetailBean>() {
        }.getType());
        if (o != null) {
            LogUtils.i("查询loadDetailLocalData:" + o.toString());
        } else {
            LogUtils.i("查询loadDetailLocalData:null");
        }
        return o;
    }


    /**
     * 获取音频集合的本地数据
     *
     * @param audio_id
     * @return
     */
    @Override
    public AudioListBean loadAudioLocalData(String audio_id) {
        Gson gson = new Gson();
        String jsonString = DBHelper.getInstance().queryValue(AudioListBean.class.getName() + audio_id);
        AudioListBean o = gson.fromJson(jsonString, new TypeToken<AudioListBean>() {
        }.getType());
        return o;
    }


    @Override
    public DownloadAudioBean saveDownloadRecord(String url, int section_id, AudioListBean.DataBean.HeadBean headBean,
                                                String title, String time, AudioListBean.DataBean.ListBean listBean) {
        DownloadAudioBean downloadAudioBean = new DownloadAudioBean(url, section_id + "",
                headBean.getVideo_type(), headBean.getVideo_name(), headBean.getPhoto(), title);
        downloadAudioBean.setState("0");
        downloadAudioBean.setMax("100");
        downloadAudioBean.setProgress("1");
        downloadAudioBean.setCreate_time(time);
        DBHelper.getInstance().save(DownloadAudioBean.class, downloadAudioBean, "section_id", section_id + "");
        return downloadAudioBean;
    }

    public List<DownloadAudioBean> getDownloadRecord(String video_type) {

        ArrayList<DownloadAudioBean> video_type1 = DBHelper.getInstance().query(DownloadAudioBean.class);
        return video_type1;
    }

    @Override
    public List<DownloadAudioBean> loadDownloadData() {
        ArrayList<DownloadAudioBean> state = DBHelper.getInstance().query(DownloadAudioBean.class, "state", "0");
        return state;
    }


    /**
     * 保存音频集合数据到本地
     *
     * @param audioListBean
     * @param audio_id
     */
    @Override
    public void saveAudio(AudioListBean audioListBean, String audio_id) {
        Gson gson = new Gson();
        String s = gson.toJson(audioListBean);
        DBHelper.getInstance().insertOrUpdateValue(AudioListBean.class.getName() + audio_id, s);
    }
}
