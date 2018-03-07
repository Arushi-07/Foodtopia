package com.phoenix.foodtopia.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.phoenix.foodtopia.Fragments.BreakfastFragment;
import com.phoenix.foodtopia.Fragments.CuisinesFragment;
import com.phoenix.foodtopia.Fragments.DinnerFragment;
import com.phoenix.foodtopia.Fragments.LunchFragment;
import com.phoenix.foodtopia.Fragments.SnacksFragment;
import com.phoenix.foodtopia.Fragments.TimeFragment;
import com.phoenix.foodtopia.R;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class DetailsHandler extends AppCompatActivity {

    private List<Fragment> fragmentList=new ArrayList<>();
    TextView selectMeal;
    View decorView;
    Bundle passed;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        createFragmentList();
        changeFragment("ask time",new TimeFragment());
        decorView = getWindow().getDecorView();

        changeStatusBarColor();

        /*initUI();

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Quikhand.otf");
        selectMeal.setTypeface(tf);


        if(passed != null)
        {
            String stringValue = passed.getString("key");
            Log.e("bundle",stringValue);
        }*/

      /* */


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

    private void createFragmentList() {

        BreakfastFragment breakfast=new BreakfastFragment();
        LunchFragment lunch=new LunchFragment();
        SnacksFragment snacks = new SnacksFragment();
        DinnerFragment dinner = new DinnerFragment();

        fragmentList.add(breakfast);
        fragmentList.add(lunch);
        fragmentList.add(snacks);
        fragmentList.add(dinner);
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

    public  void  changeFragment(String tag, Fragment fragment){

     /*   FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_right_enter,
                R.animator.fragment_slide_right_exit);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();*/
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit)
                .addToBackStack(tag)
                .commit();


    }

    public void changeActivity()
    {
        startActivity(new Intent(this,CaloriesHandler.class));
    }



    private void initUI() {
        decorView = getWindow().getDecorView();
        passed = getIntent().getExtras();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new BreakfastFragment(),"Breakfast");
        adapter.addFrag(new LunchFragment(),"Lunch");
        adapter.addFrag(new SnacksFragment(),"Snacks");
        adapter.addFrag(new DinnerFragment(),"Dinner");
        viewPager.setAdapter(adapter);
        selectMeal= (TextView) findViewById (R.id.select);



        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.breakfastselected),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.breakfastselected))
                        .title("Breakfast")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.lunchs),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Lunch")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.snackss),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.snackss))
                        .title("Snacks")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.d),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Dinner")
                        .build()
        );


        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (currentFragment instanceof CuisinesFragment){
            getSupportFragmentManager().popBackStack();
        }
        else
         finish();


    }
}
