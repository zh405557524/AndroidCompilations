package com.soul.androidcompilptions.rxandretrofi.bean;

import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 0:31
 */

public class MyCourseBean {

    /**
     * rtn : 0
     * data : {"list":[{"course_id":506,"spec_id":0,"course_name":"搭讪技巧","photo_image":"http://morgoth-aman.huainanhai.com/course/506/8160894","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":4,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":499,"spec_id":0,"course_name":"型男穿搭养成计划","photo_image":"http://morgoth-aman.huainanhai.com/course/499/8161664","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":1,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":500,"spec_id":0,"course_name":"聊天宝典","photo_image":"http://morgoth-aman.huainanhai.com/course/500/8188791","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":1,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":498,"spec_id":0,"course_name":"魅力工程2.0","photo_image":"http://morgoth-aman.huainanhai.com/course/498/6924049","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":2,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":505,"spec_id":0,"course_name":"挽回爱情","photo_image":"http://morgoth-aman.huainanhai.com/course/505/6924047","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":73,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":497,"spec_id":0,"course_name":"魅力工程1.0","photo_image":"http://morgoth-aman.huainanhai.com/course/497/6821970","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":0,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":503,"spec_id":0,"course_name":"约会攻略","photo_image":"http://morgoth-aman.huainanhai.com/course/503/8188796","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":8,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":314,"spec_id":0,"course_name":"坏男孩官方《魅力工程》高级版","photo_image":"http://morgoth-aman.huainanhai.com/course/314/3703077","course_service_type":"course","order_type":"course","is_free":false,"learn_progress":0,"expire_day":4,"mentor_user_id":99,"teach_material_count":239,"is_payover":1,"order_status":2},{"course_id":504,"spec_id":0,"course_name":"谜男方法深度解析","photo_image":"http://morgoth-aman.huainanhai.com/course/504/8189065","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":5,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":501,"spec_id":0,"course_name":"相亲宝典","photo_image":"http://morgoth-aman.huainanhai.com/course/501/8160323","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":0,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":502,"spec_id":0,"course_name":"校园恋爱","photo_image":"http://morgoth-aman.huainanhai.com/course/502/8160897","course_service_type":"video","order_type":"course","is_free":true,"learn_progress":0,"expire_day":4,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":2},{"course_id":498,"spec_id":0,"course_name":"魅力工程2.0","photo_image":"http://morgoth-aman.huainanhai.com/course/498/6924049","course_service_type":"video","order_type":"course","is_free":false,"learn_progress":2,"expire_day":0,"mentor_user_id":99,"teach_material_count":0,"is_payover":1,"order_status":0}]}
     */

    private int rtn;
    private DataBean data;

    public int getRtn() { return rtn;}

    public void setRtn(int rtn) { this.rtn = rtn;}

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() { return list;}

        public void setList(List<ListBean> list) { this.list = list;}

        public static class ListBean {
            /**
             * course_id : 506
             * spec_id : 0
             * course_name : 搭讪技巧
             * photo_image : http://morgoth-aman.huainanhai.com/course/506/8160894
             * course_service_type : video
             * order_type : course
             * is_free : true
             * learn_progress : 4.0
             * expire_day : 4
             * mentor_user_id : 99
             * teach_material_count : 0
             * is_payover : 1
             * order_status : 2
             */

            private int course_id;
            private int spec_id;
            private String course_name;
            private String photo_image;
            private String course_service_type;
            private String order_type;
            private boolean is_free;
            private double learn_progress;
            private int expire_day;
            private int mentor_user_id;
            private int teach_material_count;
            private int is_payover;
            private int order_status;

            public int getCourse_id() { return course_id;}

            public void setCourse_id(int course_id) { this.course_id = course_id;}

            public int getSpec_id() { return spec_id;}

            public void setSpec_id(int spec_id) { this.spec_id = spec_id;}

            public String getCourse_name() { return course_name;}

            public void setCourse_name(String course_name) { this.course_name = course_name;}

            public String getPhoto_image() { return photo_image;}

            public void setPhoto_image(String photo_image) { this.photo_image = photo_image;}

            public String getCourse_service_type() { return course_service_type;}

            public void setCourse_service_type(String course_service_type) { this.course_service_type = course_service_type;}

            public String getOrder_type() { return order_type;}

            public void setOrder_type(String order_type) { this.order_type = order_type;}

            public boolean isIs_free() { return is_free;}

            public void setIs_free(boolean is_free) { this.is_free = is_free;}

            public double getLearn_progress() { return learn_progress;}

            public void setLearn_progress(double learn_progress) { this.learn_progress = learn_progress;}

            public int getExpire_day() { return expire_day;}

            public void setExpire_day(int expire_day) { this.expire_day = expire_day;}

            public int getMentor_user_id() { return mentor_user_id;}

            public void setMentor_user_id(int mentor_user_id) { this.mentor_user_id = mentor_user_id;}

            public int getTeach_material_count() { return teach_material_count;}

            public void setTeach_material_count(int teach_material_count) { this.teach_material_count = teach_material_count;}

            public int getIs_payover() { return is_payover;}

            public void setIs_payover(int is_payover) { this.is_payover = is_payover;}

            public int getOrder_status() { return order_status;}

            public void setOrder_status(int order_status) { this.order_status = order_status;}
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyCourseBean{" +
                "rtn=" + rtn +
                ", data=" + data +
                '}';
    }
}
