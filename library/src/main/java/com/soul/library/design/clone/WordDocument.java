package com.soul.library.design.clone;

import java.util.ArrayList;

/**
 * @描述：文档类型，扮演的事ConcretePrototype角色，而cloneable是代表prototype角色
 * @作者：祝明
 * @项目名:AndroidCompilations.git
 * @创建时间：2018/2/26 9:31
 */

public class WordDocument implements Cloneable {

    //文本
    private String mText;

    //图片名列表
    private ArrayList<String> mImages = new ArrayList<>();

    public WordDocument() {
        System.out.println("----WordDocument构造函数----");
    }

    @Override
    protected WordDocument clone() throws CloneNotSupportedException {

        try {
            WordDocument doc = (WordDocument) super.clone();
            doc.mText = this.mText;
            doc.mImages = (ArrayList<String>) this.mImages.clone();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void setImages(ArrayList<String> images) {
        mImages = images;
    }

    public void addImage(String img) {
        this.mImages.add(img);
    }

    public void showDocument() {

        System.out.println("text:" + mText);
        System.out.println("images" + mImages);
        System.out.println("--------------------------------");
    }

}
