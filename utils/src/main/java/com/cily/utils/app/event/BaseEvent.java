package com.cily.utils.app.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.cily.utils.base.log.Logs;

/**
 * user:cily
 * time:2017/9/27
 * desc:
 */

public class BaseEvent implements Parcelable {
    public final static int APP_EXIT = -9;
    public int what;
    public Object obj;

    public BaseEvent(){}

    public BaseEvent(Parcel in) {
        what = in.readInt();
        obj = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<BaseEvent> CREATOR = new Creator<BaseEvent>() {
        @Override
        public BaseEvent createFromParcel(Parcel in) {
            return new BaseEvent(in);
        }

        @Override
        public BaseEvent[] newArray(int size) {
            return new BaseEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(what);
        if (obj != null){
            if (obj instanceof Parcelable) {
                Parcelable p = (Parcelable) obj;
                dest.writeParcelable(p, flags);
            }else{
                Logs.sysErr("The obj must be implements Parcelable!");
            }
        }
    }
}
