package com.mengpeng.mdialog.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;

import com.mengpeng.mdialog.base.BaseDialog;


/**
 * 创建:  MengPeng
 * 日期:  2017/9/25 , 下午2:06.
 * 作用:  显示弹窗的工具类
 */
public class MShowDialogUtils {

    public static void showToast(Activity activity, BaseDialog fragment) {
        showToast(activity, null, "", fragment, false);
    }

    public static void showToast(Activity activity, BaseDialog fragment, boolean isCancelable) {
        showToast(activity, null, "", fragment, isCancelable);
    }

    public static void showToast(Activity activity, String tag, BaseDialog fragment, boolean isCancelable) {
        showToast(activity, null, tag, fragment, isCancelable);
    }

    public static void showToast(Activity activity, Bundle bundle, BaseDialog fragment, boolean isCancelable) {
        showToast(activity, bundle, "", fragment, isCancelable);
    }

    public static void showToast(Activity activity, Bundle bundle, String tag, BaseDialog fragment, boolean isCancelable) {
        if (!activity.isFinishing() && !activity.isDestroyed()) {

            if (TextUtils.isEmpty(tag)) {
                tag = fragment.toString();
            }

            FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
            Fragment prev = activity.getFragmentManager().findFragmentByTag(tag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            fragment.setCancelable(isCancelable);
            if (null != bundle) {
                fragment.setArguments(bundle);
            }
            fragment.show(ft, tag);
        }
    }

    public static void dismissToast(Activity activity, String tag) {
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
            BaseDialog prev = (BaseDialog) activity.getFragmentManager().findFragmentByTag(tag);
            if (prev != null) {
                prev.dismiss();
                ft.remove(prev);
            }
            ft.addToBackStack(null);
        }
    }
}
