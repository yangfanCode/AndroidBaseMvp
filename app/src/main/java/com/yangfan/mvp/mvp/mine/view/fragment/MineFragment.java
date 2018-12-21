package com.yangfan.mvp.mvp.mine.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangfan.mvp.R;
import com.yangfan.mvp.databinding.FragmentMineBinding;

/**
 * 我的fragment
 */
public class MineFragment extends Fragment {

    private FragmentMineBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding == null) {
            binding=DataBindingUtil.inflate(inflater,R.layout.fragment_mine,container,
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
