package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.ProjectListAdapter;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetSearchReq;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.utils.CDUtil;
import com.threehmis.bjaj.utils.KeyBoardUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//工程列表
public class ProjectListFragment extends Fragment {
    private XRecyclerView recyclerview;
    private ArrayList<GetLoginListRsp> getLoginListRsp;
    private ProjectListAdapter mAdapter;
    private EditText write;
    private ImageView search;
    private BaseBeanRsp<GetLoginListRsp> getSearchRsp;  //登陆返回数据和搜索返回数据结果一样
    TextWatcher mVarValueTextWatcher;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        //读取缓存
        getLoginListRsp = (ArrayList<GetLoginListRsp>) CDUtil
                .readObject(Const.LOGINDATE);
        recyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);
        write = (EditText) view.findViewById(R.id.write);
        search = (ImageView) view.findViewById(R.id.search);
        write.setOnEditorActionListener(new editorActionListener());
        search.setOnClickListener(new Click());
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
        mAdapter = new ProjectListAdapter();
        recyclerview.setAdapter(mAdapter);
        mAdapter.notifyData(getLoginListRsp);
    }


    class XRecyclerViewLoadingListener implements XRecyclerView.LoadingListener {
        @Override
        public void onRefresh() {
            recyclerview.refreshComplete();
        }
        @Override
        public void onLoadMore() {
            recyclerview.loadMoreComplete();
        }
    }


    private class Click implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            KeyBoardUtils.closeKeybord(write,getContext());
            getInteract();
        }
    }

    private void getInteract(){
        dialog = new ProgressDialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("搜索中...");
        dialog.show();
        GetSearchReq req = new GetSearchReq();
        req.param=write.getText().toString();
        req.userId= AndroidApplication.getInstance().getuserId();
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL+"project/findByNameOrNo",req,new OkHttpcallback());
    }

    class OkHttpcallback implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);

        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d("CD", "OK"+body);
            getSearchRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetLoginListRsp>>() {});

            mHandler.sendEmptyMessage(getSearchRsp.verification?RetrofitFactory.MSG_SUCESS:RetrofitFactory.MSG_FAIL);

        }
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:
                    dialog.cancel();
                    recyclerview.setVisibility(View.VISIBLE);
                    mAdapter.notifyData(getSearchRsp.projectList);

                    break;
                case RetrofitFactory.MSG_FAIL:
                    dialog.cancel();
                    recyclerview.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "无"+write.getText().toString()+"数据!", Toast.LENGTH_SHORT).show();
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
    }


    private class editorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

            if (actionId== EditorInfo.IME_ACTION_SEARCH|| (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)){

                KeyBoardUtils.closeKeybord(write,getContext());
                getInteract();
            }

            return false;
        }
    }


}
