package com.phoenix.foodtopia.Helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.phoenix.foodtopia.R;

/**
 * Created by arushi on 3/5/18.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/comic.ttf");
        setTypeface(tf ,Typeface.BOLD);
        setHintTextColor(Color.BLACK);
        setTextColor(Color.BLACK);
        setBackgroundTintList(BackgroundTint());



    }

    private ColorStateList BackgroundTint()
    {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}
        };

        int[] colors = new int[] {
                (getContext().getResources().getColor(R.color.black))

        };

        ColorStateList buttonTint = new ColorStateList(states, colors);
        return buttonTint;
    }

}
