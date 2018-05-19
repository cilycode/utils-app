package com.cily.utils.app.fg;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cily.utils.log.L;


/**
 * user:cily
 * time:2017/2/20
 * desc:
 */

public abstract class BaseLazyFragment extends BaseFragment {
    private View mRootView;
    private boolean isPrepared = false;
    private boolean isFirst = true;
    private boolean isVisible = false;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = initView(inflater, container, savedInstanceState);
        }

        return mRootView;
    }

    /**
     * 找控件，结果可能为null
     * @param id
     * @return
     */
    protected <V extends View> V findView(@IdRes int id){
        if (mRootView == null){
            return null;
        }
        try{
            return (V)mRootView.findViewById(id);
        }catch (ClassCastException e){
            L.e(TAG, "Could not cast View to concrete class");
            return null;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible = true;
            lazyLoad();
        }else {
            isVisible = false;
        }
    }

    private void lazyLoad(){
        if (!isVisible){
            return;
        }

        if (isPrepared){
            L.i(TAG, "load");
            load();
        }

        if (isPrepared && !isFirst) {
            L.i(TAG, "loadOnShow");
        }

        if (!isPrepared || !isFirst){
            return;
        }

        L.i(TAG, "loadOnly");
        loadOnly();
        isFirst = false;
    }

    /**对应Fragment的onCreateView方法*/
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**对应Fragment的onActivityCreated方法*/
    protected void load(){}

    /**确保数据只会加载一次*/
    protected void loadOnly(){}
}
