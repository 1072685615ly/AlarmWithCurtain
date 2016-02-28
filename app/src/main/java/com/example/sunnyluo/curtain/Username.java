package com.example.sunnyluo.curtain;

import com.firebase.client.Firebase;

/**
 * Created by sunnyluo on 2016-02-28.
 */

public class Username {
    private String email;
    private String username;
    private String password;
    public Username(String email,String username,String password){
        this.email=email;
        this.username=username;
        this.password=password;
    }
    public String getEmail(){
        return email;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

}

