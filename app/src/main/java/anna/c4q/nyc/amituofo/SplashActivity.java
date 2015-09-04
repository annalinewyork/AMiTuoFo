package anna.c4q.nyc.amituofo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class SplashActivity extends Activity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Runnable startMainMethodRunnale = new Runnable() {
            @Override
            public void run() {
                startMain();
            }
        };

        handler = new Handler();
        handler.postDelayed(startMainMethodRunnale, 3000);

    }

    protected void startMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);

    }
}
