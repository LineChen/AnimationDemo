package com.beiing.animationdemo.transitions;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beiing.animationdemo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView shareImageView;
    ViewPager viewPager;
    int curPosition;//RecyclerView

    List<View> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        initImages();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        shareImageView = (ImageView) findViewById(R.id.share_image);
        int selectPosition = getIntent().getIntExtra("select_position", 0);

        //添加动画监听
        getWindow().getEnterTransition().addListener(new CustomTransitionListener(){
            @Override
            public void onTransitionEnd(Transition transition) {
                Log.e("====", "onTransitionEnd");
            }
        });

        shareImageView.setOnClickListener(this);
        viewPager.setOnClickListener(this);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Log.e("====", "instantiateItem:" + position);
                container.addView(imageViewList.get(position));
                return imageViewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                Log.e("====", "destroyItem:" + position);
                container.removeView(imageViewList.get(position));
            }

            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        viewPager.setCurrentItem(selectPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initImages() {
        imageViewList = new ArrayList<>();
        for (String url : Data.imageUrls) {
            View view = LayoutInflater.from(ImageDetailActivity.this).inflate(R.layout.item_image, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
            Glide.with(ImageDetailActivity.this).load(url).into(imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            imageViewList.add(view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_image:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("current_position", viewPager.getCurrentItem());
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    private static class CustomTransitionListener implements  Transition.TransitionListener{

        @Override
        public void onTransitionStart(Transition transition) {

        }

        @Override
        public void onTransitionEnd(Transition transition) {

        }

        @Override
        public void onTransitionCancel(Transition transition) {

        }

        @Override
        public void onTransitionPause(Transition transition) {

        }

        @Override
        public void onTransitionResume(Transition transition) {

        }
    }
}
