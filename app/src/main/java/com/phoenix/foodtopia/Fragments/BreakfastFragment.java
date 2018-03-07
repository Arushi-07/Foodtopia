package com.phoenix.foodtopia.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.phoenix.foodtopia.Activities.DetailsHandler;
import com.phoenix.foodtopia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreakfastFragment extends Fragment {


    Button proceed;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_breakfast, container, false);
        init();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailsHandler)getContext()).changeFragment("cuisines",new CuisinesFragment());
              //  startActivity(new Intent(getContext(), CaloriesHandler.class));
            }
        });
        return view;
    }


    public void init()
    {
        proceed = (Button)view.findViewById(R.id.proceed);
    }



}
