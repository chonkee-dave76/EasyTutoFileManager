package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.graphics.Color;

import top.defaults.colorpicker.ColorPickerPopup;

import java.io.File;
import java.util.Objects;

public class NewFolderCreation extends AppCompatActivity {


    String path = pathList.paths.get((pathList.paths.size()) -1);
    String FolderName;
    Button backButton;
    Button createButton;
    EditText folderName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_folder_creation);
        backButton = findViewById(R.id.backButton);
        createButton = findViewById(R.id.createButton);
        folderName = findViewById(R.id.folderName);
        Button pathButton = findViewById(R.id.pathButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), path,Toast.LENGTH_SHORT).show();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FolderName = folderName.getText().toString().trim();
                makeFolder(FolderName);
                finish();
            }
        });
    }

    private void makeFolder(String folderName) {
        File newFile = new File(path, folderName);
        if (!newFile.exists()) {
            newFile.mkdir();
        }
        if (newFile.exists()) {
            Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
        }
    }
}

