package com.soul.androidcompilptions.rxandretrofi.bean;

import java.util.List;

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/7/10 23:31
 */

public class VideoBean {


    /**
     * data : {"head":{"avatar":"http://reuel-tolkien.huainanhai.com/avatar/99/1200004","course_name":"搭讪技巧","is_pay":1,"learning_progress":"4%","mentor_user_name":"坏男孩官方","photo_image":"http://morgoth-aman.huainanhai.com/course/506/8160894","section_count":12,"section_level":"","student_count":1585,"teach_id":280,"teach_type":"video"},"list":[{"chapter":{"clip_length":0,"duration":0,"is_chapter":1,"is_free":0,"progress_state":0,"section_id":1464,"section_name":"第1章 搭讪的心态分析","seq_id":""},"sections":[{"clip_length":30,"duration":384,"is_chapter":0,"is_free":1,"progress_state":1,"section_id":1465,"section_name":"搭讪的基本概念","seq_id":"01"},{"clip_length":30,"duration":843,"is_chapter":0,"is_free":1,"progress_state":0,"section_id":1466,"section_name":"搭讪时的心态","seq_id":"02"},{"clip_length":30,"duration":1091,"is_chapter":0,"is_free":1,"progress_state":0,"section_id":1467,"section_name":"搭讪时易出的问题","seq_id":"03"},{"clip_length":30,"duration":665,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1468,"section_name":"搭讪时女性IOI表现","seq_id":"04"},{"clip_length":30,"duration":436,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1469,"section_name":"搭讪的后续","seq_id":"05"}]},{"chapter":{"clip_length":0,"duration":0,"is_chapter":1,"is_free":0,"progress_state":0,"section_id":1470,"section_name":"第2章 搭讪时具备的技巧","seq_id":""},"sections":[{"clip_length":30,"duration":374,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1471,"section_name":"搭讪的开场白","seq_id":"06"},{"clip_length":30,"duration":530,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1472,"section_name":"搭讪时的服装选择","seq_id":"07"},{"clip_length":30,"duration":818,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1473,"section_name":"搭讪时的肢体语言","seq_id":"08"},{"clip_length":30,"duration":812,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1474,"section_name":"全环境搭讪","seq_id":"09"},{"clip_length":30,"duration":545,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1475,"section_name":"搭讪后如何推进","seq_id":"10"},{"clip_length":30,"duration":636,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1476,"section_name":"搭讪经典惯例","seq_id":"11"},{"clip_length":30,"duration":514,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1477,"section_name":"搭讪如何收号","seq_id":"12"}]}],"recommend_list":[]}
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
        return "VideoBean{" +
                "data=" + data +
                ", rtn=" + rtn +
                '}';
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "head=" + head +
                    ", list=" + list +
                    ", recommend_list=" + recommend_list +
                    '}';
        }

        /**
         * head : {"avatar":"http://reuel-tolkien.huainanhai.com/avatar/99/1200004","course_name":"搭讪技巧","is_pay":1,"learning_progress":"4%","mentor_user_name":"坏男孩官方","photo_image":"http://morgoth-aman.huainanhai.com/course/506/8160894","section_count":12,"section_level":"","student_count":1585,"teach_id":280,"teach_type":"video"}
         * list : [{"chapter":{"clip_length":0,"duration":0,"is_chapter":1,"is_free":0,"progress_state":0,"section_id":1464,"section_name":"第1章 搭讪的心态分析","seq_id":""},"sections":[{"clip_length":30,"duration":384,"is_chapter":0,"is_free":1,"progress_state":1,"section_id":1465,"section_name":"搭讪的基本概念","seq_id":"01"},{"clip_length":30,"duration":843,"is_chapter":0,"is_free":1,"progress_state":0,"section_id":1466,"section_name":"搭讪时的心态","seq_id":"02"},{"clip_length":30,"duration":1091,"is_chapter":0,"is_free":1,"progress_state":0,"section_id":1467,"section_name":"搭讪时易出的问题","seq_id":"03"},{"clip_length":30,"duration":665,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1468,"section_name":"搭讪时女性IOI表现","seq_id":"04"},{"clip_length":30,"duration":436,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1469,"section_name":"搭讪的后续","seq_id":"05"}]},{"chapter":{"clip_length":0,"duration":0,"is_chapter":1,"is_free":0,"progress_state":0,"section_id":1470,"section_name":"第2章 搭讪时具备的技巧","seq_id":""},"sections":[{"clip_length":30,"duration":374,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1471,"section_name":"搭讪的开场白","seq_id":"06"},{"clip_length":30,"duration":530,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1472,"section_name":"搭讪时的服装选择","seq_id":"07"},{"clip_length":30,"duration":818,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1473,"section_name":"搭讪时的肢体语言","seq_id":"08"},{"clip_length":30,"duration":812,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1474,"section_name":"全环境搭讪","seq_id":"09"},{"clip_length":30,"duration":545,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1475,"section_name":"搭讪后如何推进","seq_id":"10"},{"clip_length":30,"duration":636,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1476,"section_name":"搭讪经典惯例","seq_id":"11"},{"clip_length":30,"duration":514,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1477,"section_name":"搭讪如何收号","seq_id":"12"}]}]
         * recommend_list : []
         */


        private HeadBean head;
        private List<ListBean> list;
        private List<?> recommend_list;

        public HeadBean getHead() { return head;}

        public void setHead(HeadBean head) { this.head = head;}

        public List<ListBean> getList() { return list;}

        public void setList(List<ListBean> list) { this.list = list;}

        public List<?> getRecommend_list() { return recommend_list;}

        public void setRecommend_list(List<?> recommend_list) { this.recommend_list = recommend_list;}

        public static class HeadBean {
            /**
             * avatar : http://reuel-tolkien.huainanhai.com/avatar/99/1200004
             * course_name : 搭讪技巧
             * is_pay : 1
             * learning_progress : 4%
             * mentor_user_name : 坏男孩官方
             * photo_image : http://morgoth-aman.huainanhai.com/course/506/8160894
             * section_count : 12
             * section_level :
             * student_count : 1585
             * teach_id : 280
             * teach_type : video
             */

            private String avatar;
            private String course_name;
            private int is_pay;
            private String learning_progress;
            private String mentor_user_name;
            private String photo_image;
            private int section_count;
            private String section_level;
            private int student_count;
            private int teach_id;
            private String teach_type;

            public String getAvatar() { return avatar;}

            public void setAvatar(String avatar) { this.avatar = avatar;}

            public String getCourse_name() { return course_name;}

            public void setCourse_name(String course_name) { this.course_name = course_name;}

            public int getIs_pay() { return is_pay;}

            public void setIs_pay(int is_pay) { this.is_pay = is_pay;}

            public String getLearning_progress() { return learning_progress;}

            public void setLearning_progress(String learning_progress) { this.learning_progress = learning_progress;}

            public String getMentor_user_name() { return mentor_user_name;}

            public void setMentor_user_name(String mentor_user_name) { this.mentor_user_name = mentor_user_name;}

            public String getPhoto_image() { return photo_image;}

            public void setPhoto_image(String photo_image) { this.photo_image = photo_image;}

            public int getSection_count() { return section_count;}

            public void setSection_count(int section_count) { this.section_count = section_count;}

            public String getSection_level() { return section_level;}

            public void setSection_level(String section_level) { this.section_level = section_level;}

            public int getStudent_count() { return student_count;}

            public void setStudent_count(int student_count) { this.student_count = student_count;}

            public int getTeach_id() { return teach_id;}

            public void setTeach_id(int teach_id) { this.teach_id = teach_id;}

            public String getTeach_type() { return teach_type;}

            public void setTeach_type(String teach_type) { this.teach_type = teach_type;}

            @Override
            public String toString() {
                return "HeadBean{" +
                        "avatar='" + avatar + '\'' +
                        ", course_name='" + course_name + '\'' +
                        ", is_pay=" + is_pay +
                        ", learning_progress='" + learning_progress + '\'' +
                        ", mentor_user_name='" + mentor_user_name + '\'' +
                        ", photo_image='" + photo_image + '\'' +
                        ", section_count=" + section_count +
                        ", section_level='" + section_level + '\'' +
                        ", student_count=" + student_count +
                        ", teach_id=" + teach_id +
                        ", teach_type='" + teach_type + '\'' +
                        '}';
            }
        }

        public static class ListBean {
            /**
             * chapter : {"clip_length":0,"duration":0,"is_chapter":1,"is_free":0,"progress_state":0,"section_id":1464,"section_name":"第1章 搭讪的心态分析","seq_id":""}
             * sections : [{"clip_length":30,"duration":384,"is_chapter":0,"is_free":1,"progress_state":1,"section_id":1465,"section_name":"搭讪的基本概念","seq_id":"01"},{"clip_length":30,"duration":843,"is_chapter":0,"is_free":1,"progress_state":0,"section_id":1466,"section_name":"搭讪时的心态","seq_id":"02"},{"clip_length":30,"duration":1091,"is_chapter":0,"is_free":1,"progress_state":0,"section_id":1467,"section_name":"搭讪时易出的问题","seq_id":"03"},{"clip_length":30,"duration":665,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1468,"section_name":"搭讪时女性IOI表现","seq_id":"04"},{"clip_length":30,"duration":436,"is_chapter":0,"is_free":0,"progress_state":0,"section_id":1469,"section_name":"搭讪的后续","seq_id":"05"}]
             */

            private ChapterBean chapter;
            private List<SectionsBean> sections;

            public ChapterBean getChapter() { return chapter;}

            public void setChapter(ChapterBean chapter) { this.chapter = chapter;}

            public List<SectionsBean> getSections() { return sections;}

            public void setSections(List<SectionsBean> sections) { this.sections = sections;}

            public static class ChapterBean {
                /**
                 * clip_length : 0
                 * duration : 0
                 * is_chapter : 1
                 * is_free : 0
                 * progress_state : 0
                 * section_id : 1464
                 * section_name : 第1章 搭讪的心态分析
                 * seq_id :
                 */

                private int clip_length;
                private int duration;
                private int is_chapter;
                private int is_free;
                private int progress_state;
                private int section_id;
                private String section_name;
                private String seq_id;

                public int getClip_length() { return clip_length;}

                public void setClip_length(int clip_length) { this.clip_length = clip_length;}

                public int getDuration() { return duration;}

                public void setDuration(int duration) { this.duration = duration;}

                public int getIs_chapter() { return is_chapter;}

                public void setIs_chapter(int is_chapter) { this.is_chapter = is_chapter;}

                public int getIs_free() { return is_free;}

                public void setIs_free(int is_free) { this.is_free = is_free;}

                public int getProgress_state() { return progress_state;}

                public void setProgress_state(int progress_state) { this.progress_state = progress_state;}

                public int getSection_id() { return section_id;}

                public void setSection_id(int section_id) { this.section_id = section_id;}

                public String getSection_name() { return section_name;}

                public void setSection_name(String section_name) { this.section_name = section_name;}

                public String getSeq_id() { return seq_id;}

                public void setSeq_id(String seq_id) { this.seq_id = seq_id;}

                @Override
                public String toString() {
                    return "ChapterBean{" +
                            "clip_length=" + clip_length +
                            ", duration=" + duration +
                            ", is_chapter=" + is_chapter +
                            ", is_free=" + is_free +
                            ", progress_state=" + progress_state +
                            ", section_id=" + section_id +
                            ", section_name='" + section_name + '\'' +
                            ", seq_id='" + seq_id + '\'' +
                            '}';
                }
            }

            public static class SectionsBean {
                /**
                 * clip_length : 30
                 * duration : 384
                 * is_chapter : 0
                 * is_free : 1
                 * progress_state : 1
                 * section_id : 1465
                 * section_name : 搭讪的基本概念
                 * seq_id : 01
                 */

                private int clip_length;
                private int duration;
                private int is_chapter;
                private int is_free;
                private int progress_state;
                private int section_id;
                private String section_name;
                private String seq_id;

                public int getClip_length() { return clip_length;}

                public void setClip_length(int clip_length) { this.clip_length = clip_length;}

                public int getDuration() { return duration;}

                public void setDuration(int duration) { this.duration = duration;}

                public int getIs_chapter() { return is_chapter;}

                public void setIs_chapter(int is_chapter) { this.is_chapter = is_chapter;}

                public int getIs_free() { return is_free;}

                public void setIs_free(int is_free) { this.is_free = is_free;}

                public int getProgress_state() { return progress_state;}

                public void setProgress_state(int progress_state) { this.progress_state = progress_state;}

                public int getSection_id() { return section_id;}

                public void setSection_id(int section_id) { this.section_id = section_id;}

                public String getSection_name() { return section_name;}

                public void setSection_name(String section_name) { this.section_name = section_name;}

                public String getSeq_id() { return seq_id;}

                public void setSeq_id(String seq_id) { this.seq_id = seq_id;}

                @Override
                public String toString() {
                    return "SectionsBean{" +
                            "clip_length=" + clip_length +
                            ", duration=" + duration +
                            ", is_chapter=" + is_chapter +
                            ", is_free=" + is_free +
                            ", progress_state=" + progress_state +
                            ", section_id=" + section_id +
                            ", section_name='" + section_name + '\'' +
                            ", seq_id='" + seq_id + '\'' +
                            '}';
                }
            }
        }
    }

}
