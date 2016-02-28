package com.example.sunnyluo.curtain;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.os.Handler;
import java.util.logging.LogRecord;

/**
 * Created by sunnyluo on 2016-02-27.
 */
public class Alarm extends Fragment {
    private static final String TAG = Login.class.getSimpleName();
    public List<AlarmTime> alarmList =new ArrayList<>();
    private ListView alarmListView;
    private View view;
    public static final String myTime = "AlarmTime" ;
    private boolean isTurnOn=false;
    MediaPlayer player;
    ArrayAdapter<AlarmTime> alarmAdapter;
    Handler mHandler = new Handler();
    SharedPreferences alarmSharedpreferences;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        view=inflater.inflate(R.layout.alarm_layout,container,false);
        alarmListView=(ListView)view.findViewById(R.id.listView);
        player=MediaPlayer.create(getActivity(),R.raw.music);
        alarmSharedpreferences = getActivity().getSharedPreferences(myTime, Context.MODE_PRIVATE);
        buttonSave();
        timer();
        return view;
    }

    public void timer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {

                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                Calendar c = Calendar.getInstance();
                                int min = c.get(Calendar.MINUTE);
                                int hour = c.get(Calendar.HOUR);
                                int month = Calendar.getInstance().MONTH;
                                int day = Calendar.getInstance().DATE;
                                for (int i = 0; i < alarmList.size(); i++) {
                                    if (alarmList.get(i).getIsTurnOn()) {
                                        if (alarmList.get(i).getDay() == day && alarmList.get(i).getMonth() == month && alarmList.get(i).getHour() == hour && alarmList.get(i).getMinute() == min) {
                                            player.start();
                                            isTurnOn = true;
                                            alarmList.get(i).setDay();
                                            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                                            dialog.setCancelable(false);
                                            dialog.setMessage("Ture off alarm?")
                                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            player.pause();
                                                            isTurnOn = false;

                                                        }
                                                    })
                                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            // User cancelled the dialog
                                                        }
                                                    });
                                            dialog.show();
                                        }
                                    }
                                }


                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    private void buttonSave(){
        Button button=(Button)view.findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker=(TimePicker)getActivity().findViewById(R.id.timePicker);
                int hour=timePicker.getCurrentHour();
                int minute=timePicker.getCurrentMinute();
                int month=Calendar.getInstance().MONTH;
                int day=Calendar.getInstance().DATE;
                SharedPreferences.Editor editor=alarmSharedpreferences.edit();

                TimeList(new AlarmTime(month,day,hour,minute));
                ListView();
            }
        });
    }
    private void TimeList(AlarmTime t){
        alarmList.add(t);
    }
    private void ListView(){
        alarmAdapter=new AdapterListView();
        alarmListView.setAdapter(alarmAdapter);
    }
    private class AdapterListView extends ArrayAdapter<AlarmTime>{
        public AdapterListView(){
            super(getActivity(),R.layout.time_list_layout,alarmList);

        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            View itemView=convertView;
            if(itemView==null){
                itemView=getActivity().getLayoutInflater().inflate(R.layout.time_list_layout,parent,false);

            }
            final AlarmTime currentTime =alarmList.get(position);
            TextView timeText=(TextView)itemView.findViewById(R.id.txt_time);
            timeText.setText(currentTime.getTime());
            final Button turnOn=(Button)itemView.findViewById(R.id.btn_off);
            turnOn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    if(currentTime.getIsTurnOn()){
                        turnOn.setText("Turn On");
                        currentTime.setTurnOn();
                    }else{
                        turnOn.setText("Turn Off");
                        currentTime.setTurnOn();
                    }

                }
            });
            final Button delete=(Button)itemView.findViewById(R.id.btn_delete);
            delete.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {

                    alarmList.remove(position);
                    alarmAdapter.notifyDataSetChanged();
                }
            });
            return itemView;
        }
    }


}
