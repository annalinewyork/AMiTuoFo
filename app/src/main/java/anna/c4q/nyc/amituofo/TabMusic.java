package anna.c4q.nyc.amituofo;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created on 9/3/15.
 */
public class TabMusic extends Fragment {

    MediaPlayer mediaPlayer;
    ImageButton imageButton;
    private boolean playing = false;
    private boolean looping = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_music, container, false);

        imageButton = (ImageButton) v.findViewById(R.id.playButton);
        mediaPlayer = MediaPlayer.create(getActivity(),R.raw.amitabha_43mb);

        

        return v;
    }
}
