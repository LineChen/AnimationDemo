package com.beiing.animationdemo.vipager_transformer;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.beiing.animationdemo.R;
import com.beiing.animationdemo.transformers.ABaseTransformer;
import com.beiing.animationdemo.transitions.Data;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ViewPager viewPager;
    List<View> imageViewList;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.viewpager_transforms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        initImages();

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViewList.get(position));
                return imageViewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
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


    }

    private void initImages() {
        imageViewList = new ArrayList<>();
        for (String url : Data.imageUrls) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_image, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
            Glide.with(this).load(url).into(imageView);
            imageViewList.add(view);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            ABaseTransformer transformer = getTransformer(getResources().getStringArray(R.array.viewpager_transforms)[position]);
            viewPager.setPageTransformer(true, transformer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public  ABaseTransformer getTransformer(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> claz = Class.forName("com.beiing.animationdemo.transformers." + className);
        Object obj = claz.newInstance();
        ABaseTransformer transformer = (ABaseTransformer)obj;
        return transformer;
    }
}
