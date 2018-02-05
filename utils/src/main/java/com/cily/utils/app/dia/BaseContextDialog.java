package com.cily.utils.app.dia;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cily.utils.app.R;
import com.cily.utils.app.utils.AcUtils;
import com.cily.utils.app.utils.ScreenUtils;

/**
 * Created by admin on 2018/1/12.
 */

public class BaseContextDialog {
    protected final String TAG = this.getClass().getSimpleName();
    private Dialog mDialog;
    private Context cx;
    private View rootView;
    private TextView tv_title;

    public BaseContextDialog(Context cx, @LayoutRes int layoutId){
        if (cx == null){
            return;
        }

        this.cx = cx;
        builder(layoutId);
    }

    private void builder(@LayoutRes int layoutId) {
        mDialog = new Dialog(cx, R.style.CommonDialogStyle);
        Window window = mDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int)(ScreenUtils.getWidthInDp(cx) * 0.8);
            params.height = ScreenUtils.getHeightInDp(cx);
            window.setGravity(Gravity.CENTER);
            params.x = 0;
            params.y = 0;
            window.setAttributes(params);
        }
        mDialog.setContentView(getContentView(layoutId));
    }

    protected Window getDialogWindow(){
        return mDialog == null ? null : mDialog.getWindow();
    }

    @NonNull
    private View getContentView(@LayoutRes int layoutId) {
        if (layoutId <= 0){
            layoutId = R.layout.default_dia_singlebtn;
        }
        rootView = View.inflate(cx, layoutId, null);
        return rootView;
    }

    public void setTitle(String msg){
        if (msg == null){
            return;
        }

        if (tv_title == null){
            if (rootView != null){
                tv_title = (TextView)rootView.findViewById(R.id.tv_title);
            }
        }
        if (tv_title != null){
            tv_title.setText(msg);
        }
    }

    public void show() {
        if (cx != null && mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public boolean isShowing(){
        if (cx != null && mDialog != null){
            return mDialog.isShowing();
        }else {
            return false;
        }
    }

    public Dialog getDialog(){
        return mDialog;
    }

    public void dismiss() {
        if (cx != null && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public BaseContextDialog setCancelable(boolean cancel) {
        if (cx != null && mDialog != null) {
            mDialog.setCancelable(cancel);
        }
        return this;
    }

    public BaseContextDialog setCanceledOnTouchOutside(boolean cancel) {
        if (cx != null && mDialog != null) {
            mDialog.setCanceledOnTouchOutside(cancel);
        }
        return this;
    }

    public void setOnDisListener(DialogInterface.OnDismissListener l){
        if (cx != null && mDialog != null) {
            if (l != null) {
                mDialog.setOnDismissListener(l);
            }
        }
    }

    public View getChildView(@IdRes int childViewId){
        if (rootView != null){
            return rootView.findViewById(childViewId);
        }
        return null;
    }
}