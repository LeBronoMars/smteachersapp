package com.avinnovz.sanmateoteachersapp.data.prefs;

import android.content.SharedPreferences;

import java.util.Set;

import javax.inject.Inject;

/**
 * Created by jayan on 4/13/2017.
 */

public class Prefs {

    SharedPreferences sharedPreferences;

    @Inject
    public Prefs(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0f);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setBoolean(String key,
                           boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    public void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public void setFloat(String key, Float value) {
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    public void removePref(String key) {
        sharedPreferences.edit().remove(key);
    }

    public void setStringSet(String key, Set<String> stringSet) {
        sharedPreferences.edit().putStringSet(key, stringSet);
    }

    public Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, null);
    }

}
