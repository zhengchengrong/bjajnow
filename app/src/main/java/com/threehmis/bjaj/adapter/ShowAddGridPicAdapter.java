package com.threehmis.bjaj.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.respon.NameKeyRsp;
import com.threehmis.bjaj.module.more.CheckQuestionFragment;
import com.threehmis.bjaj.utils.VHUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by D on 16/7/18.
 */
public class ShowAddGridPicAdapter extends BaseAdapter {

    List<NameKeyRsp> images = new ArrayList<>();

    NameKeyRsp nameKeyRsp = new NameKeyRsp();

    public ShowAddGridPicAdapter() {
        images.add(nameKeyRsp);
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public NameKeyRsp getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_show_addgrid_picture_item, null);


        ImageView img = VHUtil.ViewHolder.get(convertView, R.id.img);
        ImageView type = VHUtil.ViewHolder.get(convertView, R.id.type);
        ImageView deletimg = VHUtil.ViewHolder.get(convertView, R.id.deletimg);
        type.setVisibility(View.GONE);

        deletimg.setOnClickListener(new ClickListener(position));

        if (position == images.size() - 1) {
            deletimg.setVisibility(View.GONE);
//            img.setBackgroundResource(R.drawable.woxiu_add_pic);
            Glide.with(AndroidApplication.getInstance().getApplicationContext()).load(R.drawable.woxiu_add_pic).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        } else {
            deletimg.setVisibility(View.GONE);//这里暂时隐藏

            switch (getItem(position).name) {

                case "video":
                    Glide.with(AndroidApplication.getInstance().getApplicationContext()).load(RetrofitFactory.BASE_URL + "question/lookPhoto/" + getItem(position).key).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .override(800, 800).centerCrop().skipMemoryCache( true ).into(img);
                    type.setVisibility(View.GONE);
                    type.setVisibility(View.VISIBLE);
                    type.setImageResource(R.drawable.play_btn);
                    break;
                case "audio":
                    Glide.with(AndroidApplication.getInstance().getApplicationContext()).load(R.drawable.t_pic).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache( true ).into(img);
                    type.setVisibility(View.VISIBLE);
                    type.setImageResource(R.drawable.vol);
                    break;
                case "photo":
                    Log.d("CD","=s======="+getItem(position).key);
                    Glide.with(AndroidApplication.getInstance().getApplicationContext()).load(RetrofitFactory.BASE_URL + "question/lookPhoto/" + getItem(position).key).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache( true ).override(800, 800).centerCrop().into(img);
                    type.setVisibility(View.GONE);
                    break;


            }

        }


        return convertView;
    }

    public void notifyData(NameKeyRsp url) {

        images.add(images.size() - 1, url);

        notifyDataSetChanged();
    }
    public void adddata(NameKeyRsp url) {

        images.add(images.size() - 1, url);
    }

    public void clear() {
        images.clear();
        images.add(nameKeyRsp);
        notifyDataSetChanged();
    }

    private class ClickListener implements View.OnClickListener {

        int position;

        public ClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            images.remove(position);
            CheckQuestionFragment.pathimages.remove(position);
            notifyDataSetChanged();

        }
    }
}
