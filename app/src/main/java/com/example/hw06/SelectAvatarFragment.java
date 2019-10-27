package com.example.hw06;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;


public class SelectAvatarFragment extends Fragment {

    private SelectAvatarFragment.OnFragmentInteractionListener mListener;

    public SelectAvatarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Select Avatar");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_avatar, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         ImageView iv_avatar1 = getActivity().findViewById(R.id.iv_avatar1);
         iv_avatar1.setTag("avatar1");

         iv_avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.goToPreviousFragment(view);
            }
        });

        ImageView iv_avatar2 = getActivity().findViewById(R.id.iv_avatar2);
        iv_avatar2.setTag("avatar2");

        iv_avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.goToPreviousFragment(view);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyProfileFragment.OnFragmentInteractionListener) {
            mListener = (SelectAvatarFragment.OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {

        void goToPreviousFragment(View view);
    }
}
