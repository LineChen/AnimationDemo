package com.beiing.animationdemo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView ivAsset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivAsset = (ImageView) findViewById(R.id.iv_asset);
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
}
