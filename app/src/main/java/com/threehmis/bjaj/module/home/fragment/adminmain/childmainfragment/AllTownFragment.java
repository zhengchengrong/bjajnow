package com.threehmis.bjaj.module.home.fragment.adminmain.childmainfragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Spinner;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.widget.ExpandableGridView;
import com.threehmis.bjaj.widget.chartview.BarChart02View;

import org.xclcharts.chart.BarData;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.baidu.location.h.a.i;

/**
 * Created by llz on 2018/2/22.
 */

public class AllTownFragment extends BaseFragment {
    @BindView(R.id.tv_all_city_01)
    TextView mTvAllCity01;
    @BindView(R.id.id_spinner_year)
    Spinner mIdSpinnerYear;
    @BindView(R.id.id_spinner_all)
    Spinner mIdSpinnerAll;
    @BindView(R.id.gridview)
    ExpandableGridView mGridview;

    GridAdapter mGridAdapter;
    //图标下的文字
    String name[] = {"工程数量", "工程面积", "工程长度", "法式安全监督告知数量", "工程长度", "工程长度", "工程长度", "工程长度", "工程长度", "工程长度", "工程长度", "工程长度"};
    @BindView(R.id.mHorizontalBarChart)
    BarChart02View mMHorizontalBarChart;

    Random random = new Random();
    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mGridAdapter = new GridAdapter(mActivity);
        mGridview.setAdapter(mGridAdapter);
        mGridview.setOnItemClickListener(new Gallery.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                for (int i = 0; i < parent.getCount(); i++) {
                    TextView v = (TextView) parent.getChildAt(i);
                    if (position == i) {//当前选中的Item改变背景颜色
                        v.setBackgroundResource(R.drawable.shape_main_color_select);
                        v.setTextColor(mActivity.getResources().getColor(R.color.white));
                    } else {
                        v.setBackgroundResource(R.drawable.shape_main_color);
                        v.setTextColor(mActivity.getResources().getColor(R.color.main_color));
                    }
                }
                List<String> chartLabels = new LinkedList<String>();
                List<Double> dataSeriesA= new LinkedList<Double>();
                chartLabels.add("区城区安全监督站123"+position);
                chartLabels.add("区城区安全监督站123"+position);
                chartLabels.add("区城区安全监督站123"+position);
                mMHorizontalBarChart.setChartLabels(chartLabels);
                dataSeriesA.add((double)2500);
                dataSeriesA.add((double)1000);
                dataSeriesA.add((double)2000);
                BarData BarDataA = new BarData("危大工程"+position,dataSeriesA, Color.rgb(random.nextInt(256), random.nextInt(256),random.nextInt(256)));
                List<BarData> chartData = new LinkedList<BarData>();
                chartData.add(BarDataA);
                mMHorizontalBarChart.setChartData(chartData);
                mMHorizontalBarChart.chartRender(name[position],3000,500,500);
                mMHorizontalBarChart.invalidate();
            }
        });

        List<String> chartLabels = new LinkedList<String>();
        chartLabels.add("区城区安全监督站");
        chartLabels.add("区城区安全监督站");
        chartLabels.add("区城区安全监督站");
        mMHorizontalBarChart.setChartLabels(chartLabels);

        List<Double> dataSeriesA= new LinkedList<Double>();
        dataSeriesA.add((double)2500);
        dataSeriesA.add((double)1000);
        dataSeriesA.add((double)2000);
        BarData BarDataA = new BarData("危大工程",dataSeriesA, Color.rgb(151, 185,96));
         List<BarData> chartData = new LinkedList<BarData>();
        chartData.add(BarDataA);
        mMHorizontalBarChart.setChartData(chartData);
        mMHorizontalBarChart.chartRender(name[0],3000,500,500);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_all_town;
    }



    class GridAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public GridAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return name.length;
        }

        public Object getItem(int position) {
            return name[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ItemViewTag viewTag;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.gridview_town_item, null);
                // construct an item tag
                viewTag = new ItemViewTag((TextView) convertView.findViewById(R.id.text));
                convertView.setTag(viewTag);
            } else {
                viewTag = (ItemViewTag) convertView.getTag();
            }
            if (position == 0) {
                viewTag.mName.setBackgroundResource(R.drawable.shape_main_color_select);
                viewTag.mName.setTextColor(mActivity.getResources().getColor(R.color.white));
            }
            // set name
            viewTag.mName.setText(name[position]);
            return convertView;
        }

        class ItemViewTag {
            protected TextView mName;

            public ItemViewTag(TextView name) {
                this.mName = name;
            }
        }

    }

}
