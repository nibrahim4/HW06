package com.example.hw06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

/**
 * Group 20
 * Nadia Dorado
 * Nia Ibrahim
 */
public class MainActivity extends AppCompatActivity implements MyProfileFragment.OnFragmentInteractionListener , SelectAvatarFragment.OnFragmentInteractionListener, DisplayMyProfileFragment.OnFragmentInteractionListener{

    public MyProfileFragment profileFragment = new MyProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.apply();

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
    public void goToDisplayMyProfileFragment(View view) {

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new DisplayMyProfileFragment(view), "displayProfile")
                .commit();
    }

    @Override
    public void goToPreviousFragment(View view) {

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new MyProfileFragment(view), "backToProfile")
                .commit();
    }

    @Override
    public void goToProfileFragment(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new MyProfileFragment(view), "fromDisplayToProfile")
                .commit();
    }
}
