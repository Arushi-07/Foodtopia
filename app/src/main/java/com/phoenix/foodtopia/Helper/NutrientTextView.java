package com.phoenix.foodtopia.Helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by arushi on 3/7/18.
 */

public class NutrientTextView extends android.support.v7.widget.AppCompatTextView{


    public NutrientTextView(Context context) {
        super(context);
        init();
    }

    public NutrientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NutrientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/comic.ttf");
        setTypeface(tf ,Typeface.BOLD);
        setHintTextColor(Color.BLACK);
        setTextColor(Color.BLACK);
        setTextSize(20);





    }


}
