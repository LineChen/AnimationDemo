package com.beiing.animationdemo.valueanimator;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by linechen on 2017/7/26.<br/>
 * 描述：自定义TypeEvaluator
 * </br>
 */

public class PointEvaluator implements TypeEvaluator {

    private static final String TAG = "PointEvaluator";
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Log.e(TAG, "fraction = " + fraction);
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        int x = (int) (startPoint.x + fraction * (endPoint.x - startPoint.x));
        int y = (int) (startPoint.y + fraction * (endPoint.y - startPoint.y));
        Point point = new Point(x, y);
        return point;
    }
}
