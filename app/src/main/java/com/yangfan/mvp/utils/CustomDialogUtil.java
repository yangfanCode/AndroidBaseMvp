package com.yangfan.mvp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.yangfan.widget.CustomDialog;


/**
 * Created by yangfan on 2017/5/3.
 */

public class CustomDialogUtil {

    /**
     * final Context context,
     * String content,
     * String okText,
     * String cancelText,
     * final DialogInterface.OnClickListener okListener
     * final DialogInterface.OnClickListener cancelListener
     * boolean isAutoDissmiss
     */
    public static View normalDialog(final Context context, String title, String content, String okText, String cancelText, final DialogInterface.OnClickListener okListener, final DialogInterface.OnClickListener cancelListener, boolean isAutoDissmiss) {

        CustomDialog.Builder customBuilder = new CustomDialog.Builder(context);
        customBuilder.setTitle(title)
                .setMessage(content)
                .setNegativeButton(cancelText, (dialog, which) -> {
                    dialog.dismiss();
                    if (cancelListener != null)
                        cancelListener.onClick(dialog, which);
                })
                .setPositiveButton(okText,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (okListener != null)
                                    okListener.onClick(dialog, which);
                            }
                        });
        CustomDialog dialog = customBuilder.create();
        dialog.setCancelable(isAutoDissmiss);
        dialog.show();

        return dialog.getWindow().getDecorView();
    }

    public static View okDialog(final Context context, String title, String content, String okText, final DialogInterface.OnClickListener okListener, boolean isAutoDissmiss) {

        CustomDialog.Builder customBuilder = new CustomDialog.Builder(context);
        customBuilder.setTitle(title)
                .setMessage(content)
                .setPositiveButton(okText,
                        (dialog, which) -> {
                            dialog.dismiss();
                            if (okListener != null)
                                okListener.onClick(dialog, which);
                        });
        CustomDialog dialog = customBuilder.create();
        dialog.setCancelable(isAutoDissmiss);
        dialog.show();
        customBuilder.getNegativeButton().setVisibility(View.GONE);
        return dialog.getWindow().getDecorView();
    }

}
