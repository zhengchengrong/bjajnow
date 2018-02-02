package com.threehmis.bjaj.module.home.fragment.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.AnnounceAdapter;

public class AnnounceFragment extends Fragment {


    private XRecyclerView recyclerview;

    private AnnounceAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announce, container, false);

        recyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);

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

        mAdapter = new AnnounceAdapter();
        recyclerview.setAdapter(mAdapter);


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


}
