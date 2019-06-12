package com.qiangu.ntd.base.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import com.qiangu.ntd.R;

/**
 * 倒计时类
 * User: Ljh
 * Date&Time: 2016-03-29 16:45
 */
public class LoginTimeCount extends CountDownTimer {
    private Button btnSendCode;
    private Context context;

    public LoginTimeCount(long millisInFuture, long countDownInterval, Button btnSendCode, Context context) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.btnSendCode = btnSendCode;
        this.context = context;
    }


    @Override public void onFinish() {//计时完毕时触发
        btnSendCode.setText("再次发送");
        btnSendCode.setClickable(true);
        btnSendCode.setEnabled(true);
        btnSendCode.setBackgroundResource(R.drawable.bg_send_code_normal);
        btnSendCode.setTextColor(context.getResources().getColor(R.color.textContentColor2));

    }


    @Override public void onTick(long millisUntilFinished) {//计时过程显示
        btnSendCode.setClickable(false);
        btnSendCode.setText(String.format(
                context.getResources().getString(R.string.re_verification),
                millisUntilFinished / 1000));
        btnSendCode.setEnabled(false);
        btnSendCode.setBackground(null);
    }
}
