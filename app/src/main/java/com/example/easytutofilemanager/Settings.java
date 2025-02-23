package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import top.defaults.colorpicker.ColorPickerPopup;

public class Settings extends AppCompatActivity {

    static int folderColour;
    static  int fileColour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button backButton = findViewById(R.id.backButton);
        Button confirmButton = findViewById(R.id.confirmButton);
        Button folderColourPicker = findViewById(R.id.folderColour);
        Button fileColourPicker = findViewById(R.id.fileColour);
        View folderColourPreview = findViewById(R.id.previewFolderColour);
        View fileColourPreview = findViewById(R.id.previewFileColour);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        folderColourPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(Settings.this)
                        .initialColor(Color.RED)
                        .enableAlpha(true)
                        .enableBrightness(true)
                        .okTitle("Confirm")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                folderColour = color;
                                SharedPref.saveFolderColourInPref(getApplicationContext(), folderColour);
                                folderColourPreview.setBackgroundColor(folderColour);

                            }
                        });
            }
        });

        fileColourPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(Settings.this)
                        .initialColor(Color.RED)
                        .enableAlpha(true)
                        .enableBrightness(true)
                        .okTitle("Confirm")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                fileColour = color;
                                SharedPref.saveFileColourInPref(getApplicationContext(), fileColour);
                                fileColourPreview.setBackgroundColor(fileColour);

                            }
                        });
            }
        });
    }
}