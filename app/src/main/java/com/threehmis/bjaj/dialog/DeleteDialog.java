package com.threehmis.bjaj.dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetDeleteReq;
import com.threehmis.bjaj.api.bean.respon.StateSuccessRsp;
import com.threehmis.bjaj.module.more.ActionHistoryFragment;
import com.threehmis.bjaj.module.more.CheckQuestionFragment;
import com.threehmis.bjaj.module.more.CheckQuestionHistoryFragment;
import com.threehmis.bjaj.module.more.InfoRegistFragment;
import com.threehmis.bjaj.module.more.ScoreHistoryFragment;
import com.threehmis.bjaj.utils.ScreenUtil;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * @author CD
 *         记录里只有删除的dialog
 */
public class DeleteDialog extends android.app.Dialog implements
        View.OnClickListener {

    android.content.Context Context;
    private String id, whichnoe;
    private BaseBeanRsp<StateSuccessRsp> stateSuccessRsp;


    public DeleteDialog(android.content.Context Context, String id, String whichnoe) {
        this(Context, R.style.enterDialog);
        this.Context = Context;
        this.id = id;
        this.whichnoe = whichnoe;
    }

    public DeleteDialog(android.content.Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editdelete_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;
        wl.gravity = Gravity.BOTTOM;
        wl.width = ScreenUtil.getScreenWidth(Context);
        window.setAttributes(wl);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        findViewById(R.id.edit).setVisibility(View.GONE);
        findViewById(R.id.dele).setOnClickListener(this);
        findViewById(R.id.cancer).setOnClickListener(this);

        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mHandler.removeCallbacksAndMessages(null);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.dele:

                switch (whichnoe) {
                    case "抽查"://行为和实体抽查
                        delet("supervise/deleteCheck");
                        break;
                    case "评分记录":
                        delet("score/deleteScore");
                        break;
                    case "检测问题记录":
                        delet("question/deleteQuestion");
                        break;
                }

                break;

            case R.id.cancer:
                dismiss();
                break;
        }
    }


    private void delet(String interfacename) {

        GetDeleteReq req = new GetDeleteReq();

        req.id = id;

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + interfacename, req, new okhttpcall());
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
            stateSuccessRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<StateSuccessRsp>>() {
            });
            mHandler.sendEmptyMessage(stateSuccessRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    Toast.makeText(Context, "删除成功!", Toast.LENGTH_SHORT).show();

                    switch (whichnoe) {//广播刷新数据
                        case "抽查":
                            Intent intent = new Intent();
                            intent.setAction(ActionHistoryFragment.DATAREFRESH);
                            Context.sendBroadcast(intent);

                            Intent intent2 = new Intent();
                            intent2.setAction(InfoRegistFragment.INFOREGISTREF);
                            Context.sendBroadcast(intent2);
                            break;
                        case "评分记录":
                            Intent intent3 = new Intent();
                            intent3.setAction(ScoreHistoryFragment.DATAREFRESH);
                            Context.sendBroadcast(intent3);

                            break;
                        case "检测问题记录":
                            Intent intent4 = new Intent();
                            intent4.setAction(CheckQuestionHistoryFragment.DATAREFRESH);
                            Context.sendBroadcast(intent4);
                            //发广播清除fragment数据
                            Intent intentclean = new Intent(CheckQuestionFragment.STARTCAMERA);
                            intentclean.putExtra("clean", "clean");
                            Context.sendBroadcast(intentclean);

                            break;
                    }

                    dismiss();
                    break;
                case RetrofitFactory.MSG_FAIL:

                    Toast.makeText(Context, "删除失败!", Toast.LENGTH_SHORT).show();
                    dismiss();
                    break;
                default:
                    break;

            }
            super.handleMessage(msg);
        }
    };

}
