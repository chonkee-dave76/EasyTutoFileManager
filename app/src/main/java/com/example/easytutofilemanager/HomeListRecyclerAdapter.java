package com.example.easytutofilemanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.FileUtils;

public class HomeListRecyclerAdapter extends RecyclerView.Adapter<HomeListRecyclerAdapter.ViewHolder>{

    public static File adapterRenameFile;
    Context context;
    File[] filesAndFolders;
    public static String newName;
    public HomeListRecyclerAdapter(Context context, File[] filesAndFolders){
        this.context = context;
        this.filesAndFolders = filesAndFolders;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeListRecyclerAdapter.ViewHolder holder, int position) {

        File selectedFile = filesAndFolders[position];
        holder.textView.setText(selectedFile.getName());

        if(selectedFile.isDirectory()){
            int folderColour = SharedPref.loadFolderColourFromPref(context.getApplicationContext());
            holder.imageView.setImageResource(R.drawable.ic_baseline_folder_24);
            holder.imageView.setColorFilter(folderColour);
        }else{
            int fileColour = SharedPref.loadFileColourFromPref(context.getApplicationContext());
            holder.imageView.setImageResource(R.drawable.ic_baseline_insert_drive_file_24);
            holder.imageView.setColorFilter(fileColour);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedFile.isDirectory()){
                    Intent intent = new Intent(context, HomeListActivity.class);
                    String path = selectedFile.getAbsolutePath();
                    pathList.paths.add(path);
                    Toast.makeText(context, path + " " + pathList.paths.size(), Toast.LENGTH_SHORT).show();
                    intent.putExtra("path",path);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else{
                    //open the file
                    try {
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        String type = "image/*";
                        intent.setDataAndType(Uri.parse(selectedFile.getAbsolutePath()), type);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context.getApplicationContext(),"Cannot open the file",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context,v);
                popupMenu.getMenu().add("DELETE");
                popupMenu.getMenu().add("TAGS");
                popupMenu.getMenu().add("RENAME");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("DELETE")){
                            if (selectedFile.isDirectory())
                            {
                                try {
                                    FileUtils.deleteDirectory(selectedFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (!selectedFile.exists()) {
                                    v.setVisibility(View.GONE);
                                    Toast.makeText(context.getApplicationContext(), "DELETED", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                selectedFile.delete();
                                if (!selectedFile.exists()) {
                                    v.setVisibility(View.GONE);
                                    Toast.makeText(context.getApplicationContext(), "DELETED", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        if(item.getTitle().equals("TAGS")){
                            Toast.makeText(context.getApplicationContext(),filesAndFolders.toString(),Toast.LENGTH_LONG).show();

                        }
                        if(item.getTitle().equals("RENAME")){
                            PopUpClass renamePopup = new PopUpClass();
                            adapterRenameFile = selectedFile;
                            renamePopup.showRenamePopup(v);
                        }
                        return true;
                    }
                });

                popupMenu.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return filesAndFolders.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        TextView emojiView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.file_name_text_view);
            imageView = itemView.findViewById(R.id.icon_view);
            emojiView = itemView.findViewById(R.id.emoji_view);
        }
    }
}
