package com.example.easytutofilemanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class AddPhoto extends AppCompatActivity {

    public String imageURL;
    RecyclerView recyclerView;
    TextView textView;
    Button pickButton;
    Button doneButton;
    ArrayList<Uri> uri = new ArrayList<>();
    ImageSelecterRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        textView = findViewById(R.id.totalPhotos);
        recyclerView = findViewById(R.id.recycler_view_images);
        pickButton = findViewById(R.id.pickButton);
        doneButton = findViewById(R.id.doneButton);

        adapter = new ImageSelecterRecyclerAdapter(uri);
        recyclerView.setLayoutManager(new GridLayoutManager(AddPhoto.this, 4));
        recyclerView.setAdapter(adapter);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < uri.size(); i++){
                    File source = new File(PathUtil.getPath(AddPhoto.this, uri.get(i)));
                    File dest = new File(pathList.paths.get(pathList.paths.size() - 1));
                    Toast.makeText(AddPhoto.this, PathUtil.getPath(AddPhoto.this, uri.get(i)) + " " + pathList.paths.get(pathList.paths.size() - 1), Toast.LENGTH_SHORT).show();
                    try {
                        copy(source, dest);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                finish();
            }
        });

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture(s)"), 1);
                }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data.getClipData() != null) {
                int x = data.getClipData().getItemCount();

                for (int i = 0; i < x; i++) {
                    uri.add(data.getClipData().getItemAt(i).getUri());
                }
                adapter.notifyDataSetChanged();
                textView.setText("Photos (" + uri.size() + ")");

            }
            else if (data.getData() != null) {
                imageURL = data.getData().getPath();
                uri.add(Uri.parse(imageURL));
            }
        }

    }

    public void copy(File src, File dst) throws IOException {
        FileUtils.copyFileToDirectory(src, dst);
    }




}