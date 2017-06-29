package com.beiing.animationdemo.transformers;

import android.view.View;

/**
 * Created by chenliu on 2017/4/20.<br/>
 * 描述：沿着x轴3d翻转
 * </br>
 */

public class CubeOutXTransformer extends ABaseTransformer {

    /**
     * 	 * A页的position变化就是( 0, -1]
     B页的position变化就是[ 1 , 0 ]
     * @param page
     *            Apply the transformation to this page
     * @param position
     *            Position of page relative to the current front-and-center position of the pager. 0 is front and
     */
    @Override
    protected void onTransform(View page, float position) {
        page.setTranslationX(page.getWidth() * -position);
        page.setTranslationY(page.getHeight() * position);
        page.setPivotX(page.getWidth() * 0.5f);
        page.setPivotY(position < 0 ? page.getHeight() : 0);
        page.setRotationX(-90f * position);
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }


}
