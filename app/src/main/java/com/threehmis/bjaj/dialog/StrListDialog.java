package com.threehmis.bjaj.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.AddressListAdapter;
import com.threehmis.bjaj.api.bean.respon.GetInfoRigistRsp;
import com.threehmis.bjaj.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author CD
 */
public class StrListDialog extends Dialog implements
        View.OnClickListener {

    Activity context;

    private GetProjectListener listener;

    private ListView list_view;

    private List<GetInfoRigistRsp.TitleListBean> listdate = new ArrayList<>();
    List<GetInfoRigistRsp.CheckListBean> ckecklist = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private AddressListAdapter adapter;


    public StrListDialog(Activity context) {
        this(context, R.style.shareDialog);
        this.context = context;
    }

    public StrListDialog(Context context, int theme) {
        super(context, theme);
    }

    public StrListDialog(Activity context, List<GetInfoRigistRsp.TitleListBean> listdate, List<GetInfoRigistRsp.CheckListBean> ckecklist, GetProjectListener listener) {
        this(context, R.style.shareDialog);

        this.context = context;
        this.listdate = listdate;
        this.ckecklist = ckecklist;
        this.listener = listener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_real_measure);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;
        wl.gravity = Gravity.CENTER;
        wl.width = ScreenUtil.getScreenWidth(context);
        wl.height = ScreenUtil.getScreenHeight(context);
        window.setAttributes(wl);
        setCancelable(true); //点击返回键取消
        setCanceledOnTouchOutside(true); //点击外围空间取消

        list_view = (ListView) findViewById(R.id.list_view);


        adapter = new AddressListAdapter();
        list_view.setAdapter(adapter);


        if (listdate != null) {
            list.clear();
            for (GetInfoRigistRsp.TitleListBean obj : listdate) {

                list.add(obj.itemName);

            }
            adapter.adddate(list);
        } else {
            list.clear();
            for (GetInfoRigistRsp.CheckListBean obj : ckecklist) {

                list.add(obj.itemName);

            }
            adapter.adddate(list);
        }

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {

                if (listdate != null)
                    listener.Getdate(listdate.get(positon).itemName, listdate.get(positon).pk,null,1);
                else
                    listener.Getdate(ckecklist.get(positon).itemName, ckecklist.get(positon).baseItemPk,ckecklist.get(positon).checkFile,2);
                dismiss();
            }
        });


        TextView title = (TextView) findViewById(R.id.title);
        title.setText("列表选项");
        findViewById(R.id.titleback).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        cancel();
        switch (v.getId()) {
            case R.id.titleback:
                dismiss();
                break;
        }
    }


    //接口传参
    public interface GetProjectListener {
        /**
         * 回调函数，用于在Dialog的监听事件触发后刷新Activity的UI显示
         */
        void Getdate(String str1, String str2, List<GetInfoRigistRsp.CheckListBean.CheckFileBean> files, int i);


    }


}
