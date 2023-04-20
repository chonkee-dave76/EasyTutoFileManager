package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;

import top.defaults.colorpicker.ColorPickerPopup;

public class Settings extends AppCompatActivity {

    int defaultColour = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button confirmButton = findViewById(R.id.confirmButton);
        Button colourPicker = findViewById(R.id.folderColour);
        View colourPreview = findViewById(R.id.previewColour);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        colourPicker.setOnClickListener(new View.OnClickListener() {
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
                                defaultColour = color;
                                colourPreview.setBackgroundColor(defaultColour);

                            }
                        });
            }
        });

    }
}