package com.phoenix.foodtopia.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.phoenix.foodtopia.Helper.CustomEditText;
import com.phoenix.foodtopia.Helper.CustomTextView;
import com.phoenix.foodtopia.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileActivity extends AppCompatActivity {

    CircleImageView viewImage,camera;
    Button b;
    String mCurrentPhotoPath;
    private Uri mCropImageUri;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    CustomTextView name,workaddr,homeaddr,pref;
    CustomEditText updateHomeAddr,updateWorkAddr,updatePref;
    LinearLayout updateHomeAddrLayout,updateWorkAddrLayout,prefLayout;
    ImageView homeArrow,workArrow,saveWorkAddr,saveHomeAddr,prefarrow,savePref;
    View decorView;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        init();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        changeStatusBarColor();

        String path =sharedPref.getString("profileFoodtopia","");
        if(!path.equals(""))
            viewImage.setImageURI(Uri.fromFile(new File(path)));

        String username =sharedPref.getString("usernameFoodTopia","USERNAME");
        Log.e("username3",username);
        if(!username.equals(""))
            name.setText(username);

        homeaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updateHomeAddrLayout.getVisibility()==View.GONE)
                {
                    homeArrow.setImageDrawable(getResources().getDrawable(R.drawable.downarrow));
                    updateHomeAddrLayout.setVisibility(View.VISIBLE);
                }
                else if(updateHomeAddrLayout.getVisibility()==View.VISIBLE)
                {
                    homeArrow.setImageDrawable(getResources().getDrawable(R.drawable.rightarrow));
                    updateHomeAddrLayout.setVisibility(View.GONE);
                }



            }
        });

        workaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updateWorkAddrLayout.getVisibility()==View.GONE)
                {
                    workArrow.setImageDrawable(getResources().getDrawable(R.drawable.downarrow));
                    updateWorkAddrLayout.setVisibility(View.VISIBLE);
                }
                else if(updateWorkAddrLayout.getVisibility()==View.VISIBLE)
                {
                    workArrow.setImageDrawable(getResources().getDrawable(R.drawable.rightarrow));
                    updateWorkAddrLayout.setVisibility(View.GONE);
                }



            }
        });

        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prefLayout.getVisibility()==View.GONE)
                {
                    prefarrow.setImageDrawable(getResources().getDrawable(R.drawable.downarrow));
                    prefLayout.setVisibility(View.VISIBLE);
                }
                else if(prefLayout.getVisibility()==View.VISIBLE)
                {
                    prefarrow.setImageDrawable(getResources().getDrawable(R.drawable.rightarrow));
                    prefLayout.setVisibility(View.GONE);
                }



            }
        });


        prefarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref.performClick();
            }
        });

    }


    private void init() {
        decorView = getWindow().getDecorView();
        viewImage=(CircleImageView) findViewById(R.id.image);
        camera=(CircleImageView)findViewById(R.id.camera);
        name=(CustomTextView)findViewById(R.id.username) ;
        workaddr=(CustomTextView)findViewById(R.id.workAddr);
        homeaddr=(CustomTextView)findViewById(R.id.homeaddr);
        updateHomeAddr =(CustomEditText)findViewById(R.id.updateHomeAddr);
        updateWorkAddr=(CustomEditText)findViewById(R.id.updateWorkAddr);
        homeArrow=(ImageView)findViewById(R.id.homeArrow);
        workArrow=(ImageView)findViewById(R.id.workArrow);
        updateHomeAddrLayout=(LinearLayout)findViewById(R.id.updateHomeAddrLayout);
        updateWorkAddrLayout = (LinearLayout)findViewById(R.id.updateWorkAddrLayout);
        saveHomeAddr = (ImageView)findViewById(R.id.saveHomeAddr);
        saveWorkAddr = (ImageView) findViewById(R.id.saveWorkAddr);
        pref=(CustomTextView)findViewById(R.id.pref);
        prefarrow=(ImageView)findViewById(R.id.prefarrow);
        prefLayout=(LinearLayout)findViewById(R.id.updatePref);
        updatePref=(CustomEditText)findViewById(R.id.updatePrefA);
        savePref=(ImageView)findViewById(R.id.savePref);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
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

    /** Start pick image activity with chooser. */
    public void onSelectImageClick(View view) {
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        Log.e("image","" + data);


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                ((CircleImageView) findViewById(R.id.image)).setImageURI(result.getUri());
                Log.e("image","" +result.getUri().getPath());



                editor.putString("profileFoodtopia", result.getUri().getPath());
                editor.commit();

                Log.e("sharedpref",sharedPref.getString("profileFoodtopia",""));



                Toast.makeText(
                        this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG)
                        .show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }


}



