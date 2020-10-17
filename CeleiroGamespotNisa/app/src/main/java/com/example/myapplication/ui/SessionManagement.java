package com.example.myapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_key";
    String SESSION_USERNAME = "session_username";


    public SessionManagement(Context context){

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void saveSession(User user){
        //save session of user whenever user is logged in

        int id = user.getId();
        editor.putInt(SESSION_KEY, id).commit();
        editor.putString(SESSION_USERNAME, user.getName()).commit();
    }

    public int getSession(){
        //return user whose session is saved
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public String getSessionUsername(){return  sharedPreferences.getString(SESSION_USERNAME, "");}

    public void removeSession(){

        editor.putInt(SESSION_KEY, -1).commit();
        editor.putString(SESSION_USERNAME, "").commit();
    }





}
