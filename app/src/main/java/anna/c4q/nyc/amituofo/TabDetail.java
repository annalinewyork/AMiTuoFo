package anna.c4q.nyc.amituofo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class TabDetail extends Fragment {

    Button button;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_detail, container, false);

        button = (Button) v.findViewById(R.id.detail_button);
        textView = (TextView) v.findViewById(R.id.detail_content);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] sentence = getResources().getStringArray(R.array.sentence);
                Random randomSentence = new Random();
                int randomNum = randomSentence.nextInt(sentence.length);
                String singleSentence = sentence[randomNum];
                textView.setText(""+""+singleSentence);
            }
        });

        return v;
    }
}
