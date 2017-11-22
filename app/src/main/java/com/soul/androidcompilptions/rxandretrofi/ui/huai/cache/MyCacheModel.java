package com.soul.androidcompilptions.rxandretrofi.ui.huai.cache;

import com.soul.androidcompilptions.rxandretrofi.DBHelper;
import com.soul.androidcompilptions.rxandretrofi.bean.DownloadAudioBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/11 22:00
 */

public class MyCacheModel implements MyCacheContract.Model {

    @Override
    public Observable<List<DownloadAudioBean>> loadCacheData() {

        return Observable.create(new Observable.OnSubscribe<List<DownloadAudioBean>>() {
            @Override
            public void call(Subscriber<? super List<DownloadAudioBean>> subscriber) {
                ArrayList<DownloadAudioBean> query = DBHelper.getInstance().query(DownloadAudioBean.class, "state", "1");
                subscriber.onNext(query);
            }
        });
    }

            @Override
            public Observable<List<List<DownloadAudioBean>>> sortCacheData(List<DownloadAudioBean> list) {
                List<List<DownloadAudioBean>> lists = new ArrayList<>();
                Collections.sort(list);

                String nameType = "";
                List<DownloadAudioBean> tempList = null;
                for (int x = 0; x < list.size(); x++) {
                    DownloadAudioBean downloadAudioBean = list.get(x);
                    String video_name = downloadAudioBean.getVideo_name();
                    if (!nameType.equals(video_name)) {
                        if (tempList != null) {
                            lists.add(tempList);
                        }
                        tempList = new ArrayList<>();
                        tempList.add(downloadAudioBean);
                        nameType = video_name;
                    } else {
                        tempList.add(downloadAudioBean);
                    }
                }
                if (tempList != null) {
                    lists.add(tempList);
        }
        return Observable.just(lists);
    }
}
