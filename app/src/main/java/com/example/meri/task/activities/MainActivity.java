package com.example.meri.task.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.meri.task.R;

public class MainActivity extends AppCompatActivity {

    public static final String IMAGE_EXTRA = "image.extra";

    ImageView mFirstImageView, mSecondImageView, mThirdImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mFirstImageView = findViewById(R.id.first_image_view_main_activity);
        mSecondImageView = findViewById(R.id.second_image_view_main_activity);
        mThirdImageView = findViewById(R.id.third_image_view_main_activity);

        mFirstImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra(IMAGE_EXTRA, 1);
                startActivity(intent);
            }
        });

        mSecondImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra(IMAGE_EXTRA, 2);
                startActivity(intent);
            }
        });

        mThirdImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra(IMAGE_EXTRA, 3);
                startActivity(intent);
            }
        });
    }
}
