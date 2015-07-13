package anna.c4q.nyc.amituofo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;


public class MainActivity extends Activity {

    MediaPlayer mediaPlayer;
    private boolean playing = false;
    private boolean looping = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton buttonStart = (ImageButton) findViewById(R.id.playButton);

        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.amitabha);

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, path);
        }

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!playing) {
                    playing = true;
                    buttonStart.setImageResource(R.drawable.button_stop);

                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                } else {
                    playing = false;
                    buttonStart.setImageResource(R.drawable.button_play);

                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();

                    }
                }
            }
        });

        final ImageButton buttonLoop = (ImageButton) findViewById(R.id.loopButton);
        buttonLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!looping) {
                    looping = true;
                    buttonLoop.setImageResource(R.drawable.button_loop_pressed);
                    mediaPlayer.setLooping(true);
                } else {
                    looping = false;
                    buttonLoop.setImageResource(R.drawable.button_loop_default);
                    mediaPlayer.setLooping(false);
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }


}
