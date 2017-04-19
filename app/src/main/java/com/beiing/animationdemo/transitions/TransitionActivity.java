package com.beiing.animationdemo.transitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiing.animationdemo.R;

public class TransitionActivity extends AppCompatActivity {
    Scene mAScene;
    Scene mAnotherScene;

    LinearLayout mSceneRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition fadeTransition = TransitionInflater.from(this).inflateTransition(R.transition.transition_activity);
        fadeTransition.setDuration(300);
//        getWindow().setEnterTransition(fadeTransition);
//        getWindow().setExitTransition(new Explode().setDuration(2000));
        setContentView(R.layout.activity_transition);
        // Create the scene root for the scenes in this app
        mSceneRoot = (LinearLayout) findViewById(R.id.scene_root);

        // Create the scenes
        mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.a_scene, this);
        mAnotherScene = Scene.getSceneForLayout(mSceneRoot, R.layout.another_scene, this);
    }

    public void startTransition(View view) {
        Transition fadeTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade_transition);
        //这样会在一瞬间完成，在xml文件中设置duration无效
        TransitionManager.go(mAnotherScene, fadeTransition);

        TextView textView = new TextView(this);
        textView.setText("new text");
        TransitionManager.beginDelayedTransition(mSceneRoot, fadeTransition);
    // Add the new TextView to the view hierarchy
        mSceneRoot.addView(textView);

    }
}
