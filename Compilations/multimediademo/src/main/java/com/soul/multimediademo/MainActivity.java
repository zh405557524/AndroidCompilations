package com.soul.multimediademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * 加载大图片
 */
public class MainActivity extends AppCompatActivity {

    private ImageView mImage;
    private int mScreenWidth;
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //           1 判断手机的分辨率
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        mScreenWidth = display.getWidth();
        mScreenHeight = display.getHeight();
        mImage = (ImageView) findViewById(R.id.iv_image);
    }

    public void loadImage(View view) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        //仅仅读取图片的头信息，不加载图片的内容，不消耗内存
        opt.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.bg, opt);

        //           2 获取图片的分辨率

        int picWidth = opt.outWidth;
        int picHeight = opt.outHeight;

        System.out.println("屏幕宽高:" + mScreenWidth + "----" + mScreenHeight);
        System.out.println("图片宽高:" + picWidth + "----" + picHeight);
        //pic 2560*1600  screen 1080*1794

        //计算一个缩放比例
        int inSampleSize = 1;
        int dx = picWidth / mScreenWidth;
        int dy = picHeight / mScreenHeight;
        if (dx > dy && dx >= 1) {
            inSampleSize = dx;
        }
        if (dy > dx && dy >= 1) {
            inSampleSize = dy;
        }
        opt.inSampleSize = inSampleSize;
        //           3 根据比例进行缩放显示

        opt.inJustDecodeBounds = false;

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.bg, opt);

        mImage.setImageBitmap(bitmap1);

    }
}
