package com.example.easytutofilemanager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PopupEditTags {
    public void showEditTagsPopup(final View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.edit_tags_popup, null);
        int width = RelativeLayout.LayoutParams.MATCH_PARENT;
        int height = RelativeLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        EditText renameEditText = popupView.findViewById(R.id.editTagEditText);
        Button confirmButton = popupView.findViewById(R.id.editTagConfirmButton);
        Button cancelButton = popupView.findViewById(R.id.editTagCancelButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = String.valueOf(renameEditText.getText());
                if (tagManagement.fileWithTagsArray.get(HomeListRecyclerAdapter.editTagsIndex).contains(tag) == true) {
                    Toast.makeText(popupView.getContext(), "DUPLICATE TAG", Toast.LENGTH_SHORT).show();
                } else {
                    tagManagement.fileWithTagsArray.get(HomeListRecyclerAdapter.editTagsIndex).add(String.valueOf(renameEditText.getText()));
                    SharedPref.saveArrayList(confirmButton.getContext(), tagManagement.fileWithTagsArray);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
