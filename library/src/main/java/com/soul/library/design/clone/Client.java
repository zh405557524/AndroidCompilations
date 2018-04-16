package com.soul.library.design.clone;

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:AndroidCompilations.git
 * @创建时间：2018/2/27 9:50
 */

public class Client {


    public static void main(String[] arg) {

        WordDocument wordDocument = new WordDocument();
        wordDocument.setText("这是一篇文章");
        wordDocument.addImage("图片1");
        wordDocument.addImage("图片2");
        wordDocument.addImage("图片3");

        try {
            WordDocument document = wordDocument.clone();
            document.setText("这是修改过的文本");
            document.addImage("哈哈哈.jpg");
            document.showDocument();
            wordDocument.showDocument();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
