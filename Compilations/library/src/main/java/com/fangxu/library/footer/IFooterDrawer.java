package com.fangxu.library.footer;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IFooterDrawer {
    void drawFooter(Canvas canvas, float left, float top, float right, float bottom);
    void updateDragState(int dragState);
    boolean shouldTriggerEvent(float dragDistance);
}
