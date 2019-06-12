package com.qiangu.ntd.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.qiangu.ntd.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/19.
 */

public class DecimalEditText extends AppCompatEditText
        implements View.OnTouchListener,
        View.OnFocusChangeListener,
        TextWatcher {

    private Drawable mClearTextIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;

    private Button mBtnConfirm;


    public Button getBtnConfirm() {
        return mBtnConfirm;
    }


    public void setBtnConfirm(Button btnConfirm) {
        mBtnConfirm = btnConfirm;
    }


    public void setHint(String str) {
        SpannableString ss = new SpannableString(str);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(14, true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setHint(new SpannedString(ss));
    }


    public DecimalEditText(final Context context) {
        super(context);
        init(context);
    }

    public DecimalEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DecimalEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(final Context context) {
        final Drawable drawable = ContextCompat.getDrawable(context,
                R.drawable.edt_delete);
        final Drawable wrappedDrawable = DrawableCompat.wrap(
                drawable); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        mClearTextIcon = wrappedDrawable;
        mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(),
                mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
        InputFilter[] filters = { new CashierInputFilter() };
        setFilters(filters);
    }


    @Override public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }


    @Override public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }


    @Override public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        }
        else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }


    @Override public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() -
                mClearTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText("");
            }
            return true;
        }
        return mOnTouchListener != null &&
                mOnTouchListener.onTouch(view, motionEvent);
    }


    @Override
    public final void onTextChanged(CharSequence s, int start, int lengthBefore, int lengthAfter) {
        if (isFocused()) {
            setClearIconVisible(s.length() > 0);
        }
        if(mBtnConfirm!=null){
            if (s.toString().trim().length() > 0) {
                final String m = s.toString();
                if (validateDouble(m)) {
                    Double money = Double.parseDouble(m);
                    if (money > 0) {
                        mBtnConfirm.setEnabled(true);
                    }
                }
                else {
                    mBtnConfirm.setEnabled(false);
                }
            }
            else {
                mBtnConfirm.setEnabled(false);
            }
        }

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override public void afterTextChanged(Editable s) {

    }


    private void setClearIconVisible(final boolean visible) {
        mClearTextIcon.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1],
                visible ? mClearTextIcon : null, compoundDrawables[3]);
    }


    private class CashierInputFilter implements InputFilter {
        Pattern mPattern;
        //输入的最大金额
        private static final int MAX_VALUE = Integer.MAX_VALUE;
        //小数点后的位数
        private static final int POINTER_LENGTH = 2;
        private static final String POINTER = ".";
        private static final String ZERO = "0";


        public CashierInputFilter() {
            mPattern = Pattern.compile("([0-9]|\\.)*");
        }


        /**
         * @param source 新输入的字符串
         * @param start 新输入的字符串起始下标，一般为0
         * @param end 新输入的字符串终点下标，一般为source长度-1
         * @param dest 输入之前文本框内容
         * @param dstart 原内容起始坐标，一般为0
         * @param dend 原内容终点坐标，一般为dest长度-1
         * @return 输入内容
         */
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String sourceText = source.toString();
            String destText = dest.toString();
            //验证删除等按键
            if (TextUtils.isEmpty(sourceText)) {
                return "";
            }
            Matcher matcher = mPattern.matcher(source);
            //已经输入小数点的情况下，只能输入数字
            if (destText.contains(POINTER)) {
                if (!matcher.matches()) {
                    return "";
                }
                else {
                    if (POINTER.equals(source)) {  //只能输入一个小数点
                        return "";
                    }
                }
                //验证小数点精度，保证小数点后只能输入1位
                int index = destText.indexOf(POINTER);
                int length = dend - index;
                if (length > POINTER_LENGTH) {
                    return dest.subSequence(dstart, dend);
                }
            }
            else {
                //没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
                if (!matcher.matches()) {
                    return "";
                }
                else {
                    if ((POINTER.equals(source)) &&
                            TextUtils.isEmpty(destText)) {
                        return "";
                    }
                    //如果首位为“0”，则只能再输“.”
                    if (ZERO.equals(destText)) {
                        if (!POINTER.equals(sourceText)) {
                            return "";
                        }
                    }
                }
            }
            //验证输入金额的大小
            double sumText = Double.parseDouble(destText + sourceText);
            if (sumText > MAX_VALUE) {
                return dest.subSequence(dstart, dend);
            }
            return dest.subSequence(dstart, dend) + sourceText;
        }
    }


    private boolean validateDouble(String str) throws RuntimeException {
        if (str == null)
        // throw new RuntimeException("null input");
        {
            return false;
        }
        char c;
        int k = 0;
        for (int i = 0, l = str.length(); i < l; i++) {
            c = str.charAt(i);
            if (!((c >= '0') && (c <= '9'))) {
                if (!(i == 0 && (c == '-' || c == '+'))) {
                    if (!(c == '.' && i < l - 1 && k < 1))
                    // throw new RuntimeException("invalid number");
                    {
                        return false;
                    }
                    else {
                        k++;
                    }
                }
            }
        }
        return true;
    }
}
