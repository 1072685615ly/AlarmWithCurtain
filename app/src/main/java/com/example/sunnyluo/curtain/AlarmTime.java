package com.example.sunnyluo.curtain;

/**
 * Created by sunnyluo on 2016-02-27.
 */
public class AlarmTime {
    private int month;
    private int day;
    private int hour;
    private int minute;
    private boolean isTurnOn;
    public AlarmTime(int month,int day,int hour,int minute){
        super();
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.minute=minute;
        isTurnOn=true;
    }
    public String getTime(){
        if (hour<10){
            return "0"+hour+":"+minute;
        }
        if(minute<10){
            return hour+":0"+minute;
        }
        return hour+":"+minute;

    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public Boolean getIsTurnOn(){
        return isTurnOn;
    }
    public void setTurnOn(){
        isTurnOn=(!isTurnOn);
    }
    public void setDay(){
        day++;
    }
}
