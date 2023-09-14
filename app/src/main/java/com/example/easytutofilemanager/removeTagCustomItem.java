package com.example.easytutofilemanager;

import android.widget.Button;

public class removeTagCustomItem {
    private String mTagName;

    public removeTagCustomItem(String tagName, Button removeButton) {
        mTagName = tagName;
    }

    public String getmTagName() {
        return mTagName;
    }

    public void setmTagName(String mTagName) {
        this.mTagName = mTagName;
    }
}
