package com.soul.androidcompilptions.rxandretrofi.bean;

import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/7 23:29
 */

public class DetailBean extends BaseBean {


    /**
     * data : {"admin_list":[],"broadcast":{"broadcast_id":57037,"has_learned":true,"is_secret_class":true,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330","has_weixin":0,"price":0,"user_id":12657967,"user_name":"春心","user_type":1},"photos":["http://morgoth-aman.huainanhai.com/broadcast/12657967/1706261910388605600"],"share_url":"http://legolas.huainanhai.com/broadcast-57037","subject":"如何找到你心目中完美的女生","teach_type":1,"webview_url":"http://playback-ts.huainanhai.com/57037.mp3?v=1"},"is_upload":false}
     * rtn : 0
     */

    private DataBean data;
    private int rtn;

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public int getRtn() { return rtn;}

    public void setRtn(int rtn) { this.rtn = rtn;}

    public static class DataBean {
        /**
         * admin_list : []
         * broadcast : {"broadcast_id":57037,"has_learned":true,"is_secret_class":true,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330","has_weixin":0,"price":0,"user_id":12657967,"user_name":"春心","user_type":1},"photos":["http://morgoth-aman.huainanhai.com/broadcast/12657967/1706261910388605600"],"share_url":"http://legolas.huainanhai.com/broadcast-57037","subject":"如何找到你心目中完美的女生","teach_type":1,"webview_url":"http://playback-ts.huainanhai.com/57037.mp3?v=1"}
         * is_upload : false
         */

        private BroadcastBean broadcast;
        private boolean is_upload;
        private List<?> admin_list;

        public BroadcastBean getBroadcast() { return broadcast;}

        public void setBroadcast(BroadcastBean broadcast) { this.broadcast = broadcast;}

        public boolean isIs_upload() { return is_upload;}

        public void setIs_upload(boolean is_upload) { this.is_upload = is_upload;}

        public List<?> getAdmin_list() { return admin_list;}

        public void setAdmin_list(List<?> admin_list) { this.admin_list = admin_list;}

        public static class BroadcastBean {
            /**
             * broadcast_id : 57037
             * has_learned : true
             * is_secret_class : true
             * mentor : {"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330","has_weixin":0,"price":0,"user_id":12657967,"user_name":"春心","user_type":1}
             * photos : ["http://morgoth-aman.huainanhai.com/broadcast/12657967/1706261910388605600"]
             * share_url : http://legolas.huainanhai.com/broadcast-57037
             * subject : 如何找到你心目中完美的女生
             * teach_type : 1
             * webview_url : http://playback-ts.huainanhai.com/57037.mp3?v=1
             */

            private int broadcast_id;
            private boolean has_learned;
            private boolean is_secret_class;
            private MentorBean mentor;
            private String share_url;
            private String subject;
            private int teach_type;
            private String webview_url;
            private List<String> photos;

            public int getBroadcast_id() { return broadcast_id;}

            public void setBroadcast_id(int broadcast_id) { this.broadcast_id = broadcast_id;}

            public boolean isHas_learned() { return has_learned;}

            public void setHas_learned(boolean has_learned) { this.has_learned = has_learned;}

            public boolean isIs_secret_class() { return is_secret_class;}

            public void setIs_secret_class(boolean is_secret_class) { this.is_secret_class = is_secret_class;}

            public MentorBean getMentor() { return mentor;}

            public void setMentor(MentorBean mentor) { this.mentor = mentor;}

            public String getShare_url() { return share_url;}

            public void setShare_url(String share_url) { this.share_url = share_url;}

            public String getSubject() { return subject;}

            public void setSubject(String subject) { this.subject = subject;}

            public int getTeach_type() { return teach_type;}

            public void setTeach_type(int teach_type) { this.teach_type = teach_type;}

            public String getWebview_url() { return webview_url;}

            public void setWebview_url(String webview_url) { this.webview_url = webview_url;}

            public List<String> getPhotos() { return photos;}

            public void setPhotos(List<String> photos) { this.photos = photos;}

            public static class MentorBean {
                /**
                 * avatar : http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330
                 * has_weixin : 0
                 * price : 0
                 * user_id : 12657967
                 * user_name : 春心
                 * user_type : 1
                 */

                private String avatar;
                private int has_weixin;
                private int price;
                private int user_id;
                private String user_name;
                private int user_type;

                public String getAvatar() { return avatar;}

                public void setAvatar(String avatar) { this.avatar = avatar;}

                public int getHas_weixin() { return has_weixin;}

                public void setHas_weixin(int has_weixin) { this.has_weixin = has_weixin;}

                public int getPrice() { return price;}

                public void setPrice(int price) { this.price = price;}

                public int getUser_id() { return user_id;}

                public void setUser_id(int user_id) { this.user_id = user_id;}

                public String getUser_name() { return user_name;}

                public void setUser_name(String user_name) { this.user_name = user_name;}

                public int getUser_type() { return user_type;}

                public void setUser_type(int user_type) { this.user_type = user_type;}

                @Override
                public String toString() {
                    return "MentorBean{" +
                            "avatar='" + avatar + '\'' +
                            ", has_weixin=" + has_weixin +
                            ", price=" + price +
                            ", user_id=" + user_id +
                            ", user_name='" + user_name + '\'' +
                            ", user_type=" + user_type +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "BroadcastBean{" +
                        "broadcast_id=" + broadcast_id +
                        ", has_learned=" + has_learned +
                        ", is_secret_class=" + is_secret_class +
                        ", mentor=" + mentor +
                        ", share_url='" + share_url + '\'' +
                        ", subject='" + subject + '\'' +
                        ", teach_type=" + teach_type +
                        ", webview_url='" + webview_url + '\'' +
                        ", photos=" + photos +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "broadcast=" + broadcast +
                    ", is_upload=" + is_upload +
                    ", admin_list=" + admin_list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DetailBean{" +
                "data=" + data +
                ", rtn=" + rtn +
                '}';
    }
}
