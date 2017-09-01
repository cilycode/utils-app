package com.cily.utils.app.dia;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cily.utils.app.R;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class SingleButtonDialog extends BaseDialog {
    private TextView tv_bottom;
    private EditText ed_msg;

    public SingleButtonDialog(Activity ac) {
        this(ac, R.layout.default_dia_singlebtn);
    }

    public SingleButtonDialog(Activity ac, @LayoutRes int layoutId){
        super(ac, layoutId);
    }

    public void setMsg(String msg){
        if (msg == null){
            return;
        }

        if (ed_msg == null){
            View v = getChildView(R.id.ed_msg);
            if (v != null && v instanceof EditText) {
                ed_msg = (EditText) v;
            }
        }
        if (ed_msg != null){
            ed_msg.setText(msg);
        }
    }

    public void setBtnMsg(String msg, View.OnClickListener listener){
        if (msg == null){
            return;
        }

        if (tv_bottom == null){
            View v = getChildView(R.id.tv_bottom);
            if (v != null && v instanceof TextView) {
                tv_bottom = (TextView) v;
            }
        }
        if (tv_bottom != null){
            tv_bottom.setText(msg);

            if (listener != null){
                tv_bottom.setOnClickListener(listener);
            }
        }
    }
}
