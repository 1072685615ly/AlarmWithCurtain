package com.example.sunnyluo.curtain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sunnyluo on 2016-02-27.
 */
public class Turn extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.turn_layout,container,false);

        return view;
    }
}
