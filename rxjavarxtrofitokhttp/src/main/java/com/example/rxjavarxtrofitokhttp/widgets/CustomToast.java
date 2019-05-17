package com.example.rxjavarxtrofitokhttp.widgets;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavarxtrofitokhttp.R;
import com.example.rxjavarxtrofitokhttp.config.CheryConfig;


/**
 * @author:: wangjianchi
 * @time: 2018/1/12  9:25.
 * @description:
 */

public class CustomToast {
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };


    public static void showToast(Context mContext, String text) {
        showToast(mContext, text, 2000);
    }

    public static void showToast(String text) {
        showToast(CheryConfig.getContext(), text, 2000);
    }

    public static void showToast(@StringRes int textRes) {
        showToast(CheryConfig.getContext(), textRes, 2000);
    }

    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

//    public static void showCustomToast(Context context, String msg) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //自定义布局
//        View view = inflater.inflate(R.layout.toast_warning_view, null);
//        TextView textView = (TextView) view.findViewById(R.id.tv_warning_msg);
//        textView.setText(msg);
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.TOP,0,0);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.getScreenWidth(context), UIUtils.dip2px(30));
//        layoutParams.gravity = Gravity.CENTER_VERTICAL;
//        textView.setLayoutParams(layoutParams);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(view);
//        toast.show();
//    }
//    public static void showCustomImageToast(Context context, String msg, int imgesrc) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //自定义布局
//        View view = inflater.inflate(R.layout.toast_image_view, null);
//        TextView textView = (TextView) view.findViewById(R.id.tv_toast);
//        textView.setText(msg);
//        ImageView imageView = view.findViewById(R.id.iv_toast_image);
//        imageView.setImageResource(imgesrc);
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER,0,0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(view);
//        toast.show();
//    }
}
