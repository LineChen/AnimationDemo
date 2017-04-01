package com.beiing.animationdemo.transitions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beiing.animationdemo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {

    RecyclerView rvImageList;
    List<String> imageUrls;
    private int selectPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        imageUrls = Data.imageUrls;
        rvImageList = (RecyclerView) findViewById(R.id.rv_image_list);
        rvImageList.setLayoutManager(new LinearLayoutManager(this));
        rvImageList.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new Holder(LayoutInflater.from(ImageListActivity.this).inflate(R.layout.item_image, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.image_view);
                Glide.with(ImageListActivity.this).load(imageUrls.get(position)).into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ImageListActivity.this, ImageDetailActivity.class);
                        intent.putExtra("select_position", position);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ImageListActivity.this,
                                        v, getResources().getString(R.string.image_transition_name)).toBundle());
                        selectPosition = position;
                    }
                });
            }

            @Override
            public int getItemCount() {
                return imageUrls.size();
            }
        });
    }

    /**
     * 当您通过活动转换启动的活动通过返回的活动转换显示此活动时调用，为您提供resultCode及其中的任何其他数据。
     * 只有当活动设置了RESULT_CANCELED以外的结果代码并且它支持使用Window.FEATURE_ACTIVITY_TRANSITIONS进行活动转换时，才会调用此方法。
     * 此功能的目的是让被调用的活动发送关于其状态的提示，以便此底层的Activity可以准备暴露。
     * 对此方法的调用并不能保证被调用的Activity已经或将要很快退出。 它只表示它将暴露此Activity的窗口，并且它有一些数据要通过来准备它。
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if(resultCode == RESULT_OK){
            int currentPos = data.getIntExtra("current_position", 0);
            imageUrls.set(selectPosition, imageUrls.get(currentPos));
            rvImageList.getAdapter().notifyItemChanged(selectPosition);
        }
    }

    private static class Holder extends RecyclerView.ViewHolder{
        Holder(View itemView) {
            super(itemView);
        }
    }

}
