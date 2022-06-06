package com.example.farm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Stream extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String mediaURL = "rtsp://172.20.10.2:8081/";
        VideoView v = (VideoView) findViewById( R.id.videoView );
//        v.setVideoPath("rtsp://172.20.10.2:8081/unicast");
//        //v.setMediaController( new MediaController( this ) );
//        v.requestFocus();
//        v.start();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mediaURL));
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}