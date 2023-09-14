package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RemoveTagsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_tags);

        String selectedFilePath = getIntent().getStringExtra("selectedFilePath");

        final ArrayList<removeTagCustomItem> finalArraylist = new ArrayList<removeTagCustomItem>();

        for (int i = 0; i < tagManagement.fileWithTagsArray.get(HomeListRecyclerAdapter.editTagsIndex).size(); i++) {
            finalArraylist.add(new removeTagCustomItem(tagManagement.fileWithTagsArray.get(HomeListRecyclerAdapter.editTagsIndex).get(i), findViewById(R.id.removeTagButton)));
        }



        removeTagAdapter removeTagAdapter = new removeTagAdapter(this, finalArraylist);

        ListView removeTagListView = findViewById(R.id.removeTagsListView);

        removeTagListView.setAdapter(removeTagAdapter);

    }
}