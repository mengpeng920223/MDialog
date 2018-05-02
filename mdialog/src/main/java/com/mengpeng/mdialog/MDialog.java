package com.mengpeng.mdialog;

import android.app.Activity;
import android.app.Application;

import com.mengpeng.mdialog.loading.LoadingDialog;
import com.mengpeng.mdialog.utils.MDialogAppFrontBackHelper;
import com.mengpeng.mdialog.utils.MShowDialogUtils;

/**
 * 作者: 孟鹏
 * 时间: 2018/4/19 - 11:53.
 * 作用:
 */
public class MDialog {

    private static LoadingDialog loadingDialog;
    private static boolean isFront = false;

    /**
     * 注册应用前后台切换监听
     */
    public static void init(Application application) {
        MDialogAppFrontBackHelper helper = new MDialogAppFrontBackHelper();
        helper.register(application, new MDialogAppFrontBackHelper.OnAppStatusListener() {
            @Override
            public void onFront() {
                isFront = true;
            }

            @Override
            public void onBack() {
                isFront = false;
            }
        });
    }

    private static void getLoadingDialog() {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog();
        }
    }

    public static void showLoadingDialog(Activity activity) {
        getLoadingDialog();
        if (null != loadingDialog && isFront) {
            MShowDialogUtils.showToast(activity, "LoadingDialog", loadingDialog, false);
        }
    }

    public static void showLoadingDialog(Activity activity, boolean isCancelable) {
        getLoadingDialog();
        if (null != loadingDialog && isFront) {
            MShowDialogUtils.showToast(activity, "LoadingDialog", loadingDialog, isCancelable);
        }
    }

    public static void dismissLoadingDialog() {
        if (null != loadingDialog && isFront && loadingDialog.isVisible()) {
            loadingDialog.dismiss();
        }
    }
}
