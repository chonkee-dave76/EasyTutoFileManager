package com.example.easytutofilemanager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static final String FOLDER_COLOUR = "folder_colour";
    private static final String PREF_FOLDER_COLOUR_KEY = "pref_folder_colour_key";
    private static final String FILE_COLOUR = "file_colour";
    private static final String PREF_FILE_COLOUR_KEY = "pref_file_colour_key";

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
