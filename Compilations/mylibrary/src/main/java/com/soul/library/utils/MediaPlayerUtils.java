package com.soul.library.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * * @author soul
 *
 * @项目名:MyApplication
 * @包名: com.soul.library.utils
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/25 20:07
 */

public class MediaPlayerUtils {
    private static int maxVolume = 50; // 最大音量值
    private static int curVolume = 20; // 当前音量值
    private static int stepVolume = 0; // 每次调整的音量幅度
    private static AudioManager audioMgr;//播放器

    private static MediaPlayer sMediaPlayer;

    public static MediaPlayer create(Context context, int id) {
        if (sMediaPlayer == null) {
            sMediaPlayer = MediaPlayer.create(context, id);
        }
        return sMediaPlayer;
    }

    public static void adjustVolume(int volume, Context context) {
        if (audioMgr == null) {
            audioMgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            maxVolume = audioMgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        }
        audioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (((float)volume / 100.f) * (float)maxVolume),
                AudioManager.FLAG_PLAY_SOUND);
    }


}
