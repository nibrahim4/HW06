package com.example.hw06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MyProfileFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Profile");

           MyProfileFragment profileFragment = new MyProfileFragment();

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
}
