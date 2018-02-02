package com.threehmis.bjaj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.utils.GlideTools;
import com.threehmis.bjaj.utils.VHUtil;


import java.util.ArrayList;
import java.util.List;


public class ProjectRollAdapter extends StaticPagerAdapter {


//    List<GetRollPictureRsp> mRsps = new ArrayList<>();
    List<String> mRsps = new ArrayList<>();


    //http://img0.imgtn.bdimg.com/it/u=1901715451,1483624715&fm=21&gp=0.jpg


    @Override
    public int getCount() {
        return mRsps!=null?mRsps.size():0;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_projectroll, null);


        ImageView img = VHUtil.ViewHolder.get(view, R.id.img);



        GlideTools.GlideNofit(mRsps.get(position), img, R.drawable.default_pic);

        return view;
    }

    public void notifyData(List<String> mRsps) {

        this.mRsps.clear();
        this.mRsps = mRsps;
        notifyDataSetChanged();

    }





}
