package com.soul.androidcompilptions.rxandretrofi.bean;

import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 23:54
 */

public class VideoUrlBean {


    /**
     * data : {"is_pay":1,"line_type":[0,1],"url":"http://smaug-aman-media.huainanhai.com/vcourse/868_570dea7d956d9-full-app?sign=54ce7ab3e0fdc9bd4a2f6799caa4418b&t=5963b12e"}
     * rtn : 0
     */

    private DataBean data;
    private int rtn;

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public int getRtn() { return rtn;}

    public void setRtn(int rtn) { this.rtn = rtn;}


    @Override
    public String toString() {
        return "VideoUrlBean{" +
                "data=" + data +
                ", rtn=" + rtn +
                '}';
    }

    public static class DataBean {
        /**
         * is_pay : 1
         * line_type : [0,1]
         * url : http://smaug-aman-media.huainanhai.com/vcourse/868_570dea7d956d9-full-app?sign=54ce7ab3e0fdc9bd4a2f6799caa4418b&t=5963b12e
         */

        private int is_pay;
        private String url;
        private List<Integer> line_type;

        public int getIs_pay() { return is_pay;}

        public void setIs_pay(int is_pay) { this.is_pay = is_pay;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public List<Integer> getLine_type() { return line_type;}

        public void setLine_type(List<Integer> line_type) { this.line_type = line_type;}

        @Override
        public String toString() {
            return "DataBean{" +
                    "is_pay=" + is_pay +
                    ", url='" + url + '\'' +
                    ", line_type=" + line_type +
                    '}';
        }
    }
}
