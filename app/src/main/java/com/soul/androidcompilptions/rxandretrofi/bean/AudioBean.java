package com.soul.androidcompilptions.rxandretrofi.bean;

import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.bean
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/7/4 0:30
 */

public class AudioBean extends BaseBean {


    /**
     * data : {"circulation":[{"audio_id":0,"authority":"loop_course_level1","begin_time":"2017-06-28 19:00:00","broadcast_id":57039,"broadcast_state":6,"end_time":"2017-06-28 20:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330","price":0,"user_id":12657967,"user_name":"春心","user_type":1},"room_id":"mentor-12657967","subject":"男性在婚姻恋爱过程中的地位和角色"},{"audio_id":0,"authority":"loop_course_level2","begin_time":"2017-06-28 20:00:00","broadcast_id":54994,"broadcast_state":6,"end_time":"2017-06-28 21:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12858047/7323410438888511","price":0,"user_id":12858047,"user_name":"乔掌门","user_type":1},"room_id":"mentor-12858047","subject":"20，告别课：聊聊挽回"},{"audio_id":0,"authority":"loop_course_level3","begin_time":"2017-06-28 21:00:00","broadcast_id":57060,"broadcast_state":6,"end_time":"2017-06-28 22:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/7510750/21964117751775693","price":0,"user_id":7510750,"user_name":"可乐cola💉","user_type":1},"room_id":"mentor-7510750","subject":"女人的毒品之刺激与情绪"}],"expiry_date":"有效期: 2016.07.14-2017.07.14","head_teacher":{"authority":"loop_course_level1","avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","chatgroup_id":"194910249247834536","has_authority":true,"online_state":0,"price":0,"url":"","user_id":4635828,"user_name":"李大仁","user_type":1},"order_list":["head_teacher","special_mentor","calendar","answer_question","circulation","user_info"],"privilege":[{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://self_homework_list","logo":"http://morgoth-aman.puahome.com/my_privilege_homework","name":"作业本","show_type":""},{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://group_chat/194910249247834536","logo":"http://morgoth-aman.puahome.com/my_privilege_chatgroup","name":"群聊","show_type":""},{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://wing_man_bar/1","logo":"http://morgoth-aman.puahome.com/my_privilege_wangman","name":"坏吧","show_type":""},{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://wing_man_bar_publish/1/1","logo":"http://morgoth-aman.puahome.com/my_privilege_question","name":"提问","show_type":""}],"special":[{"audio_id":0,"begin_time":"2016-08-14 22:00:00","broadcast_id":29620,"broadcast_state":6,"end_time":"2016-08-14 23:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1},"room_id":"mentor-4635828","subject":"李大仁的特色课"},{"audio_id":0,"begin_time":"2016-08-05 22:00:00","broadcast_id":28729,"broadcast_state":6,"end_time":"2016-08-05 23:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1},"room_id":"mentor-4635828","subject":"李大仁的特色课"},{"audio_id":0,"begin_time":"2016-07-31 22:00:00","broadcast_id":28269,"broadcast_state":6,"end_time":"2016-07-31 23:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1},"room_id":"mentor-4635828","subject":"李大仁的特色课"}],"special_mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1},"test_url":"","upgrade_courses":[],"user_info":{"authority":"loop_course_level3","expiry_date":"有效期: 2016.07.14-2017.07.14","has_expire":true,"remaining_days":11},"video":{"href":"aman://vcourse_group","ios_hidden":0,"logo":"http://morgoth-aman.huainanhai.com/zixueshipin","name":""}}
     * rtn : 0
     */

    private DataBean data;
    private int rtn;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRtn() {
        return rtn;
    }

    public void setRtn(int rtn) {
        this.rtn = rtn;
    }

    public static class DataBean {
        /**
         * circulation : [{"audio_id":0,"authority":"loop_course_level1","begin_time":"2017-06-28 19:00:00","broadcast_id":57039,"broadcast_state":6,"end_time":"2017-06-28 20:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330","price":0,"user_id":12657967,"user_name":"春心","user_type":1},"room_id":"mentor-12657967","subject":"男性在婚姻恋爱过程中的地位和角色"},{"audio_id":0,"authority":"loop_course_level2","begin_time":"2017-06-28 20:00:00","broadcast_id":54994,"broadcast_state":6,"end_time":"2017-06-28 21:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12858047/7323410438888511","price":0,"user_id":12858047,"user_name":"乔掌门","user_type":1},"room_id":"mentor-12858047","subject":"20，告别课：聊聊挽回"},{"audio_id":0,"authority":"loop_course_level3","begin_time":"2017-06-28 21:00:00","broadcast_id":57060,"broadcast_state":6,"end_time":"2017-06-28 22:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/7510750/21964117751775693","price":0,"user_id":7510750,"user_name":"可乐cola💉","user_type":1},"room_id":"mentor-7510750","subject":"女人的毒品之刺激与情绪"}]
         * expiry_date : 有效期: 2016.07.14-2017.07.14
         * head_teacher : {"authority":"loop_course_level1","avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","chatgroup_id":"194910249247834536","has_authority":true,"online_state":0,"price":0,"url":"","user_id":4635828,"user_name":"李大仁","user_type":1}
         * order_list : ["head_teacher","special_mentor","calendar","answer_question","circulation","user_info"]
         * privilege : [{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://self_homework_list","logo":"http://morgoth-aman.puahome.com/my_privilege_homework","name":"作业本","show_type":""},{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://group_chat/194910249247834536","logo":"http://morgoth-aman.puahome.com/my_privilege_chatgroup","name":"群聊","show_type":""},{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://wing_man_bar/1","logo":"http://morgoth-aman.puahome.com/my_privilege_wangman","name":"坏吧","show_type":""},{"authority":"loop_course_level1","has_authority":true,"has_red":false,"href":"aman://wing_man_bar_publish/1/1","logo":"http://morgoth-aman.puahome.com/my_privilege_question","name":"提问","show_type":""}]
         * special : [{"audio_id":0,"begin_time":"2016-08-14 22:00:00","broadcast_id":29620,"broadcast_state":6,"end_time":"2016-08-14 23:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1},"room_id":"mentor-4635828","subject":"李大仁的特色课"},{"audio_id":0,"begin_time":"2016-08-05 22:00:00","broadcast_id":28729,"broadcast_state":6,"end_time":"2016-08-05 23:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1},"room_id":"mentor-4635828","subject":"李大仁的特色课"},{"audio_id":0,"begin_time":"2016-07-31 22:00:00","broadcast_id":28269,"broadcast_state":6,"end_time":"2016-07-31 23:00:00","has_authority":true,"has_homework":false,"has_learned":false,"mentor":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1},"room_id":"mentor-4635828","subject":"李大仁的特色课"}]
         * special_mentor : {"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1}
         * test_url :
         * upgrade_courses : []
         * user_info : {"authority":"loop_course_level3","expiry_date":"有效期: 2016.07.14-2017.07.14","has_expire":true,"remaining_days":11}
         * video : {"href":"aman://vcourse_group","ios_hidden":0,"logo":"http://morgoth-aman.huainanhai.com/zixueshipin","name":""}
         */

        private String expiry_date;
        private HeadTeacherBean head_teacher;
        private SpecialMentorBean special_mentor;
        private String test_url;
        private UserInfoBean user_info;
        private VideoBean video;
        private List<CirculationBean> circulation;
        private List<String> order_list;
        private List<PrivilegeBean> privilege;
        private List<SpecialBean> special;
        private List<?> upgrade_courses;

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }

        public HeadTeacherBean getHead_teacher() {
            return head_teacher;
        }

        public void setHead_teacher(HeadTeacherBean head_teacher) {
            this.head_teacher = head_teacher;
        }

        public SpecialMentorBean getSpecial_mentor() {
            return special_mentor;
        }

        public void setSpecial_mentor(SpecialMentorBean special_mentor) {
            this.special_mentor = special_mentor;
        }

        public String getTest_url() {
            return test_url;
        }

        public void setTest_url(String test_url) {
            this.test_url = test_url;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public List<CirculationBean> getCirculation() {
            return circulation;
        }

        public void setCirculation(List<CirculationBean> circulation) {
            this.circulation = circulation;
        }

        public List<String> getOrder_list() {
            return order_list;
        }

        public void setOrder_list(List<String> order_list) {
            this.order_list = order_list;
        }

        public List<PrivilegeBean> getPrivilege() {
            return privilege;
        }

        public void setPrivilege(List<PrivilegeBean> privilege) {
            this.privilege = privilege;
        }

        public List<SpecialBean> getSpecial() {
            return special;
        }

        public void setSpecial(List<SpecialBean> special) {
            this.special = special;
        }

        public List<?> getUpgrade_courses() {
            return upgrade_courses;
        }

        public void setUpgrade_courses(List<?> upgrade_courses) {
            this.upgrade_courses = upgrade_courses;
        }

        public static class HeadTeacherBean {
            /**
             * authority : loop_course_level1
             * avatar : http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486
             * chatgroup_id : 194910249247834536
             * has_authority : true
             * online_state : 0
             * price : 0
             * url :
             * user_id : 4635828
             * user_name : 李大仁
             * user_type : 1
             */

            private String authority;
            private String avatar;
            private String chatgroup_id;
            private boolean has_authority;
            private int online_state;
            private int price;
            private String url;
            private int user_id;
            private String user_name;
            private int user_type;

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getChatgroup_id() {
                return chatgroup_id;
            }

            public void setChatgroup_id(String chatgroup_id) {
                this.chatgroup_id = chatgroup_id;
            }

            public boolean isHas_authority() {
                return has_authority;
            }

            public void setHas_authority(boolean has_authority) {
                this.has_authority = has_authority;
            }

            public int getOnline_state() {
                return online_state;
            }

            public void setOnline_state(int online_state) {
                this.online_state = online_state;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }
        }

        public static class SpecialMentorBean {
            /**
             * avatar : http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486
             * price : 0
             * user_id : 4635828
             * user_name : 李大仁
             * user_type : 1
             */

            private String avatar;
            private int price;
            private int user_id;
            private String user_name;
            private int user_type;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }
        }

        public static class UserInfoBean {
            /**
             * authority : loop_course_level3
             * expiry_date : 有效期: 2016.07.14-2017.07.14
             * has_expire : true
             * remaining_days : 11
             */

            private String authority;
            private String expiry_date;
            private boolean has_expire;
            private int remaining_days;

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public String getExpiry_date() {
                return expiry_date;
            }

            public void setExpiry_date(String expiry_date) {
                this.expiry_date = expiry_date;
            }

            public boolean isHas_expire() {
                return has_expire;
            }

            public void setHas_expire(boolean has_expire) {
                this.has_expire = has_expire;
            }

            public int getRemaining_days() {
                return remaining_days;
            }

            public void setRemaining_days(int remaining_days) {
                this.remaining_days = remaining_days;
            }
        }

        public static class VideoBean {
            /**
             * href : aman://vcourse_group
             * ios_hidden : 0
             * logo : http://morgoth-aman.huainanhai.com/zixueshipin
             * name :
             */

            private String href;
            private int ios_hidden;
            private String logo;
            private String name;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getIos_hidden() {
                return ios_hidden;
            }

            public void setIos_hidden(int ios_hidden) {
                this.ios_hidden = ios_hidden;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class CirculationBean {
            /**
             * audio_id : 0
             * authority : loop_course_level1
             * begin_time : 2017-06-28 19:00:00
             * broadcast_id : 57039
             * broadcast_state : 6
             * end_time : 2017-06-28 20:00:00
             * has_authority : true
             * has_homework : false
             * has_learned : false
             * mentor : {"avatar":"http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330","price":0,"user_id":12657967,"user_name":"春心","user_type":1}
             * room_id : mentor-12657967
             * subject : 男性在婚姻恋爱过程中的地位和角色
             */

            private int audio_id;
            private String authority;
            private String begin_time;
            private int broadcast_id;
            private int broadcast_state;
            private String end_time;
            private boolean has_authority;
            private boolean has_homework;
            private boolean has_learned;
            private MentorBean mentor;
            private String room_id;
            private String subject;

            public int getAudio_id() {
                return audio_id;
            }

            public void setAudio_id(int audio_id) {
                this.audio_id = audio_id;
            }

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public String getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(String begin_time) {
                this.begin_time = begin_time;
            }

            public int getBroadcast_id() {
                return broadcast_id;
            }

            public void setBroadcast_id(int broadcast_id) {
                this.broadcast_id = broadcast_id;
            }

            public int getBroadcast_state() {
                return broadcast_state;
            }

            public void setBroadcast_state(int broadcast_state) {
                this.broadcast_state = broadcast_state;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public boolean isHas_authority() {
                return has_authority;
            }

            public void setHas_authority(boolean has_authority) {
                this.has_authority = has_authority;
            }

            public boolean isHas_homework() {
                return has_homework;
            }

            public void setHas_homework(boolean has_homework) {
                this.has_homework = has_homework;
            }

            public boolean isHas_learned() {
                return has_learned;
            }

            public void setHas_learned(boolean has_learned) {
                this.has_learned = has_learned;
            }

            public MentorBean getMentor() {
                return mentor;
            }

            public void setMentor(MentorBean mentor) {
                this.mentor = mentor;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public static class MentorBean {
                /**
                 * avatar : http://reuel-tolkien.huainanhai.com/avatar/12657967/44722747262165330
                 * price : 0
                 * user_id : 12657967
                 * user_name : 春心
                 * user_type : 1
                 */

                private String avatar;
                private int price;
                private int user_id;
                private String user_name;
                private int user_type;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public int getUser_type() {
                    return user_type;
                }

                public void setUser_type(int user_type) {
                    this.user_type = user_type;
                }
            }
        }

        public static class PrivilegeBean {
            /**
             * authority : loop_course_level1
             * has_authority : true
             * has_red : false
             * href : aman://self_homework_list
             * logo : http://morgoth-aman.puahome.com/my_privilege_homework
             * name : 作业本
             * show_type :
             */

            private String authority;
            private boolean has_authority;
            private boolean has_red;
            private String href;
            private String logo;
            private String name;
            private String show_type;

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public boolean isHas_authority() {
                return has_authority;
            }

            public void setHas_authority(boolean has_authority) {
                this.has_authority = has_authority;
            }

            public boolean isHas_red() {
                return has_red;
            }

            public void setHas_red(boolean has_red) {
                this.has_red = has_red;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShow_type() {
                return show_type;
            }

            public void setShow_type(String show_type) {
                this.show_type = show_type;
            }
        }

        public static class SpecialBean {
            /**
             * audio_id : 0
             * begin_time : 2016-08-14 22:00:00
             * broadcast_id : 29620
             * broadcast_state : 6
             * end_time : 2016-08-14 23:00:00
             * has_authority : true
             * has_homework : false
             * has_learned : false
             * mentor : {"avatar":"http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486","price":0,"user_id":4635828,"user_name":"李大仁","user_type":1}
             * room_id : mentor-4635828
             * subject : 李大仁的特色课
             */

            private int audio_id;
            private String begin_time;
            private int broadcast_id;
            private int broadcast_state;
            private String end_time;
            private boolean has_authority;
            private boolean has_homework;
            private boolean has_learned;
            private MentorBeanX mentor;
            private String room_id;
            private String subject;

            public int getAudio_id() {
                return audio_id;
            }

            public void setAudio_id(int audio_id) {
                this.audio_id = audio_id;
            }

            public String getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(String begin_time) {
                this.begin_time = begin_time;
            }

            public int getBroadcast_id() {
                return broadcast_id;
            }

            public void setBroadcast_id(int broadcast_id) {
                this.broadcast_id = broadcast_id;
            }

            public int getBroadcast_state() {
                return broadcast_state;
            }

            public void setBroadcast_state(int broadcast_state) {
                this.broadcast_state = broadcast_state;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public boolean isHas_authority() {
                return has_authority;
            }

            public void setHas_authority(boolean has_authority) {
                this.has_authority = has_authority;
            }

            public boolean isHas_homework() {
                return has_homework;
            }

            public void setHas_homework(boolean has_homework) {
                this.has_homework = has_homework;
            }

            public boolean isHas_learned() {
                return has_learned;
            }

            public void setHas_learned(boolean has_learned) {
                this.has_learned = has_learned;
            }

            public MentorBeanX getMentor() {
                return mentor;
            }

            public void setMentor(MentorBeanX mentor) {
                this.mentor = mentor;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public static class MentorBeanX {
                /**
                 * avatar : http://reuel-tolkien.huainanhai.com/avatar/4635828/5931257424682486
                 * price : 0
                 * user_id : 4635828
                 * user_name : 李大仁
                 * user_type : 1
                 */

                private String avatar;
                private int price;
                private int user_id;
                private String user_name;
                private int user_type;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public int getUser_type() {
                    return user_type;
                }

                public void setUser_type(int user_type) {
                    this.user_type = user_type;
                }
            }
        }
    }
}
