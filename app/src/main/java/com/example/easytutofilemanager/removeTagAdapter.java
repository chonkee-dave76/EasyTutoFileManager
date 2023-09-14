package com.example.easytutofilemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class removeTagAdapter extends ArrayAdapter<removeTagCustomItem> {
    public removeTagAdapter(@NonNull Context context, ArrayList<removeTagCustomItem> arrayList) {

        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        removeTagCustomItem currentNumberPosition = getItem(position);

        TextView textViewRemoveTextAdapter = currentItemView.findViewById(R.id.listViewCustomItem);
        textViewRemoveTextAdapter.setText(currentNumberPosition.getmTagName());

        Button removeButton = currentItemView.findViewById(R.id.removeTagButton);

        View finalCurrentItemView = currentItemView;
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempIndex = tagManagement.fileWithTagsArray.get(HomeListRecyclerAdapter.editTagsIndex).indexOf(currentNumberPosition.getmTagName());
                tagManagement.fileWithTagsArray.get(HomeListRecyclerAdapter.editTagsIndex).remove(tempIndex);
                finalCurrentItemView.setVisibility(View.GONE);
            }
        });

        return currentItemView;

    }

}
