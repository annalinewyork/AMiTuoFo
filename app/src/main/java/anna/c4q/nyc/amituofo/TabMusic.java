package anna.c4q.nyc.amituofo;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TabMusic extends Fragment {


    private MediaPlayer mediaPlayer;
    private ImageButton btnPlay, btnPause, btnStop;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler mHandler = new Handler();
  //  private SeekBar seekBar;
    private TextView startText, finalText;
    public static int oneTimeOnly = 0;

    private boolean playing = false;
    private boolean pause = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_music, container, false);

        btnPlay = (ImageButton) v.findViewById(R.id.playButton);
        btnPause = (ImageButton) v.findViewById(R.id.pauseButton);
        btnStop = (ImageButton) v.findViewById(R.id.stopButton);
        startText = (TextView) v.findViewById(R.id.startText);
        finalText = (TextView) v.findViewById(R.id.finalText);
      //  seekBar = (SeekBar) v.findViewById(R.id.seekbar);

        btnPlay = (ImageButton) v.findViewById(R.id.playButton);

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.amitabha_43mb);
        }

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playing){
                    playing = true;
                    btnPlay.setImageResource(R.drawable.btn_play_pressed);

                    if (mediaPlayer != null){
                        mediaPlayer.start();
                    }
                }else{
                    playing = false;
                    btnPlay.setImageResource(R.drawable.btn_play);
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pause){
                    pause = true;
                    btnPause.setImageResource(R.drawable.btn_pause);
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.btn_play);
                }
                else{
                    pause = false;
                    btnPause.setImageResource(R.drawable.btn_pause);
                }
            }
        });

        btnStop = (ImageButton)v.findViewById(R.id.stopButton);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                else {
                mediaPlayer.start();
                }
            }
        });

        //set btn to "start play" again after music finished.
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnPlay.setImageResource(R.drawable.btn_play);
                mediaPlayer.release();
            }
        });


        return v;
    }
}
