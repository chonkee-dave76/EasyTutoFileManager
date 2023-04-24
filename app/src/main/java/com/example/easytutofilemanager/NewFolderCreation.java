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

import com.jakewharton.processphoenix.ProcessPhoenix;

import top.defaults.colorpicker.ColorPickerPopup;

import java.io.File;

public class NewFolderCreation extends AppCompatActivity {


    String FolderName;
    Button backButton;
    Button createButton;
    Button colourPicker;
    EditText folderName;
    public static String selectedFolderPath;
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
                Toast.makeText(getApplicationContext(), selectedFolderPath,Toast.LENGTH_SHORT).show();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FolderName = folderName.getText().toString().trim();
                makeFolder(FolderName);
                Intent nextIntent = new Intent(String.valueOf(HomeListActivity.class));
                ProcessPhoenix.triggerRebirth(getApplicationContext(), nextIntent);
            }
        });
    }

    private void makeFolder(String folderName) {
        File newFile = new File(selectedFolderPath, folderName);
        if (!newFile.exists()) {
            newFile.mkdir();
        } else {
            Toast.makeText(getApplicationContext(), "Folder already exists", Toast.LENGTH_SHORT).show();
        }
    }
}

