package com.threehmis.bjaj.module.home.fragment.map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetLoginListReq;
import com.threehmis.bjaj.api.bean.request.GetSearchReq;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.api.bean.respon.GetSearchRsp;
import com.threehmis.bjaj.utils.CDUtil;
import com.threehmis.bjaj.utils.KeyBoardUtils;
import com.threehmis.bjaj.utils.RegexUtil;


import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;


public class MapFragment extends Fragment {


    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();

    TextureMapView mMapView;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位

    // 初始化全局 bitmap 信息，不用时及时 recycle
    BitmapDescriptor[] bd;
    BitmapDescriptor icon;
    Marker[] mMarker;
    private EditText write;
    private ImageView search;

    //定位点
    private LatLng point;

    //
    BaseBeanRsp<GetSearchRsp> getSearchRsp;

    @SuppressWarnings("unchecked")
    ArrayList<GetLoginListRsp> getLoginListRsp;
    private View view;

//    private ArrayList<GetLoginListRsp> getLoginListRsp= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == view)
            view = inflater.inflate(R.layout.fragment_map, container, false);
        initView();// 控件初始化
        return view;
    }

    private void initView() {
        //读取缓存
        getLoginListRsp = (ArrayList<GetLoginListRsp>) CDUtil
                .readObject(Const.LOGINDATE);
        if(getLoginListRsp == null){
            getLoginListRsp = new ArrayList<GetLoginListRsp>();
        }
//        getLoginListRsp = (ArrayList<GetLoginListRsp>) getArguments().getSerializable("getLoginListRsp");

    //    Log.e("CD", "xxx===" + JSON.toJSONString(getLoginListRsp));


        write = (EditText) view.findViewById(R.id.write);
        search = (ImageView) view.findViewById(R.id.search);
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
        search.setOnClickListener(new clcik());

// 地图初始化
        mMapView = (TextureMapView) view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        //设置地图缩放级别
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类
        option.setScanSpan(5000); //15秒
        option.setPriority(LocationClientOption.GpsFirst); // 设置GPS优先
        option.disableCache(false);// 禁止启用缓存定位
        mLocClient.setLocOption(option);
        mLocClient.start();

        //设置工程坐标点
        bd = new BitmapDescriptor[getLoginListRsp.size()];
        for (int i = 0; i < bd.length; i++) {

            bd[i] = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        }
        initOverlay();


        // 设置marker点的点击事件
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                for (int i = 0; i < mMarker.length; i++) {
                    //
                    if (marker == mMarker[i]) {
//                        Toast.makeText(getContext(), "你点击了" + getLoginListRsp.get(i).projectID, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), ProjectActivity.class);
                        intent.putExtra(Const.PROJECTID, getLoginListRsp.get(i).projectID);
                        intent.putExtra(Const.PROJECTNUM, getLoginListRsp.get(i).projectName);
                        intent.putExtra(Const.CUSTOMERID, getLoginListRsp.get(i).customerId);
                        intent.putExtra(Const.SGXKZH,getLoginListRsp.get(i).getSgxkzh());
                        intent.putExtra(Const.PROJECTCODE,getLoginListRsp.get(i).getProjectCode());
                        intent.putExtra(Const.SGXKZH,getLoginListRsp.get(i).getSgxkzh());
                        intent.putExtra(Const.PROJECTNUM,getLoginListRsp.get(i).getProjectNum());
                        startActivity(intent);

                    }
                }

                return true;
            }
        });

    }


    public void initOverlay() {
        // add marker overlay
        LatLng[] llA = new LatLng[getLoginListRsp.size()];
        for (int i = 0; i < getLoginListRsp.size(); i++) {
            // 封裝定位的點，因為數據都是0，所有這裡的點都是[0,0]
            String x = getLoginListRsp.get(i).projectGpsX;
            String y = getLoginListRsp.get(i).projectGpsX;
            if(TextUtils.isEmpty(x)){
                x="0";
            }
            if(TextUtils.isEmpty(y)){
                y="0";
            }
            llA[i] = new LatLng(Float.parseFloat(x), Float.parseFloat(y));
        }

//        llA[0] = new LatLng(23.126172, 113.347093);

        MarkerOptions[] ooA = new MarkerOptions[getLoginListRsp.size()];

        for (int i = 0; i < getLoginListRsp.size(); i++) {
            // 設置點的圖片
            ooA[i] = new MarkerOptions().position(llA[i]).icon(bd[i])
                    .zIndex(i + 1).draggable(true);
        }

//        ooA[0] = new MarkerOptions().position(llA[0]).icon(bd[0])
//                .zIndex(1).draggable(true);


        mMarker = new Marker[getLoginListRsp.size()];
        for (int i = 0; i < getLoginListRsp.size(); i++) {
            // 最后封装成一个Marker列表。
            mMarker[i] = (Marker) (mBaiduMap.addOverlay(ooA[i]));
        }

//        mMarker[0] = (Marker) (mBaiduMap.addOverlay(ooA[0]));
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 這裡不斷監聽，獲取當前的位置
            Log.d("CD", JSON.toJSONString(locData) + "");
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }

            mBaiduMap
                    .setMyLocationConfigeration(new MyLocationConfiguration(
                            MyLocationConfiguration.LocationMode.NORMAL, true, null));
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    //点击搜索
    private class clcik implements View.OnClickListener {


        @Override
        public void onClick(View view) {

            if (TextUtils.isEmpty(write.getText().toString())) {
                Toast.makeText(getContext(), "请输入搜索项目的名称或监督号!", Toast.LENGTH_SHORT).show();
                return;
            }
            KeyBoardUtils.closeKeybord(write, getContext());

            getInteract();


        }
    }


    private void getInteract() {

        GetSearchReq req = new GetSearchReq();
        req.keyword = write.getText().toString();
        req.personId = AndroidApplication.getInstance().getpersonId();
       // req.param = write.getText().toString();
      //  req.userId = AndroidApplication.getInstance().getuserId();
        // 访问网络
      //  AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "project/getProjectListByKeyword", req, new OkHttpcallback());

      //  String str = new Gson().toJson(req);

        Observable<BaseBeanRsp<GetSearchRsp>> observable = RetrofitFactory.getInstance().byKeyForMapLocal(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<GetSearchRsp>>compose(
        )).subscribe(new BaseObserver<GetSearchRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<GetSearchRsp> getSearchRspBaseBeanRsp) {
                if (getSearchRsp.verification) {
                    mHandler.sendEmptyMessage(RetrofitFactory.MSG_SUCESS);
                } else {
                    mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
                }
            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<GetSearchRsp> t) {
            }
        });
    }

 /*   class OkHttpcallback implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            Log.d("CD", "FAIL");
            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, okhttp3.Response response) throws IOException {


            String body = response.body().string();

            Log.d("CD", "OK" + body);

            getSearchRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetSearchRsp>>() {
            });

            if (getSearchRsp.verification) {
                mHandler.sendEmptyMessage(RetrofitFactory.MSG_SUCESS);
            } else {
                mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
            }
//
//            System.out.println("==dfs===" + SetPictureAddRsp.data.get(0).spath);


        }
    }*/

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if(getSearchRsp.projectList.size()>0) {
                        point = new LatLng(getSearchRsp.projectList.get(0).projectGpsY, getSearchRsp.projectList.get(0).projectGpsX);
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
                        MarkerOptions options = new MarkerOptions().icon(icon).position(point);
                        mBaiduMap.addOverlay(options);

                        //设定中心点坐标
                        //LatLng cenpt = new LatLng(30.663791,104.07281);
                        //定义地图状态
                        MapStatus mMapStatus = new MapStatus.Builder()
                                .target(point)
                                .zoom(18.0f)
                                .build();
                        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

                        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                        //改变地图状态
                        mBaiduMap.setMapStatus(mMapStatusUpdate);

                    }
                    break;
                case RetrofitFactory.MSG_FAIL:

                    Toast.makeText(getContext(), "无" + write.getText().toString() + "数据!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        if (mLocClient.isStarted() == false) {
            mLocClient.start();// 开启定位
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.onResume(); // 使百度地图地图控件和Fragment的生命周期保持一致
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.onPause(); // 使百度地图地图控件和Fragment的生命周期保持一致
        }
    }

    @Override
    public void onDestroy() {

        mHandler.removeCallbacksAndMessages(null);
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();

        if (bd != null) {

            for (int i = 0; i < bd.length; i++) {
                bd[i].recycle();
            }
        }

        if (icon != null) {
            icon.recycle();
        }

    }

}
