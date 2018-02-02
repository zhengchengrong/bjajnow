package com.threehmis.bjaj.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;


import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkhttpCallbackUtils<T> implements Callback {


    private TypeReference<BaseBeanRsp<T>> mClazz;

    protected OkhttpCallbackUtils(TypeReference<BaseBeanRsp<T>> mClazz) {
        if (mClazz == null) {
            throw new IllegalArgumentException("response can't be null");
        }

        this.mClazz = mClazz;
    }

    @Override
    public void onFailure(Call call, IOException e) {

        Message message = Message.obtain();
        message.what = RetrofitFactory.MSG_FAIL;
        message.obj = e;
        mHandler.sendMessage(message);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        if (response.isSuccessful()) {
            String body = response.body().string();

            //fastjison 解析 对泛型最多只能解析一层泛型数据 泛型多层嵌套无法解析内部泛型 分析后要传人整个TypeReference<T>

            BaseBeanRsp<T> mResponse = JSON.parseObject(body, mClazz);
            //mResponse = JSON.parseObject(body,type);
            Log.d("CD","DDDDJSOM="+ JSON.toJSONString(mResponse));

            Message message = Message.obtain();
            message.what = RetrofitFactory.MSG_SUCESS;
            message.obj = mResponse;
            mHandler.sendMessage(message);

        } else {
            Message message = Message.obtain();
            message.what = RetrofitFactory.MSG_FAIL;
            mHandler.sendMessage(message);
        }

    }

    //hander 的处理
    private Handler mHandler = new UIHandler(this);

    private static class UIHandler<T> extends Handler {

        //handler 弱引用
        private WeakReference mWeakReference;

        private UIHandler(OkhttpCallbackUtils callback) {
            super(Looper.getMainLooper());

            mWeakReference = new WeakReference(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS: {
                    BaseBeanRsp<T> t = (BaseBeanRsp<T>) msg.obj;
                    OkhttpCallbackUtils callback = (OkhttpCallbackUtils) mWeakReference.get();
                    if (callback != null) {
                        callback.onResponse(t);
                    }
                    break;
                }
                case RetrofitFactory.MSG_FAIL: {
                    IOException e = (IOException) msg.obj;
                    OkhttpCallbackUtils callback = (OkhttpCallbackUtils) mWeakReference.get();
                    if (callback != null) {
                        callback.onFailure(e);
                    }
                    break;
                }
                default:
                    super.handleMessage(msg);
                    break;
            }


        }

    }

    //增加两个外面调用处理的方法
    public void onResponse(BaseBeanRsp<T> t) {

    }

    public void onFailure(IOException e) {

    }


}

