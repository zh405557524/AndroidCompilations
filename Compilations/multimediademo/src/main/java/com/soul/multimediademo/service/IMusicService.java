package com.soul.multimediademo.service;


import com.soul.multimediademo.MusicInfo;

import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.servicedemo
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/2/6 15:46
 */

public interface IMusicService {
    /**
     * 中间人的方法，调用服务里的播放音乐
     * @param infoS
     */
    public void callPlay(List<MusicInfo> infoS, int index);

    /**
     * 获取当前播放位置
     * @return
     */
    public int callGetCurrentPosition();
}
