package com.threehmis.bjaj.module.more;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.InfoRegistAdapter;
import com.threehmis.bjaj.adapter.PopupListAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetInfoRegistReq;
import com.threehmis.bjaj.api.bean.request.GetInfoRegistSearchReq;
import com.threehmis.bjaj.api.bean.respon.GetInfoRigistRsp;
import com.threehmis.bjaj.utils.ScreenUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//信息登记 行为
public class InfoRegistFragment extends Fragment implements View.OnClickListener {


    private XRecyclerView recyclerview;
    private String projectType;

    private String inspectionId;
    public static String INFOREGISTREF = "inforefreshdate"; //刷新数据

    BaseBeanRsp<GetInfoRigistRsp> getInfoRigistRsp;
    private InfoRegistAdapter mAdapter;
    private boolean isentity;
    private boolean isquality; //判断是否是质量监督的

    // 加载第几页
    private int pageIndex = 1, pagesize = 20;

    private TextView typelist, delet, type;
    private PopupWindow popLeft;
    private LinearLayout layout_top;
    private String searchtype;

    //单位类型数据
    List<GetInfoRigistRsp.TitleListBean> liststr = new ArrayList<>();
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_regist, container, false);

        layout_top = (LinearLayout) view.findViewById(R.id.layout_top);
        typelist = (TextView) view.findViewById(R.id.typelist);
        delet = (TextView) view.findViewById(R.id.delet);
        type = (TextView) view.findViewById(R.id.type);
        recyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);

        layout_top.setVisibility(View.GONE);

        Bundle bundle = getActivity().getIntent().getExtras();
        projectType = bundle.getString("projectType");
        isentity = bundle.getBoolean("isentity");
        inspectionId = bundle.getString("pk");
        isquality = bundle.getBoolean("isquality", false);

        dialog = new ProgressDialog(getActivity());

        dialog.setCanceledOnTouchOutside(false);

        dialog.setTitle("获取数据中...");


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerview.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);

        recyclerview.setLaodingMoreProgressStyle(ProgressStyle.Pacman);

        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerview.setLoadingListener(new XRecyclerViewLoadingListener());

        mAdapter = new InfoRegistAdapter(getActivity(), inspectionId, mHandler2, isentity);
        recyclerview.setAdapter(mAdapter);

        typelist.setOnClickListener(this);
        delet.setOnClickListener(this);

        getdate();

    }

    private void getdate() {

        dialog.show();
        GetInfoRegistReq req = new GetInfoRegistReq();

        req.superviseType = isquality ? "质量" : "安全";
        req.projectType = projectType;
        req.itemType = isentity ? "实体抽查" : "行为管理";
        req.inspectionId = inspectionId;
        req.pageno = pageIndex + "";
        req.pagesize = pagesize + "";

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "supervise/getItem", req, new okhttpcall());

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.typelist:
                if (popLeft != null && popLeft.isShowing()) {
                    popLeft.dismiss();
                } else {
                    if (liststr != null && liststr.size() > 0)
                        dealpopupWindow();
                }

                break;

            case R.id.delet:
                type.setText("条件筛选");
                getdate();
                break;

        }
    }


    class okhttpcall implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();

            Log.d("CD", "DDDDDDDDDD=" + body);

            getInfoRigistRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetInfoRigistRsp>>() {
            });
            mHandler.sendEmptyMessage(getInfoRigistRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    dialog.cancel();
                    if (pageIndex == 1) recyclerview.refreshComplete();
                    if (pageIndex != 1) recyclerview.loadMoreComplete();
                    mAdapter.notifyData(getInfoRigistRsp.projectList, pageIndex);

                    liststr = getInfoRigistRsp.projectList.get(0).titleList;

                    layout_top.setVisibility(View.VISIBLE);
                    break;
                case RetrofitFactory.MSG_FAIL:

                    dialog.cancel();
                    recyclerview.refreshComplete();
                    if (getActivity() != null) {
                        if (getInfoRigistRsp.result != null)
                            Toast.makeText(getActivity(), getInfoRigistRsp.result, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(), "获取数据失败!", Toast.LENGTH_SHORT).show();

                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    protected Handler mHandler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if (type.getText().toString().equals("条件筛选"))
                        getdate(); //刷新数据
                    else
                        getSearchdate(searchtype);


                    Toast.makeText(getContext(), "保存成功!", Toast.LENGTH_SHORT).show();

                    break;
                case RetrofitFactory.MSG_FAIL:

                    if (getActivity() != null)
                        Toast.makeText(getContext(), "保存失败!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
        mHandler2.removeCallbacksAndMessages(null);
        getActivity().unregisterReceiver(receiver);

        InfoRegistAdapter.checkId = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(INFOREGISTREF);
        getActivity().registerReceiver(receiver, filter);


    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null)
                getdate();
            type.setText("条件筛选");
        }
    };

    class XRecyclerViewLoadingListener implements XRecyclerView.LoadingListener {


        @Override
        public void onRefresh() {
            pageIndex = 1;
            getdate();
        }

        @Override
        public void onLoadMore() {
            if (mAdapter.getItemCount() % 20 == 0) {
                pageIndex = ++pageIndex;
                getdate();
            } else {
                recyclerview.noMoreLoading();
            }
        }
    }

    private void dealpopupWindow() {

        View layoutLeft = LayoutInflater.from(getActivity()).inflate(
                R.layout.pop_menulist, null);
        ListView menulistLeft = (ListView) layoutLeft
                .findViewById(R.id.menulist);
        PopupListAdapter listAdapter = new PopupListAdapter(liststr);
        menulistLeft.setAdapter(listAdapter);

        // 点击listview中item的处理
        menulistLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 改变顶部对应TextView值

                type.setText(liststr.get(position).itemName);
                pageIndex = 1;
                searchtype=liststr.get(position).pk;
                getSearchdate(liststr.get(position).pk);
                // 隐藏弹出窗口
                if (popLeft != null && popLeft.isShowing()) {
                    popLeft.dismiss();
                }

            }
        });

        // 创建弹出窗口
        // 窗口内容为layoutLeft，里面包含一个ListView
        // 窗口宽度跟tvLeft一样
        popLeft = new PopupWindow(layoutLeft, (int) (ScreenUtil.getScreenWidth(getActivity()) * 0.7),
                LinearLayout.LayoutParams.WRAP_CONTENT);

        ColorDrawable cd = new ColorDrawable(0x00ffffff);
        popLeft.setBackgroundDrawable(cd);
//        popLeft.setAnimationStyle(R.style.PopupAnimation);
        popLeft.update();
        popLeft.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popLeft.setTouchable(true); // 设置popupwindow可点击
        popLeft.setOutsideTouchable(true); // 设置popupwindow外部可点击
        popLeft.setFocusable(true); // 获取焦点

        // 设置popupwindow的位置（相对tvLeft的位置）
        //int topBarHeight = typelist.getBottom();
        popLeft.showAsDropDown(typelist, typelist.getWidth() + 14,
                -typelist.getHeight());

        popLeft.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果点击了popupwindow的外部，popupwindow也会消失
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popLeft.dismiss();
                    return true;
                }
                return false;
            }
        });


    }

    private int dp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private void getSearchdate(String type) {

        dialog.show();
        GetInfoRegistSearchReq req = new GetInfoRegistSearchReq();

        req.type = type;
        req.inspectionId = inspectionId;
        req.pageno = pageIndex + "";
        req.pagesize = pagesize + "";
        req.superviseType = isquality ? "质量" : "安全";
        req.projectType = projectType;
        req.itemType = isentity ? "实体抽查" : "行为管理";

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "supervise/getItemByType", req, new okhttpcall());

    }

}
