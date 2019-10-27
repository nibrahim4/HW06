package com.example.hw06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MyProfileFragment.OnFragmentInteractionListener , SelectAvatarFragment.OnFragmentInteractionListener{

    public MyProfileFragment profileFragment = new MyProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           getFragmentManager().beginTransaction()
                .add(R.id.container, profileFragment, "myProfile")
                .commit();

    }

    @Override
    public void goToNextFragment() {
           getFragmentManager().beginTransaction()
                .replace(R.id.container, new SelectAvatarFragment(), "selectAvatar")
                .commit();

    }

    @Override
    public void goToPreviousFragment(View view) {

        //profileFragment.v_avatar = view;

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new MyProfileFragment(view), "backToProfile")
                .commit();
    }
}
