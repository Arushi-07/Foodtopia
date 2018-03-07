package com.phoenix.foodtopia.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phoenix.foodtopia.Activities.UserProfileActivity;
import com.phoenix.foodtopia.Helper.NutrientEditText;
import com.phoenix.foodtopia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NutrientFragment extends Fragment {

    NutrientEditText caloriesText,fatText,proteinsText,ironText,calciumText;
    View view;
    ImageView userProfile;
    TextView quantity;
   /* public static final String calorie ="com.example.android.twoactivities.extra.MESSAGE";
    public static final String fat ="com.example.android.twoactivities.extra.MESSAGE";
    public static final String protein ="com.example.android.twoactivities.extra.MESSAGE";
    public static final String iron ="com.example.android.twoactivities.extra.MESSAGE";
    public static final String calcium ="com.example.android.twoactivities.extra.MESSAGE";
*/

    public NutrientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_nutrient, container, false);
        init();

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Quikhand.otf");
        quantity.setTypeface(tf);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(getContext(), UserProfileActivity.class));
            }
        });

        return view;
    }

    public void init()
    {
        caloriesText=(NutrientEditText)view.findViewById(R.id.calories);
        fatText=(NutrientEditText)view.findViewById(R.id.fat);
        proteinsText=(NutrientEditText)view.findViewById(R.id.proteins);
        ironText=(NutrientEditText)view.findViewById(R.id.iron);
        calciumText=(NutrientEditText)view.findViewById(R.id.calcium);
        quantity =(TextView)view.findViewById(R.id.quantity);
        userProfile=(ImageView) view.findViewById(R.id.userProfileNutrient);
    }

    public Intent getData(Intent intent,String calorie,String fat,String protein,String iron,String calcium)
    {
        String cal = caloriesText.getText().toString();
        String f=fatText.getText().toString();
        String pro=proteinsText.getText().toString();
        String fe=ironText.getText().toString();
        String ca=calciumText.getText().toString();
        intent.putExtra(calorie, cal);
        intent.putExtra(fat, f);
        intent.putExtra(protein, pro);
        intent.putExtra(iron, fe);
        intent.putExtra(calcium, ca);
        return intent;
    }

}
