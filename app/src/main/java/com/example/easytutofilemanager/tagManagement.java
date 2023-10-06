package com.example.easytutofilemanager;

import android.app.Application;

import java.util.ArrayList;
import java.util.Collections;

public class tagManagement
{

    public static ArrayList<String> fileTagPaths = new ArrayList<>(Collections.singletonList("BLOCK"));
    public static ArrayList<Integer> fileTagNum = new ArrayList<>(Collections.singletonList(-1));
    public static ArrayList<ArrayList<String>> fileWithTagsArray = new ArrayList<>(Collections.singletonList(new ArrayList<String>()));
    public static CharSequence checkForTags (String path) {
        if (tagManagement.fileTagPaths != null) {
            if (tagManagement.fileTagPaths.contains(path)) {
                return "true";
            } else {
                return "false";
            }
        }
        return "false";
    }
    public static void createTagArray(String path, Integer pos) {
        fileTagPaths.add(path);
        fileTagNum.add(pos);
        fileWithTagsArray.add(new ArrayList<>());

    }
}
