package com.example.easytutofilemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class tagManagement
{
    public static final ArrayList<String> fileTagPaths = new ArrayList<>();
    public static final ArrayList<Integer> fileTagNum = new ArrayList<>();
    public static final Integer tagsFileCount = 0;

    public static void saveTagsFileCount(Context context, int count) {
        SharedPreferences pref = context.getSharedPreferences(tagsFileCount, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_FOLDER_COLOUR_KEY, colour);
        editor.apply();
    }

    public static ArrayList<ArrayList> fileWithTagsArray = new ArrayList<>();

    public static CharSequence checkForTags (String path) {
        if (tagManagement.fileWithTagsArray.contains(path)) {
            return "true";
        }
        else {
            return "false";
        }
    }
    public static void createTagArray (ArrayList<String> path) {

    }
}
