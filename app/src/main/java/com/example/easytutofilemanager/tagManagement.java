package com.example.easytutofilemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tagManagement
{
    public static int tagArrayCount = 0;
    public static ArrayList<String> fileTagPaths = new ArrayList<>(Arrays.asList("BLOCK"));
    public static ArrayList<Integer> fileTagNum = new ArrayList<>(Arrays.asList(-1));
    public static ArrayList<ArrayList<String>> fileWithTagsArray = new ArrayList<>(Arrays.asList(new ArrayList<String>()));

    public static CharSequence checkForTags (String path) {
        if (tagManagement.fileTagPaths.contains(path)) {
            return "true";
        }
        else {
            return "false";
        }
    }
    public static void createTagArray (String path) {
        fileTagPaths.add(path);
        tagArrayCount++;
        fileTagNum.add(tagArrayCount);
    }
}
