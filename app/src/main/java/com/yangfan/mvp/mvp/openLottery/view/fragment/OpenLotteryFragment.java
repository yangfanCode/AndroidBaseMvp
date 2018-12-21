package com.yangfan.mvp.mvp.openLottery.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangfan.mvp.R;
import com.yangfan.mvp.databinding.FragmentOpenLotteryBinding;

/**
 * 开奖fragment
 */
public class OpenLotteryFragment extends Fragment {

    private FragmentOpenLotteryBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding == null) {
            binding=DataBindingUtil.inflate(inflater,R.layout.fragment_open_lottery,container,
                    false);
            //初始化
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
}
