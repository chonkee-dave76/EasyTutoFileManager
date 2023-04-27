package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class addPhoto extends AppCompatActivity {

    Button nextButton;
    Button previousButton;
    Button selectImages;
    ImageSwitcher imagePreview;
    String imageEncoded;
    TextView totalImages;
    ArrayList<Uri> mainArrayURI;
    List<String> imagesEncodedList;
    int PICK_IMAGE_MULTIPLE = 1;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        nextButton.findViewById(R.id.nextButton);
        previousButton.findViewById(R.id.previousButton);
        selectImages.findViewById(R.id.selectButton);
        imagePreview.findViewById(R.id.imagePreview);
        totalImages.findViewById(R.id.numberOfImages);
        mainArrayURI = new ArrayList<Uri>();

        imagePreview.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView1 = new ImageView(getApplicationContext());
                return imageView1;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < mainArrayURI.size() - 1) {
                    position++;
                    imagePreview.setImageURI(mainArrayURI.get(position));
                } else {
                    Toast.makeText(getApplicationContext(), "Last image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0) {
                    position--;
                    imagePreview.setImageURI(mainArrayURI.get(position));
                }
            }
        });

        selectImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mainArrayURI.add(imageurl);
                }
                // setting 1st selected image into image switcher
                imagePreview.setImageURI(mainArrayURI.get(0));
                position = 0;
            } else {
                Uri imageurl = data.getData();
                mainArrayURI.add(imageurl);
                imagePreview.setImageURI(mainArrayURI.get(0));
                position = 0;
            }
        } else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}