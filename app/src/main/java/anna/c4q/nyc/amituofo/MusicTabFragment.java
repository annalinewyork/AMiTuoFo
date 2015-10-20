package anna.c4q.nyc.amituofo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;

public class MusicTabFragment extends Fragment {

    private Button buttonPlayStop;
    private MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound = false;
    private boolean paused = false;
    private boolean playing = false;
    private MusicController controller;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_music, container, false);

        buttonPlayStop = (Button) v.findViewById(R.id.buttonPlayStop);
        buttonPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!playing) {
                    playing = true;
                    buttonPlayStop.setText("stop");
                    musicSrv.playSong();

                } else {
                    playing = false;

                    buttonPlayStop.setText("play");
                    musicSrv.pausePlayer();


                }
            }
        });

        return v;
    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicSrv = binder.getService();
            //bind to play the song ????
            musicSrv.playSong();
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
            //not sure if getActivity is correct? instead of "this"
            playIntent = new Intent(getActivity(), MusicService.class);
            getActivity().bindService(playIntent,musicConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(playIntent);
        }
    }

    public boolean canPause(){
        return true;
    }

    public int getCurrentPosition(){
        if(musicSrv!=null && musicBound && musicSrv.isPng()){
            return  musicSrv.getPosn();
        }
        else return 0;
    }

    public boolean isPlaying(){
        if (musicSrv!=null && musicBound){
            return musicSrv.isPng();
        }
        return false;
    }

    public void seekTo (int pos){
        musicSrv.seek(pos);
    }

    public void start(){
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
        if (paused){
            paused=false;
        }
    }

    @Override
    public void onStop() {
        //controller.hide();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        //stopService(playIntent);
        musicSrv=null;
        super.onDestroy();
    }
}