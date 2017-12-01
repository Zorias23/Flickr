package com.example.admin.flickr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        im = findViewById(R.id.imageFull);
       String im_url =  getIntent().getStringExtra("imageUrl");
        Glide.with(this).load(im_url).into(im);
    }
}
