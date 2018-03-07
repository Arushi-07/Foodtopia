package com.phoenix.foodtopia.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phoenix.foodtopia.Activities.UserProfileActivity;
import com.phoenix.foodtopia.R;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class TimeFragment extends Fragment {

    View view;
    private List<Fragment> fragmentList=new ArrayList<>();
    TextView selectMeal;
    ImageView userProfile;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_time_home, container, false);
        initUI();


        createFragmentList();


        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Quikhand.otf");
        selectMeal.setTypeface(tf);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(),UserProfileActivity.class));
                //((DetailsHandler)getContext()).changeFragment("user profile",new UserProfileActivity());
            }
        });


        return view;
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






    private void initUI() {

        userProfile=(ImageView) view.findViewById(R.id.userProfile);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_horizontal_ntb);

        TimeFragment.ViewPagerAdapter adapter = new TimeFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new BreakfastFragment(),"Breakfast");
        adapter.addFrag(new LunchFragment(),"Lunch");
        adapter.addFrag(new SnacksFragment(),"Snacks");
        adapter.addFrag(new DinnerFragment(),"Dinner");
        viewPager.setAdapter(adapter);
        selectMeal= (TextView) view.findViewById (R.id.select);



        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) view.findViewById(R.id.ntb_horizontal);
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



}
