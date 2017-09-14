package com.cily.utils.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cily.utils.app.fg.BaseFragment;

import java.util.List;

/**
 * user:cily
 * time:2017/9/4
 * desc:ViewPager + Fragment的adapter
 */

public class BaseViewPagerFragmentAdapter<T extends BaseFragment> extends FragmentPagerAdapter {
    private List<T>datas;

    public BaseViewPagerFragmentAdapter(FragmentManager fm, List<T>datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }
}
