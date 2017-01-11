package com.soul.library.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.soul.library.R;


/**
 * * @author soul
 *
 * @项目名:Compilations
 * @作者：祝明
 * @描述：评星
 * @创建时间：2016/12/28 23:59
 */

public class RatingStartView extends View {

    private Resources mRes;
    private Bitmap mBottomRating;
    private Bitmap mSelectedRating;
    private Paint mPaint;
    //total number of star
    private int mNumStart;
    //review of the star
    private float mRating;
    //each start of the pitch
    private int mSpacing;


    public RatingStartView(Context context) {
        this(context, null);
    }

    public RatingStartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingStartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRes = context.getResources();
        init(context);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RatingStartView,
                defStyleAttr, 0);
        mNumStart = (int) a.getFloat(R.styleable.RatingStartView_numStars, 5);
        mRating = a.getFloat(R.styleable.RatingStartView_rating, 2);
        mSpacing = a.getDimensionPixelSize(R.styleable.RatingStartView_spacing, 2);
        mPaint = new Paint();
        a.recycle();
        
    }

    private void init(Context context) {

        //init bitmap
        //At the bottom of the Bitmap
        mBottomRating = BitmapFactory.decodeResource(mRes, R.drawable.drawer_icon_star_u);
        mSelectedRating = BitmapFactory.decodeResource(mRes, R.drawable.drawer_icon_star_s);

    }

    /**
     * EXACTLY specific number
     * AT_MOST package content
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int ratingWith = mNumStart * mBottomRating.getWidth() + (mNumStart - 1) * mSpacing;
            int desired = ratingWith + getPaddingLeft() + getPaddingRight();
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int ratingHeight = mBottomRating.getHeight();
            int desired = ratingHeight + getPaddingTop() + getPaddingBottom();
            height = desired;
        }
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawNumStart(canvas);
        drawRating(canvas);

    }

    /**
     * the total number of draw a start rating
     *
     * @param canvas
     */
    private void drawNumStart(Canvas canvas) {

        if (mBottomRating != null) {
            for (int x = 0; x < mNumStart; x++) {
                canvas.drawBitmap(mBottomRating, x * (mBottomRating.getWidth() + mSpacing), 0, mPaint);
            }
        }

    }

    /**
     * draw the selected evaluation of starts
     *
     * @param canvas
     */
    private void drawRating(Canvas canvas) {

        if (mSelectedRating != null) {
            if (mRating > mNumStart) {
                mRating = mNumStart;
            }
            for (int x = 0; x < mRating; x++) {
                if (mRating - x < 1 && mRating - x > 0) {
                    Rect src = new Rect(0, 0, (int) ((mSelectedRating.getWidth() * (mRating - x))),
                            mSelectedRating.getHeight());
                    Rect dst = new Rect(x * (mSelectedRating.getWidth() + mSpacing), 0,
                            (int) (mRating * mSelectedRating.getWidth() + x * mSpacing),
                            mSelectedRating.getHeight());
                    canvas.drawBitmap(mSelectedRating, src, dst, mPaint);
                } else {
                    canvas.drawBitmap(mSelectedRating, x * (mSelectedRating.getWidth() + mSpacing),
                            0, mPaint);
                }
            }
        }

    }

    /**
     * set a review of the star
     *
     * @param rating
     */
    public void setRating(float rating) {

        mRating = rating;
        invalidate();

    }

    /**
     * set the total number of star rating
     *
     * @param numStart
     */
    public void setNumStart(int numStart) {

        mNumStart = numStart;
        invalidate();

    }


}
