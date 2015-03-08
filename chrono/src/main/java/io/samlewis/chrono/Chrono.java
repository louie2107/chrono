package io.samlewis.chrono;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lumis on 3/7/2015.
 */
public class Chrono extends TextView {

    private long currentCount;
    private long interval = 1000;
    private boolean isCounting = false;
    private boolean isCountingUp = true;
    private Handler handler = new Handler();
    private OnChronoActiveListener onChronoActiveListener;
    private OnChronoChangeListener onChronoChangeListener;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isCountingUp) {
                currentCount++;
            } else {
                currentCount--;
            }
            if (onChronoChangeListener != null) {
                onChronoChangeListener.onChronoChange(isCounting, currentCount);
            }
            setText(timeConversion(currentCount));
            handler.postDelayed(this, interval);
        }
    };

    public Chrono(Context context) {
        super(context);
    }

    public Chrono(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Chrono(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private static String timeConversion(long totalSeconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;


        long seconds = totalSeconds % SECONDS_IN_A_MINUTE;
        long totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
        long minutes = totalMinutes % MINUTES_IN_AN_HOUR;
        long hours = totalMinutes / MINUTES_IN_AN_HOUR;

        StringBuilder time = new StringBuilder();
        time.append(totalSeconds < 0 ? "-" : "");
        time.append(hours < 10 ? "0" : "");
        time.append(Math.abs(hours) + ":");
        time.append(minutes < 10 ? "0" : "");
        time.append(Math.abs(minutes) + ":");
        time.append(seconds < 10 ? "0" : "");
        time.append(Math.abs(seconds));

        return time.toString();
    }

    public void reset() {
        stopCounting();
        setText("00:00:00");
        currentCount = 0;
    }

    public void startCounting() {
        isCounting = true;
        handler.post(runnable);
        chronoActiveoChange();
    }

    public void stopCounting() {
        isCounting = false;
        handler.removeCallbacks(runnable);
        chronoActiveoChange();
    }

    private void chronoActiveoChange() {
        if (onChronoActiveListener != null) {
            onChronoActiveListener.onChronoActiveChange(isCounting, currentCount);
        }
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public boolean toggle() {
        if (isCounting()) {
            stopCounting();
        } else {
            startCounting();
        }
        return isCounting();
    }

    public boolean toggleDirection() {
        isCountingUp = !isCountingUp;
        return isCountingUp();
    }

    public boolean isCounting() {
        return isCounting;
    }

    public boolean isCountingUp() {
        return isCountingUp;
    }

    public long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(long currentCount) {
        this.currentCount = currentCount;
    }

    public void setOnChronoActiveListener(OnChronoActiveListener onChronoActiveListener) {
        this.onChronoActiveListener = onChronoActiveListener;
    }

    public void setOnChronoChangeListener(OnChronoChangeListener onChronoChangeListener) {
        this.onChronoChangeListener = onChronoChangeListener;
    }

    private void init(Context context, AttributeSet attrs) {
        CustomFontHelper.setCustomFont(this, context, attrs);
        if (isInEditMode()) {
            setText("00:00:00");
        }
        setText("00:00:00");
    }

    @Override
    public Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putLong("CURRENT_COUNT", currentCount);
        bundle.putBoolean("COUNTING_UP", isCountingUp);
        bundle.putBoolean("IS_COUNTING", isCounting);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            currentCount = bundle.getLong("CURRENT_COUNT");
            isCountingUp = bundle.getBoolean("COUNTING_UP");
            isCounting = bundle.getBoolean("IS_COUNTING");
            state = bundle.getParcelable("instanceState");
            if (isCounting) {
                startCounting();
            }
        }
        super.onRestoreInstanceState(state);
    }

    public interface OnChronoChangeListener {
        void onChronoChange(boolean isCounting, long currentCount);
    }

    public interface OnChronoActiveListener {
        void onChronoActiveChange(boolean isCounting, long currentCount);
    }


}
