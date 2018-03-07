package com.phoenix.foodtopia.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phoenix.foodtopia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        TextView logo=(TextView) view.findViewById(R.id.logo);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/Quikhand.otf");
        logo.setTypeface(tf);

        return view;
    }

}
