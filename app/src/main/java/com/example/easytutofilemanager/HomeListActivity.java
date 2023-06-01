package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

public class HomeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_file_list);

        ImageButton refreshButton = findViewById(R.id.refreshButton);
        Button selectPhotoButton = findViewById(R.id.addPhotoButton);
        Button settingsButton = findViewById(R.id.settingButton);
        Button createNewFolder = findViewById(R.id.createNewFolder);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TextView noFilesText = findViewById(R.id.nofiles_textview);

        selectPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddPhoto = new Intent(HomeListActivity.this, addPhoto.class);
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
        File[] filesAndFolders = root.listFiles();

        if(filesAndFolders==null || filesAndFolders.length ==0){
            noFilesText.setVisibility(View.VISIBLE);
            return;
        }

        noFilesText.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HomeListRecyclerAdapter(getApplicationContext(),filesAndFolders));


    }

    @Override
    public void onBackPressed() {
        pathList.paths.remove(pathList.paths.size()-1);
        super.onBackPressed();
    }
}