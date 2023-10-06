package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_file_list);

        ImageButton refreshButton = findViewById(R.id.refreshButton);
        Button selectPhotoButton = findViewById(R.id.addPhotoButton);
        Button settingsButton = findViewById(R.id.settingButton);
        Button createNewFolder = findViewById(R.id.createNewFolder);
        recyclerView = findViewById(R.id.recycler_view);
        TextView noFilesText = findViewById(R.id.nofiles_textview);
        Button testButton = findViewById(R.id.testButton);
        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tagManagement.fileTagNum = SharedPref.readNumListFromPref(getApplicationContext());
                filterList(newText);
                return false;
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> savedList = SharedPref.readNumListFromPref(getApplicationContext());
                if (savedList != null) {
                    for (Integer item : savedList) {
                        Log.d("app", String.valueOf(item));
                    }
                } else {
                    Log.d("app", "Saved list is null");
                }
            }
        });



        selectPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddPhoto = new Intent(HomeListActivity.this, AddPhoto.class);
                startActivity(toAddPhoto);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSettings = new Intent(HomeListActivity.this, Settings.class);
                startActivity(toSettings);
            }
        });

        createNewFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toFolderCreate = new Intent(HomeListActivity.this, NewFolderCreation.class);
                startActivity(toFolderCreate);
            }
        });

        String path = getIntent().getStringExtra("path");
        File root = new File(path);
        List<File> filesAndFolders = Arrays.asList(root.listFiles());

        if(filesAndFolders==null || filesAndFolders.size() ==0){
            noFilesText.setVisibility(View.VISIBLE);
            return;
        }

        noFilesText.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HomeListRecyclerAdapter(getApplicationContext(), filesAndFolders));


    }

    private void filterList(String text) {
        ArrayList<File> filteredList = new ArrayList<>();
        ArrayList<ArrayList<String>> tempList = SharedPref.loadArrayList(getApplicationContext());
        if (tempList != null) {
            for (ArrayList<String> innerList : tempList) {
                for (String item : innerList) {
                    if (item.toLowerCase().contains(text.toLowerCase())) ;
                    {
                        filteredList.add(HomeListRecyclerAdapter.filesAndFolders.get(tagManagement.fileTagNum.get(HomeListRecyclerAdapter.editTagsIndex)));
                    }
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
            } else {
                recyclerView.setAdapter(new HomeListRecyclerAdapter(getApplicationContext(), filteredList));
            }
        }
    }

    @Override
    public void onBackPressed() {
        pathList.paths.remove(pathList.paths.size()-1);
        super.onBackPressed();
    }
}