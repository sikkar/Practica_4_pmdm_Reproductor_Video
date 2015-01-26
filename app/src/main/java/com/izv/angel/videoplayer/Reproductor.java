package com.izv.angel.videoplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class Reproductor extends ActionBarActivity {

    private VideoView video;
    private Uri path;
    private int savePos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_reproductor);
        video = (VideoView) findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        video.setMediaController(mc);
        Intent intent = getIntent();
        path=intent.getParcelableExtra("path");
        video.setVideoURI(path);
        video.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        if(video.isPlaying()) {
            int pos = video.getCurrentPosition();
            guardarEstado.putInt("posicion", pos);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        if (recEstado != null ) {
            savePos = recEstado.getInt("posicion");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        video.seekTo(savePos);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reproductor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
