package com.example.sunnyluo.curtain;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by sunnyluo on 2016-02-27.
 */
public class Register extends Fragment{
   private Firebase ref;

    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        view=inflater.inflate(R.layout.register_layout,container,false);
        buttonRegister();
        Firebase.setAndroidContext(getActivity());
        ref=new Firebase("https://amber-heat-7158.firebaseio.com");

        return view;
    }
    public void buttonRegister(){
        Button button_register = (Button) view.findViewById(R.id.btn_register);
        button_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String email= getActivity().findViewById(R.id.txt_email).toString();
                String username= getActivity().findViewById(R.id.txt_username).toString();
                String password= getActivity().findViewById(R.id.txt_password).toString();
                Username user=new Username(email,username,password);
                Firebase userRef=ref.child("users").child(username+"ref");
                userRef.setValue(user);
            }
        });


    }



}
