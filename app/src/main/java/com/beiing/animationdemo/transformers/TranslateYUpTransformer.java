package com.beiing.animationdemo.transformers;

import android.view.View;

/**
 * Created by chenliu on 2017/4/20.<br/>
 * 描述：从下往上移动
 * </br>
 */

public class TranslateYUpTransformer extends ABaseTransformer {
    @Override
    protected void onTransform(View page, float position) {
        page.setTranslationX(page.getWidth() * -position);
        page.setTranslationY(page.getHeight() * position);
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }

}
