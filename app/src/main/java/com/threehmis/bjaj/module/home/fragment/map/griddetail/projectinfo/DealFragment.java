package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.widget.chartview.HistogramFourRedView;
import com.threehmis.bjaj.widget.chartview.LineChartTwoView;


//处理情况
public class DealFragment extends Fragment implements View.OnClickListener{

    private int[] progress,progress2;
    private ImageView change;
    boolean isclick=false;
    private LinearLayout layouline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deal, container, false);

        layouline = (LinearLayout) view.findViewById(R.id.layou_line);
        LineChartTwoView line = (LineChartTwoView) view.findViewById(R.id.line);
        LinearLayout chart = (LinearLayout) view.findViewById(R.id.chart);

        HistogramFourRedView blue = (HistogramFourRedView) view.findViewById(R.id.blue);

        progress = new int[]{ 9, 8, 7, 11 };// 4
        progress2 = new int[]{ 5, 3, 2, 1 };// 4
        blue.start(15.0,progress,progress2);
        change = (ImageView) view.findViewById(R.id.change);
        change.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.change:

                change.setImageResource(isclick?R.drawable.s01:R.drawable.s02);
                isclick=!isclick;
                layouline.setVisibility(isclick? View.VISIBLE: View.GONE);
                break;

        }
    }


}
