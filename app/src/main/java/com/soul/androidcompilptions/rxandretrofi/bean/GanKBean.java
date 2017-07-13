package com.soul.androidcompilptions.rxandretrofi.bean;

import com.google.gson.annotations.SerializedName;
import com.soul.androidcompilptions.rxandretrofi.entity.GanK;

import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.bean
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/18 20:07
 */

public class GanKBean extends BaseBean {

    public Result results;
    public List<String> category;

    public class Result {
        @SerializedName("Android")
        public List<GanK> androidList;
        @SerializedName("休息视频")
        public List<GanK> restVideoList;
        @SerializedName("iOS")
        public List<GanK> iOSList;
        @SerializedName("福利")
        public List<GanK> welfareList;
        @SerializedName("拓展资源")
        public List<GanK> extendResourcesList;
        @SerializedName("瞎推荐")
        public List<GanK> blindRecommendList;
        @SerializedName("App")
        public List<GanK> appList;


    }


}
