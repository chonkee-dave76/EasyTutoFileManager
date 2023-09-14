package com.example.easytutofilemanager;

import java.util.ArrayList;
import java.util.Collections;

public class tagManagement
{
    public static int tagArrayCount = 0;
    public static ArrayList<String> fileTagPaths = new ArrayList<>(Collections.singletonList("BLOCK"));
    public static ArrayList<Integer> fileTagNum = new ArrayList<>(Collections.singletonList(-1));
    public static ArrayList<ArrayList<String>> fileWithTagsArray = new ArrayList<>(Collections.singletonList(new ArrayList<String>()));

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
        fileWithTagsArray.add(new ArrayList<>());
    }
}
