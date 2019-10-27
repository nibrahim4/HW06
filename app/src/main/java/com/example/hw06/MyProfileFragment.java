package com.example.hw06;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MyProfileFragment extends Fragment {

    public View v_avatar;
    public ImageView iv_selectAvatar;

    private OnFragmentInteractionListener mListener;

    @SuppressLint("ValidFragment")
    public MyProfileFragment(View view) {

        v_avatar = view;
    }

    public MyProfileFragment(){
        // Required empty public constructor
    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("My Profile");




       return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            iv_selectAvatar = getActivity().findViewById(R.id.iv_selectAvatar);

            if(v_avatar != null){
                if(v_avatar.getTag().equals("avatar1")){

                    iv_selectAvatar.setImageResource(R.drawable.avatar_f_3);
                }

            }


        getActivity().findViewById(R.id.iv_selectAvatar).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mListener.goToNextFragment();
           }
       });
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

        void goToNextFragment();
    }
}
