package com.example.hw06;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MyProfileFragment extends Fragment  {

    public View v_avatar;
    public ImageView iv_selectAvatar;
    public EditText et_firstName;
    public EditText et_lastName;
    public EditText et_studentId;
    public Button btn_save;
    public String firstName;
    public String lastName;
    public String studentId;
    public RadioGroup rg_dept;
    public RadioButton rb_cs;
    public RadioButton rb_sis;
    public RadioButton rb_bio;
    public RadioButton rb_other;
    public String selectedDept = "";
    public Boolean isErrorThrown = false;

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

        et_firstName = getActivity().findViewById(R.id.et_firstName);
        et_lastName = getActivity().findViewById(R.id.et_lastName);
        et_studentId = getActivity().findViewById(R.id.et_studentNumber);
        rg_dept = getActivity().findViewById(R.id.rg_departments);
        rb_sis = getActivity().findViewById(R.id.rb_sis);
        rb_other = getActivity().findViewById(R.id.rb_other);
        rb_cs = getActivity().findViewById(R.id.rb_cs);
        rb_bio = getActivity().findViewById(R.id.rb_bio);
        iv_selectAvatar = getActivity().findViewById(R.id.iv_selectAvatar);

        rg_dept.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,  int i){
                switch(radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_bio:
                        selectedDept = "BIO";
                        break;
                    case R.id.rb_cs:
                        selectedDept = "CS";
                        break;
                    case R.id.rb_other:
                        selectedDept = "OTHER";
                        break;
                    case R.id.rb_sis:
                        selectedDept = "SIS";
                        break;
                    default:
                        break;
                }
            }
        });

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        if(sharedPref != null){

            String firstName = sharedPref.getString("saved_firstName",null);
            String lastName = sharedPref.getString("saved_lastName",null);
            String studentId = sharedPref.getString("saved_studentId", null);
            String dept = sharedPref.getString("saved_dept", "");

            et_firstName.setText(firstName);
            et_lastName.setText(lastName);
            et_studentId.setText(studentId);

            switch (dept){
                case "BIO":
                    rb_bio.setChecked(true);
                    break;
                case "OTHER":
                    rb_other.setChecked(true);
                    break;
                case "CS":
                    rb_cs.setChecked(true);
                    break;
                case "SIS":
                    rb_sis.setChecked(true);
                    break;
                    default :
                        break;
            }
        }


        if (v_avatar != null) {
            if (v_avatar.getTag().equals("avatar1")) {
                iv_selectAvatar.setImageResource(R.drawable.avatar_f_3);
            } else if (v_avatar.getTag().equals("avatar2")) {
                iv_selectAvatar.setImageResource(R.drawable.avatar_f_2);
            } else if (v_avatar.getTag().equals("avatar3")) {
                iv_selectAvatar.setImageResource(R.drawable.avatar_f_1);
            } else if (v_avatar.getTag().equals("avatar4")) {
                iv_selectAvatar.setImageResource(R.drawable.avatar_m_1);
            } else if (v_avatar.getTag().equals("avatar5")) {
                iv_selectAvatar.setImageResource(R.drawable.avatar_m_2);
            } else if (v_avatar.getTag().equals("avatar6")) {
                iv_selectAvatar.setImageResource(R.drawable.avatar_m_3);
            }

        }





        getActivity().findViewById(R.id.iv_selectAvatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToNextFragment();
            }
        });

        btn_save = getActivity().findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             firstName = et_firstName.getText().toString();
             lastName = et_lastName.getText().toString();
             studentId = et_studentId.getText().toString();

            if(firstName.equals("")){
                et_firstName.setError("Please enter a valid first name.");
                isErrorThrown = true;
            }else{
                isErrorThrown = false;
            }
            if(lastName.equals("")){
                et_lastName.setError("Please enter a valid last name.");
                isErrorThrown = true;
            }else{
                isErrorThrown= false;
            }
            //try {
                if(studentId.equals("") || studentId == null || Integer.parseInt(studentId) < 0 || studentId.length() != 9){
                    isErrorThrown = true;
                    et_studentId.setError("Please enter a 9 digit student id.");

                }else{
                    isErrorThrown = false;
                }
           // }catch(Exception e){
             //   isErrorThrown = true;
           // }

            if (selectedDept.equals("") || selectedDept == null){
                Toast.makeText(getActivity(), "Please select a valid department", Toast.LENGTH_LONG).show();
                isErrorThrown = true;
            }else{
                isErrorThrown = false;
            }


             if(!isErrorThrown){
                 SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = sharedPref.edit();

                 editor.putString("saved_firstName", firstName);
                 editor.putString("saved_lastName", lastName);
                 editor.putString("saved_studentId", studentId);
                 editor.putString("saved_dept", selectedDept);
                 editor.apply();

                 mListener.goToDisplayMyProfileFragment(v_avatar);
             }

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
        void goToDisplayMyProfileFragment(View view);
    }
}
