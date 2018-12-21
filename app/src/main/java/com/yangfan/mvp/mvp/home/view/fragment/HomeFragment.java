package com.yangfan.mvp.mvp.home.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangfan.mvp.mvp.home.presenter.UserPresenter;
import com.yangfan.mvp.mvp.home.view.IUserView;
import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;
import com.yangfan.mvp.R;
import com.yangfan.mvp.adapter.base.BaseRecyclerAdapter;
import com.yangfan.mvp.databinding.FragmentHomeBinding;
import com.yangfan.mvp.mvp.home.presenter.UserPresenter;
import com.yangfan.mvp.mvp.home.view.IUserView;
import com.yangfan.mvp.mvp.home.viewmodel.UserInfo;

import java.util.List;

/**
 * view层只关心页面的更新
 * 首页fragment
 */
public class HomeFragment extends Fragment implements IUserView {
    private UserPresenter presenter;
    private FragmentHomeBinding binding;
    private BaseRecyclerAdapter<UserInfo> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding == null) {
            binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,
                    false);
            LinearLayoutManager manager=new LinearLayoutManager(getContext());
            binding.recyHome.setLayoutManager(manager);
            adapter=new BaseRecyclerAdapter<UserInfo>() {};
            binding.recyHome.setAdapter(adapter);
            presenter=new UserPresenter(this);
            presenter.getUserInfo();//请求数据
        }
        ViewGroup parent = (ViewGroup) binding.getRoot().getParent();
        if (parent != null) {
            parent.removeView(binding.getRoot());
        }
        return binding.getRoot();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onLoadUserDataFailed() {

    }

    @Override
    public void onLoadUserDataSuccess(List<UserInfo> list) {
        adapter.addAll(list,false);
    }
}
