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
 * Created by arushi on 3/2/18.
 */

public class CustomRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomRadioButton(Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/comic.ttf");
        setTypeface(tf ,Typeface.BOLD);
        setTextSize(20);
        setTextColor(Color.BLACK);

        setButtonTintList(ColorTint());


    }

    public ColorStateList ColorTint()
    {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[] {
                (getContext().getResources().getColor(R.color.FerrariRed)),
                (getContext().getResources().getColor(R.color.black)),
                Color.GREEN,
                Color.BLUE
        };

        ColorStateList buttonTint = new ColorStateList(states, colors);
        return buttonTint;
    }


}
