package com.example.meri.task.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.meri.task.R;
import com.example.meri.task.view.CustomView;

public class ImageActivity extends AppCompatActivity {

    private static final String POSITIVE_BUTTON_TEXT = "Rotate";

    private static final String NEGATIVE_BUTTON_TEXT = "Cancel";

    private CustomView mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        init();
    }

    private void init() {
        FloatingActionButton rotateButton = findViewById(R.id.rotate_button_image_activity);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        mCustomView = findViewById(R.id.custom_view_image_activity);

        if (getIntent() != null) {
            int num = getIntent().getIntExtra(MainActivity.IMAGE_EXTRA, 0);
            mCustomView.setNumber(num);
        }
    }

    private void openDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_view, null);

        final EditText degreeEditText = dialogView.findViewById(R.id.rotate_degree_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        float degree = Float.valueOf(degreeEditText.getText().toString());

                        mCustomView.rotateView(degree);
                    }
                })
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, null);

        builder.create().show();
    }
}
