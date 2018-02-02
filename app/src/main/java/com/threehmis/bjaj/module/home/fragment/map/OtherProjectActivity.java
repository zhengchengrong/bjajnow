package com.threehmis.bjaj.module.home.fragment.map;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.ProjectListotherAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetSearchReq;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.utils.KeyBoardUtils;
import com.threehmis.bjaj.utils.OkhttpCallbackUtils;
import com.threehmis.bjaj.utils.RegexUtil;


import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherProjectActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.write)
    EditText write;

    // 加载第几页
    private int pageIndex = 1, pagesize = 20;
    private ProgressDialog dialog;
    private ProjectListotherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_project);
        ButterKnife.bind(this);
        title.setText("监督巡查");

        initView();

    }

    private void initView() {

        recyclerview.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);

        recyclerview.setLaodingMoreProgressStyle(ProgressStyle.Pacman);

        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerview.setLoadingListener(new XRecyclerViewLoadingListener());


        mAdapter = new ProjectListotherAdapter();
        recyclerview.setAdapter(mAdapter);

        write.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String editable = write.getText().toString();
                String str = RegexUtil.stringFilter(editable.toString());
                if (!editable.equals(str)) {
                    write.setText(str);
                    //设置新的光标所在位置
                    write.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        write.setOnEditorActionListener(new editorActionListener());
    }

    private class editorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

            if (actionId == EditorInfo.IME_ACTION_SEARCH || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                KeyBoardUtils.closeKeybord(write, OtherProjectActivity.this);
                getInteract();
            }

            return false;
        }
    }

    private void getInteract() {

        dialog = new ProgressDialog(this);

        dialog.setCanceledOnTouchOutside(false);

        dialog.setTitle("搜索中...");

        dialog.show();

        GetSearchReq req = new GetSearchReq();
        req.param = write.getText().toString();
        req.customerId = AndroidApplication.getInstance().getcustomerId();
        req.pageno = pageIndex + "";
        req.pagesize = pagesize + "";

        AndroidApplication.getInstance().doPostAsyncfilexx(RetrofitFactory.BASE_URL + "project/findOtherByNameOrNo", req,
                new OkhttpCallbackUtils<GetLoginListRsp>(new TypeReference<BaseBeanRsp<GetLoginListRsp>>() {
                }) {

                    @Override
                    public void onFailure(IOException e) {
                        super.onFailure(e);
                        dialog.dismiss();
                        if (pageIndex == 1) recyclerview.refreshComplete();
                        if (pageIndex != 1) recyclerview.loadMoreComplete();
                        Toast.makeText(getApplicationContext(), "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseBeanRsp<GetLoginListRsp> t) {
                        super.onResponse(t);
                        if (t.verification) {
                            mAdapter.notifyData(t.projectList, pageIndex);
                        } else {
                            Toast.makeText(getApplicationContext(), t.result, Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        if (pageIndex == 1) recyclerview.refreshComplete();
                        if (pageIndex != 1) recyclerview.loadMoreComplete();
                    }
                });

    }

    class XRecyclerViewLoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {
            pageIndex = 1;
            getInteract();
        }

        @Override
        public void onLoadMore() {
            if (mAdapter.getItemCount() % 20 == 0) {
                pageIndex = ++pageIndex;
                getInteract();
            } else {
                recyclerview.noMoreLoading();
            }
        }
    }

    @OnClick(R.id.search)
    void search() {
        KeyBoardUtils.closeKeybord(write, OtherProjectActivity.this);
        pageIndex = 1;
        getInteract();
    }

    @OnClick(R.id.titleback)
    void titleback() {

        finish();
    }


}
