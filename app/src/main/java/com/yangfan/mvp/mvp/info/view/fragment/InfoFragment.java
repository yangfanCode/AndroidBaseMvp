package com.yangfan.mvp.mvp.info.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangfan.mvp.mvp.info.view.IInfoView;
import com.yangfan.mvp.R;
import com.yangfan.mvp.adapter.base.BaseRecyclerAdapter;
import com.yangfan.mvp.adapter.base.OnClickPresenter;
import com.yangfan.mvp.databinding.FragmentInfoBinding;
import com.yangfan.mvp.mvp.info.presenter.InfoPresenter;
import com.yangfan.mvp.mvp.info.view.IInfoView;
import com.yangfan.mvp.mvp.info.viewmodel.InfoVM;
import com.yangfan.mvp.widget.TipsToast;

import java.util.List;

/**
 * 资讯fragment
 */
public class InfoFragment extends Fragment implements IInfoView,OnClickPresenter<InfoVM> {

    private FragmentInfoBinding binding;
    private InfoPresenter presenter;
    private BaseRecyclerAdapter<InfoVM>adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (binding == null) {
            binding=DataBindingUtil.inflate(inflater,R.layout.fragment_info,container,
                    false);
            LinearLayoutManager manager=new LinearLayoutManager(getContext());
            binding.rvInfo.setLayoutManager(manager);
            adapter=new BaseRecyclerAdapter<InfoVM>() {};
            binding.rvInfo.setAdapter(adapter);
            adapter.setOnClickPresenter(this);
            presenter=new InfoPresenter(this,getActivity());
            presenter.getInfo();//请求数据
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
    public void onLoadInfoDataFailed() {

    }

    @Override
    public void onLoadInfoDataSuccess(List<InfoVM> info) {
        adapter.addAll(info,true);
    }

    @Override
    public void onClick(View view, InfoVM infoVM) {
        int pos=adapter.getPosition(infoVM);
        TipsToast.showTips(infoVM.getSource()+":"+pos);
    }
}
