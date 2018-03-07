package com.phoenix.foodtopia.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.phoenix.foodtopia.Activities.DetailsHandler;
import com.phoenix.foodtopia.Activities.LoginHandler;
import com.phoenix.foodtopia.Helper.CustomEditText;
import com.phoenix.foodtopia.Helper.CustomTextView;
import com.phoenix.foodtopia.Helper.LoginButtonFB;
import com.phoenix.foodtopia.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignInFragment extends Fragment {

    Button SignIn;
    View view;
    CustomEditText nameUser,password;
    CustomTextView acc;
    TextView signIn,logo;
    LoginButtonFB loginButton;
    CallbackManager callbackManager;
    RelativeLayout fbLoginLayout;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signin, container, false);
        init();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameUser.getText().toString();
                Log.e("username",name);
               // sharedPref.edit().putString("usernameFoodTopia", name).commit();
                editor.putString("usernameFoodTopia", name).apply();
                editor.commit();
                String username =sharedPref.getString("usernameFoodTopia","Hello");
                Log.e("username2",username);
                getActivity().finish();
                startActivity(new Intent(getContext(), DetailsHandler.class));
            }
        });

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/Quikhand.otf");
        logo.setTypeface(tf);
      //  nameUser.setTypeface(tf);
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(getContext(), "Login Button Clicked", Toast.LENGTH_SHORT).show();

                facebookLogin(loginButton,callbackManager);


            }
        });
        fbLoginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(), "Login Layout Clicked", Toast.LENGTH_SHORT).show();
                loginButton.performClick();
            }
        });

        acc=(CustomTextView) view.findViewById(R.id.account);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginHandler)getContext()).changeFragment("LogIn",new SignUpFragment());
            }
        });




        return view;
    }

    public void init()
    {
        sharedPref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        nameUser=(CustomEditText) view.findViewById(R.id.userName);
        password = (CustomEditText) view.findViewById(R.id.password);
        SignIn=(Button) view.findViewById(R.id.signIn);
        loginButton = (LoginButtonFB) view.findViewById(R.id.login_button);
        logo=(TextView) view.findViewById(R.id.logo);
        loginButton.setReadPermissions("public_profile email user_birthday");


        loginButton.setFragment(this);
        // Other app specific specialization

        callbackManager = CallbackManager.Factory.create();

        fbLoginLayout=(RelativeLayout) view.findViewById(R.id.fbLoginLayout);
    }

    public void facebookLogin(LoginButton login, CallbackManager callbackManager) {

        //  Toast.makeText(getContext(), "in facebook login", Toast.LENGTH_SHORT).show();
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {



            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(getContext(), "successful login", Toast.LENGTH_SHORT).show();
                if (AccessToken.getCurrentAccessToken() != null) {
                    GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {

                                Log.e("check", "response" + response.toString());
                                RequestData();

                                loginButton.setVisibility(View.INVISIBLE);

                                Log.v("LoginActivity", response.toString());

                                // Application code
                                String email = object.getString("email");
                                String birthday = object.getString("birthday");
                                Log.d("bundle", email + " " + birthday);

                                getActivity().startActivity(new Intent(getActivity(), DetailsHandler.class));
                                getActivity().finish();










                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id name link");
                    Log.e("bundle" , "here " + parameters);
                    request.setParameters(parameters);
                    request.executeAsync();
                }

            }

            @Override
            public void onCancel() {

                Toast.makeText(getContext(), "fb login cancelled", Toast.LENGTH_SHORT).show();
                ((LoginHandler)getContext()).changeFragment("SignIn",new SignInFragment());

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getContext(), "Error in fb Login.Try Again", Toast.LENGTH_SHORT).show();
                ((LoginHandler)getContext()).changeFragment("SignIn",new SignInFragment());
            }
        });
    }

    private void RequestData()
    {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                Log.e("check" ,"response = " + response);

                JSONObject json = response.getJSONObject();
                try {
                    if(json != null){
                        String text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
                       /* save_value=text;
                        //detailsDialog.
                        profile.setProfileId(json.getString("id"));
                        info.setText("" + json.getString("name") + "\n"+ json.getString("email") + "\n");*/
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
