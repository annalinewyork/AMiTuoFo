package anna.c4q.nyc.amituofo;


import android.media.MediaPlayer;
import android.net.Uri;
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

/**
 * Created on 9/3/15.
 */
public class TabMusic extends Fragment {


    private MediaPlayer mediaPlayer;
    private ImageButton btnPlay, btnPause, btnLoop;
    private ImageView imageView;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler mHandler = new Handler();
    private SeekBar seekBar;
    private TextView startText, finalText;
    public static int oneTimeOnly = 0;

    private boolean playing = false;
    private boolean looping = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_music, container, false);

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.amitabha_43mb);
        btnPlay = (ImageButton) v.findViewById(R.id.playButton);
        btnPause = (ImageButton) v.findViewById(R.id.pauseButton);
        btnLoop = (ImageButton) v.findViewById(R.id.loopButton);
        imageView = (ImageView) v.findViewById(R.id.mainImage);
        startText = (TextView) v.findViewById(R.id.startText);
        finalText = (TextView) v.findViewById(R.id.finalText);
        seekBar = (SeekBar) v.findViewById(R.id.seekbar);
        seekBar.setClickable(false);
        btnPause.setEnabled(false);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekBar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }
                startText.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
                );

                finalText.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
                );

                seekBar.setProgress((int) startTime);
                boolean b = mHandler.postDelayed(UpdateSongTime, 1000);
                btnPause.setEnabled(true);
                btnPlay.setEnabled(false);



            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                btnPause.setEnabled(false);
                btnPlay.setEnabled(true);
            }
        });

        return v;
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            startText.setText(String.format("%d min, %d sec",

                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                            toMinutes((long) startTime)))
            );
            seekBar.setProgress((int)startTime);
            mHandler.postDelayed(this, 1000);
        }
    };

}
