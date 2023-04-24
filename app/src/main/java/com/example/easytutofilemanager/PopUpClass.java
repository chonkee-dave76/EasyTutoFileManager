package com.example.easytutofilemanager;

import android.content.Intent;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.io.File;

public class PopUpClass {
    public void showRenamePopup(final View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.rename_popup, null);
        int width = RelativeLayout.LayoutParams.MATCH_PARENT;
        int height = RelativeLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        EditText renameEditText = popupView.findViewById(R.id.renameEditText);
        Button confirmButton = popupView.findViewById(R.id.confirmButton);
        Button cancelButton = popupView.findViewById(R.id.cancelButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File renameToFile = new File(MyAdapter.adapterRenameFile.getParent(), renameEditText.getText().toString());
                boolean success = (MyAdapter.adapterRenameFile).renameTo(renameToFile);
                if (success) {
                    Toast.makeText(popupView.getContext(), "SUCCESS",Toast.LENGTH_SHORT).show();
                }
                popupWindow.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
