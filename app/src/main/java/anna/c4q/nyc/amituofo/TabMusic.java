package anna.c4q.nyc.amituofo;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class TabMusic extends Fragment {

    private MediaPlayer mediaPlayer;
    private Button buttonPlayStop;
    private SeekBar seekbar;
    private boolean playing = false;
    private boolean looping = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_music, container, false);

        buttonPlayStop = (Button) v.findViewById(R.id.buttonPlayStop);
        seekbar = (SeekBar) v.findViewById(R.id.seekbar);

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.amitabha_43mb);
        }

        buttonPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!playing) {
                    playing = true;
                    buttonPlayStop.setText("stop");

                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                } else {
                    playing = false;
                    buttonPlayStop.setText("play");

                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                buttonPlayStop.setText("play");
            }
        });

        final ImageButton buttonLoop = (ImageButton)v.findViewById(R.id.buttonLoop);
        buttonLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!looping) {
                    looping = true;
                    buttonLoop.setImageResource(R.drawable.ic_repeat_black_18dp);
                    mediaPlayer.setLooping(true);
                } else {
                    looping = false;
                    buttonLoop.setImageResource(R.drawable.ic_repeat_white_18dp);
                    mediaPlayer.setLooping(false);
                }
            }

        });

        return v;
    }


}
