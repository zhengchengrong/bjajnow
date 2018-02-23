package com.threehmis.bjaj.module.home.fragment.adminmain.childmainfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.widget.ExpandableGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    ArrayList<Map<String, Object>> dataList;
    private SimpleAdapter adapter;

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        String[] from={"text"};
        int[] to={R.id.text};
        initData();
        adapter=new SimpleAdapter(mActivity, dataList, R.layout.gridview_town_item, from, to);
        mGridview.setAdapter(adapter);
        mGridview.setOnItemClickListener(new Gallery.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position,long arg3) {
                for(int i=0;i<parent.getCount();i++){
                    TextView v=(TextView)parent.getChildAt(i);
                    if (position == i) {//当前选中的Item改变背景颜色
                        v.setBackgroundResource(R.drawable.shape_main_color_select);
                        v.setTextColor(mActivity.getResources().getColor(R.color.white));
                    } else {
                        v.setBackgroundResource(R.drawable.shape_main_color);
                        v.setTextColor(mActivity.getResources().getColor(R.color.main_color));
                    }
                }
            }});

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_all_town;
    }

    void initData() {

        //图标下的文字
        String name[]={"工程数量","工程面积","工程长度","法式安全监督告知数量","工程长度","工程长度","工程长度","工程长度","工程长度","工程长度","工程长度","工程长度"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <name.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("text",name[i]);
            dataList.add(map);
        }
    }
     class GridAdapter extends BaseAdapter {
        private ArrayList<String> mNameList = new ArrayList<String>();
        private ArrayList<Drawable> mDrawableList = new ArrayList<Drawable>();
        private LayoutInflater mInflater;
        private Context mContext;
        LinearLayout.LayoutParams params;

        public GridAdapter(Context context, ArrayList<String> nameList, ArrayList<Drawable> drawableList) {
            mNameList = nameList;
            mDrawableList = drawableList;
            mContext = context;
            mInflater = LayoutInflater.from(context);

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
        }

        public int getCount() {
            return mNameList.size();
        }

        public Object getItem(int position) {
            return mNameList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ItemViewTag viewTag;

            if (convertView == null)
            {
                convertView = mInflater.inflate(R.layout.gridview_town_item, null);

                // construct an item tag
                viewTag = new ItemViewTag((TextView) convertView.findViewById(R.id.text));
                convertView.setTag(viewTag);
            } else
            {
                viewTag = (ItemViewTag) convertView.getTag();
            }

            // set name
            viewTag.mName.setText(mNameList.get(position));
            return convertView;
        }

        class ItemViewTag
        {
            protected TextView mName;
            public ItemViewTag(TextView name)
            {
                this.mName = name;
            }
        }

    }

}
