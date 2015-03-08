package io.samlewis.chrono;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFontHelper {

    public static void setCustomFont(TextView textView, Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customfont);
        String fontFamily = null;
        final int n = a.getIndexCount();
        for (int i = 0; i < n; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.customfont_android_fontFamily) {
                fontFamily = a.getString(attr);
            }
        }
        a.recycle();
        if (!textView.isInEditMode()) {
            try {
                Typeface tf = Typeface.createFromAsset(
                        context.getAssets(), fontFamily);
                textView.setTypeface(tf);
            } catch (Exception ignored) {
            }
        }
    }

}