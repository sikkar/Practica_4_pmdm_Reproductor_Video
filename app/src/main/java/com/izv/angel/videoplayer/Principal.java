package com.izv.angel.videoplayer;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class Principal extends ActionBarActivity {

    private Uri path;
    private final int VIDEOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        Intent intent = getIntent();
        if (intent.getAction().equals(Intent.ACTION_VIEW) && intent.getType() !=null ) {
            Intent playvideo = new Intent(this, Reproductor.class);
            path = intent.getData();
            playvideo.putExtra("path", path);
            startActivity(playvideo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
            cargarVideos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cargarVideos(){
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("video/*");
        startActivityForResult(galleryIntent, VIDEOS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case VIDEOS:
                    Intent playvideo = new Intent(this, Reproductor.class);
                    path = data.getData();
                    playvideo.putExtra("path", path);
                    startActivity(playvideo);
                    break;
            }
        }
    }


}
