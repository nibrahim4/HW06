package com.example.hw06;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;


public class DisplayMyProfileFragment extends Fragment {

    public TextView tv_name;
    public TextView tv_studentId;
    public TextView tv_dept;
    public ImageView iv_selectedAvatar;
    public View v_selectedAvatar;
    public Button btn_edit;

    private OnFragmentInteractionListener mListener;

    public DisplayMyProfileFragment() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public DisplayMyProfileFragment(View view) {

        v_selectedAvatar = view;
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Display My Profile");


        return inflater.inflate(R.layout.fragment_display_my_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String firstName = sharedPref.getString("saved_firstName",null);
        String lastName = sharedPref.getString("saved_lastName",null);
        String studentId = sharedPref.getString("saved_studentId", null);
        String dept = sharedPref.getString("saved_dept", null);

        tv_name = getActivity().findViewById(R.id.tv_Name);
        tv_name.setText("Name: " + firstName + " " + lastName);

        tv_studentId = getActivity().findViewById(R.id.tv_studentId);
        tv_studentId.setText("Student ID: " + studentId);

        tv_dept = getActivity().findViewById(R.id.tv_selectedDept);
        tv_dept.setText("Department: " + dept);

        iv_selectedAvatar = getActivity().findViewById(R.id.iv_displayAvatar);

        btn_edit = getActivity().findViewById(R.id.btn_Edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.goToProfileFragment();
            }
        });

        if (v_selectedAvatar != null) {
            if (v_selectedAvatar.getTag().equals("avatar1")) {
                iv_selectedAvatar.setImageResource(R.drawable.avatar_f_3);
            } else if (v_selectedAvatar.getTag().equals("avatar2")) {
                iv_selectedAvatar.setImageResource(R.drawable.avatar_f_2);
            } else if (v_selectedAvatar.getTag().equals("avatar3")) {
                iv_selectedAvatar.setImageResource(R.drawable.avatar_f_1);
            } else if (v_selectedAvatar.getTag().equals("avatar4")) {
                iv_selectedAvatar.setImageResource(R.drawable.avatar_m_1);
            } else if (v_selectedAvatar.getTag().equals("avatar5")) {
                iv_selectedAvatar.setImageResource(R.drawable.avatar_m_2);
            } else if (v_selectedAvatar.getTag().equals("avatar6")) {
                iv_selectedAvatar.setImageResource(R.drawable.avatar_m_3);
            }

        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
       void goToProfileFragment();
    }
}
