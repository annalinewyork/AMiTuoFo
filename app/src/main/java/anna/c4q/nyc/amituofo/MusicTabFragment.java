package anna.c4q.nyc.amituofo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class MusicTabFragment extends Fragment {

    private static final String TAG = MusicTabFragment.class.getSimpleName();
    private Button buttonPlayStop;
    private ImageButton buttonLoop;
    private MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound = false;
    private boolean paused = false;
    private boolean playing = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_music, container, false);

        buttonPlayStop = (Button) v.findViewById(R.id.buttonPlayStop);
        buttonLoop = (ImageButton) v.findViewById(R.id.buttonLoop);

        buttonPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playing) {
                    playing = true;
                    buttonPlayStop.setText("stop");
                    if (musicSrv != null) {
                        musicSrv.resumePlaying();
                    } else {
                        Log.w(TAG, "music service not ready yet");
                    }
                } else {
                    playing = false;
                    buttonPlayStop.setText("play");
                    if (musicSrv != null) {
                        musicSrv.pausePlayer();
                    }

                }
            }
        });

        buttonLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicSrv.isLoop())
                    buttonLoop.setImageResource(R.drawable.ic_repeat_black_18dp);
                else
                    buttonLoop.setImageResource(R.drawable.ic_repeat_white_18dp);
                musicSrv.switchLooping();
            }
        });

        return v;
    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicSrv = binder.getService();
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(getActivity(), MusicService.class);
            getActivity().bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(playIntent);
        }
    }

    public boolean canPause() {
        return true;
    }

    public int getCurrentPosition() {
        if (musicSrv != null && musicBound && musicSrv.isPng()) {
            return musicSrv.getPosn();
        } else return 0;
    }

    public boolean isPlaying() {
        if (musicSrv != null && musicBound) {
            return musicSrv.isPng();
        }
        return false;
    }

    public void seekTo(int pos) {
        musicSrv.seek(pos);
    }

    public void start() {
        musicSrv.go();
    }


    @Override
    public void onPause() {
        super.onPause();
        paused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (paused) {
            paused = false;
        }
    }

    @Override
    public void onStop() {
        //controller.hide();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        musicSrv = null;
        getActivity().stopService(playIntent);
        super.onDestroy();
    }
}