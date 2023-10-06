package com.example.easytutofilemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPref {

    private static final String FOLDER_COLOUR = "folder_colour";
    private static final String PREF_FOLDER_COLOUR_KEY = "pref_folder_colour_key";
    private static final String FILE_COLOUR = "file_colour";
    private static final String PREF_FILE_COLOUR_KEY = "pref_file_colour_key";

    private static final String PREFS_NAME1 = "my_prefs1";
    private static final String KEY_MY_LIST1 = "my_list1";
    public static void writePathListInPref(Context context, ArrayList<String> list) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_MY_LIST1, json);
        editor.apply();
    }

    public static ArrayList<String> readPathListFromPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME1, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_MY_LIST1, "");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private static final String PREFS_NAME2 = "my_prefs2";
    private static final String KEY_MY_LIST2 = "my_list2";
    public static void writeNumListInPref(Context context, ArrayList<Integer> list) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME2, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_MY_LIST2, json);
        editor.apply();
    }

    public static ArrayList<Integer> readNumListFromPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME2, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_MY_LIST2, "");
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private static final String PREFS_NAME3 = "my_prefs3";
    private static final String KEY_MY_LIST3 = "my_list3";

    public static void saveArrayList(Context context, ArrayList<ArrayList<String>> list) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME3, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_MY_LIST3, json);
        editor.apply();
    }

    public static ArrayList<ArrayList<String>> loadArrayList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME3, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_MY_LIST3, "");
        Type type = new TypeToken<ArrayList<ArrayList<String>>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void saveFolderColourInPref(Context context, int colour) {
        SharedPreferences pref = context.getSharedPreferences(FOLDER_COLOUR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_FOLDER_COLOUR_KEY, colour);
        editor.apply();
    }

    public static int loadFolderColourFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(FOLDER_COLOUR, Context.MODE_PRIVATE);
        return pref.getInt(PREF_FOLDER_COLOUR_KEY, 0);
    }

    public static void saveFileColourInPref(Context context, int colour) {
        SharedPreferences pref = context.getSharedPreferences(FILE_COLOUR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_FILE_COLOUR_KEY, colour);
        editor.apply();
    }

    public static int loadFileColourFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(FILE_COLOUR, Context.MODE_PRIVATE);
        return pref.getInt(PREF_FILE_COLOUR_KEY, 0);
    }
}
