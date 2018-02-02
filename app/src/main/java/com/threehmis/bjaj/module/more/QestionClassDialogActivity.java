package com.threehmis.bjaj.module.more;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
import com.threehmis.bjaj.adapter.PopupList3Adapter;
import com.threehmis.bjaj.adapter.QuestionClassDialogListAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetCheckQuestionClassReq;
import com.threehmis.bjaj.api.bean.request.GetQuestionClassSearchReq;
import com.threehmis.bjaj.api.bean.respon.GetCheckQuestionClassRsp;
import com.threehmis.bjaj.utils.ScreenUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

// 基于问题类型的dialog修改为带搜索的activity  QestionClassDialog和这个 可分别使用
public class QestionClassDialogActivity extends AppCompatActivity implements View.OnClickListener {


    private XRecyclerView recyclerview;
    // 加载第几页
    private int pageIndex = 1, pagesize = 20;
    private BaseBeanRsp<GetCheckQuestionClassRsp> getCheckQuestionClassRsp;
    private QuestionClassDialogListAdapter adapter;

    private boolean isquality; //判断是否是质量监督的
    private String projectType;

    private TextView typelist, delet, type;
    private PopupWindow popLeft;
    private LinearLayout layout_top;

    //单位类型数据
    List<GetCheckQuestionClassRsp.TitleListBean> liststr = new ArrayList<>();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qestion_class_dialog);

        Bundle bundle = this.getIntent().getExtras();
        isquality = bundle.getBoolean("isquality", false);
        projectType = bundle.getString("projectType");

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("获取数据中...");


        recyclerview = (XRecyclerView) findViewById(R.id.recyclerview);
        layout_top = (LinearLayout) findViewById(R.id.layout_top);
        delet = (TextView) findViewById(R.id.delet);
        type = (TextView) findViewById(R.id.type);
        typelist = (TextView) findViewById(R.id.typelist);

        layout_top.setVisibility(View.GONE);

        recyclerview = (XRecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);

        recyclerview.setLaodingMoreProgressStyle(ProgressStyle.Pacman);

        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerview.setLoadingListener(new XRecyclerViewLoadingListener());


        adapter = new QuestionClassDialogListAdapter();
        recyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new QuestionClassDialogListAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(String date1, String date2, String date3) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("date1", date1);
                bundle.putString("date2", date2);
                bundle.putString("date3", date3);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }

        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("问题类型");
        findViewById(R.id.titleback).setOnClickListener(this);
        typelist.setOnClickListener(this);
        delet.setOnClickListener(this);

        getData();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleback:
                finish();
                break;
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
                getData();
                break;

        }

    }

    //获取数据
    private void getData() {

        dialog.show();
        GetCheckQuestionClassReq req = new GetCheckQuestionClassReq();

        req.superviseType = isquality ? "质量" : "安全";  //0.质量 1.安全
        req.projectType = projectType;
        req.pageno = pageIndex + "";
        req.pagesize = pagesize + "";

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "question/getBaseQuestion", req, new okhttpcall());


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

            getCheckQuestionClassRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetCheckQuestionClassRsp>>() {
            });


            mHandler.sendEmptyMessage(getCheckQuestionClassRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    dialog.cancel();
                    if (pageIndex == 1) recyclerview.refreshComplete();
                    if (pageIndex != 1) recyclerview.loadMoreComplete();
                    adapter.notifyData(getCheckQuestionClassRsp.projectList.get(0).questionList, pageIndex);

                    liststr = getCheckQuestionClassRsp.projectList.get(0).titleList;
                    layout_top.setVisibility(View.VISIBLE);
                    break;
                case RetrofitFactory.MSG_FAIL:

                    dialog.cancel();
                    Toast.makeText(QestionClassDialogActivity.this, "获取数据失败!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    class XRecyclerViewLoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {
            pageIndex = 1;
            getData();
        }

        @Override
        public void onLoadMore() {
            if (adapter.getItemCount() % 20 == 0) {
                pageIndex = ++pageIndex;
                getData();
            } else {
                recyclerview.noMoreLoading();
            }
        }
    }

    private void dealpopupWindow() {

        View layoutLeft = LayoutInflater.from(this).inflate(
                R.layout.pop_menulist, null);
        ListView menulistLeft = (ListView) layoutLeft
                .findViewById(R.id.menulist);
        PopupList3Adapter listAdapter = new PopupList3Adapter(liststr);
        menulistLeft.setAdapter(listAdapter);

        // 点击listview中item的处理
        menulistLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 改变顶部对应TextView值

                type.setText(liststr.get(position).projectBranch);
                pageIndex = 1;
                getSearchdate(liststr.get(position).projectBranch);
                // 隐藏弹出窗口
                if (popLeft != null && popLeft.isShowing()) {
                    popLeft.dismiss();
                }

            }
        });

        // 创建弹出窗口
        // 窗口内容为layoutLeft，里面包含一个ListView
        // 窗口宽度跟tvLeft一样
        popLeft = new PopupWindow(layoutLeft, (int) (ScreenUtil.getScreenWidth(this) * 0.7),
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

    private void getSearchdate(String type) {

        dialog.show();
        GetQuestionClassSearchReq req = new GetQuestionClassSearchReq();

        req.type = type;
        req.pageno = pageIndex + "";
        req.pagesize = pagesize + "";
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "question/getQuestionByType", req, new okhttpcall());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }


}
