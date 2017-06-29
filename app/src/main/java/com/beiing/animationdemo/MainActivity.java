package com.beiing.animationdemo;

import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beiing.animationdemo.transitions.AnimationActivity;
import com.beiing.animationdemo.transitions.ImageListActivity;
import com.beiing.animationdemo.transitions.TransitionActivity;
import com.beiing.animationdemo.vipager_transformer.ViewpagerActivity;

/**
 * 可以测试使用contentprovider获取另一个应用中assets文件夹下的图片
 *  对应：CostomView
 */
public class MainActivity extends AppCompatActivity {
    ImageView ivAsset;
    Button shareBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivAsset = (ImageView) findViewById(R.id.iv_asset);
        shareBtn2 = (Button) findViewById(R.id.share_btn2);
        Log.e("====", "wtfk");
    }

    public void Test1Activity(View view) {
        startActivity(new Intent(this, Test1Activity.class));
    }

    public void TestBehavior(View view) {
        startActivity(new Intent(this, ScrollingActivity.class));
    }

    public void getShareFile(View view) {
        ContentResolver resolver = this.getContentResolver();
        // Uri uri =
        // Uri.parse("content://com.zgl.myprovider/file/phoneModel.xml");
        Uri uri = Uri.parse("content://com.beiing.customview.assetsprovider");
        String filename = "index0.jpg";
        try {
            //--------------------------------
            Bundle extras = new Bundle();
            extras.putString("filename", filename);
            Bundle retBundle = resolver.call(uri, "getIn", null, extras);
            byte[] b = retBundle.getByteArray("myBundle");
            ivAsset.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TestTransition(View view) {
//        startActivity(new Intent(this, TransitionActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        //共享元素-单个 Pair.create(iv1,"myiv"), create(textView,"mytv")
//        startActivity(new Intent(this, TransitionActivity.class), ActivityOptions.makeSceneTransitionAnimation(this,view,"share_btn").toBundle());

        //共享元素有多个
        Pair pair1 = Pair.create(view,"share_btn");//, create(shareBtn2, "share_btn2");
        Pair pair2 = Pair.create(shareBtn2,"share_btn2");
        startActivity(new Intent(this, TransitionActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this, pair1, pair2).toBundle());

    }


    public void animateActivity(View view) {
        startActivity(new Intent(this, AnimationActivity.class));
    }

    public void imageList(View view) {
        startActivity(new Intent(this, ImageListActivity.class));
    }

    public void viewpagerActivity(View view) {
        startActivity(new Intent(this, ViewpagerActivity.class));
    }
}
