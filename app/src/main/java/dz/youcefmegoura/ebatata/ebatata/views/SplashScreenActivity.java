package dz.youcefmegoura.ebatata.ebatata.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dz.youcefmegoura.ebatata.ebatata.R;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class SplashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 3000;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        /*************** Splash Screen **************/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);

        if (!sharedPreferences.getBoolean("firstTime", false)) {
            long time = System.currentTimeMillis();
            editor = sharedPreferences.edit();
            editor.putLong("firstCurrentTimeMillis", time);


            // mark first time has ran.
            editor.putBoolean("firstTime", true);
            editor.commit();
            editor.apply();
        }
    }
}
