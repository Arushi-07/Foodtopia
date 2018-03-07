package com.phoenix.foodtopia.Helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.AttributeSet;

import com.phoenix.foodtopia.R;

/**
 * Created by arushi on 3/7/18.
 */

public class NutrientEditText extends android.support.v7.widget.AppCompatEditText {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NutrientEditText(Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NutrientEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NutrientEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/comic.ttf");
        setTypeface(tf ,Typeface.BOLD);
        setHintTextColor(Color.BLACK);
        setTextColor(Color.BLACK);
        setTextSize(20);
        setRawInputType(InputType.TYPE_CLASS_NUMBER);
        setBackground(getResources().getDrawable(R.drawable.edittext_bg));



    }
}
