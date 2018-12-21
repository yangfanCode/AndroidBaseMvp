package com.yangfan.mvp.versioncontrol.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yangfan.mvp.R;
import com.yangfan.mvp.databinding.AdapterNewVersionInfoItemBinding;


/**
 * 新版本内容
 * Created by yangfan on 2017/9/23.
 * nrainyseason@163.com
 */

public class NewVersionInfoAdapter extends BaseAdapter {
    private LayoutInflater inflate;
    private String[] mList;
    private Context mContext;
    private AdapterNewVersionInfoItemBinding binding;

    public NewVersionInfoAdapter(Context context, String[] list) {
        mList = list;
        mContext = context;
        inflate=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public Object getItem(int position) {
        return mList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            binding=DataBindingUtil.inflate(inflate,R.layout.adapter_new_version_info_item, null,false);
            view=binding.getRoot();
            view.setTag(binding);
        } else {
            binding = (AdapterNewVersionInfoItemBinding) view.getTag();
        }
        binding.tvContent.setText(mList[position].trim());

        return view;
    }
}
