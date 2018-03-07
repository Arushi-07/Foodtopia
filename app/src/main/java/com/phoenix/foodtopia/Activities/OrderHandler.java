package com.phoenix.foodtopia.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phoenix.foodtopia.R;

public class OrderHandler extends AppCompatActivity {

    TextView choosePayment;
    RelativeLayout visa,mastercard,paytm;
    View decorView;
    ImageView userProfile;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_handler);
        init();
        changeStatusBarColor();
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Quikhand.otf");
        choosePayment.setTypeface(tf);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               startActivity(intent);
            }
        });

        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderHandler.this, "Visa Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderHandler.this, "MasterCard Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderHandler.this, "Paytm Clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init() {

        intent= new Intent(this,UserProfileActivity.class);
        choosePayment=(TextView)findViewById(R.id.choosePayment);
        decorView = getWindow().getDecorView();
        userProfile=(ImageView) findViewById(R.id.userProfileOrder);
        visa=(RelativeLayout) findViewById(R.id.visa);
        mastercard=(RelativeLayout) findViewById(R.id.mastercard);
        paytm=(RelativeLayout) findViewById(R.id.paytm);
    }

    private void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }*/
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
