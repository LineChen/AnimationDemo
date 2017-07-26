package com.beiing.animationdemo.valueanimator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.beiing.animationdemo.R;

public class AnimatorMainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_main);
        textView1 = (TextView) findViewById(R.id.text_view1);
        textView1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_view1:
                common();
                break;
        }
    }

    public void common(){
        ValueAnimator floatAnim = ValueAnimator.ofFloat(0, 10).setDuration(200);
        ValueAnimator intAnim = ValueAnimator.ofInt(0, 10, 5, 20).setDuration(200);

//        ValueAnimator argbAnim = ValueAnimator.ofArgb(Color.argb(255, 255,0, 0), Color.argb(255, 0, 0, 255), Color.argb(255, 0, 255, 0)).setDuration(2000);
//        argbAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int animatedValue = (int) animation.getAnimatedValue();
//                textView1.setBackgroundColor(animatedValue);
//            }
//        });
//        argbAnim.start();

        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",1.0f,0.2f,1.0f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1.0f,0.2f,1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",1.0f,0.2f,1.0f);
        ObjectAnimator  holerAnim = ObjectAnimator .ofPropertyValuesHolder(textView1, alpha, scaleX, scaleY).setDuration(5000);
        holerAnim.start();

    }

    public void common2(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(getWindow().getDecorView(), "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();

        textView1.animate().alpha(0.2f).rotation(270).setDuration(2000).start();

    }


}
