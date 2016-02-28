package com.example.sunnyluo.curtain;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by sunnyluo on 2016-02-27.
 */
public class Login extends Fragment {
    private static final String TAG = Login.class.getSimpleName();
    private View view;
    private Firebase ref;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        view=inflater.inflate(R.layout.login_layout,container,false);
        RegisterButton();
        Firebase.setAndroidContext(getActivity());
        ref=new Firebase("https://amber-heat-7158.firebaseio.com");

        return view;
    }
    public void RegisterButton(){
        Button button_register = (Button) view.findViewById(R.id.btn_register);
        button_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, new Register()).commit();
            }
        });
    }

}
