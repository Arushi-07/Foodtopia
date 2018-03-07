package com.phoenix.foodtopia.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.phoenix.foodtopia.Activities.CaloriesHandler;
import com.phoenix.foodtopia.Activities.UserProfileActivity;
import com.phoenix.foodtopia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CuisinesFragment extends Fragment {

    View view;
    TextView selectMeal;
    ImageView userProfile;
    Button proceed;
    public CuisinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_cuisines, container, false);
        init();
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Quikhand.otf");
        selectMeal.setTypeface(tf);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               getActivity().startActivity(new Intent(getContext(), UserProfileActivity.class));
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), CaloriesHandler    .class));
            }
        });
        return view;
    }

    public void init()
    {
        userProfile=(ImageView) view.findViewById(R.id.userProfile);
        selectMeal= (TextView) view.findViewById (R.id.select);
        proceed = (Button)view.findViewById(R.id.proceed);
    }

}
