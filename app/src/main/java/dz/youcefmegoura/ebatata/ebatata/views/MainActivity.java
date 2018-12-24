package dz.youcefmegoura.ebatata.ebatata.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.controllers.DialogActivation;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SharedPreferences sharedPreferences;
    private long firstCurrentTimeMillis;
    private static long AVAILABLE_DAYS_IN_MILLIS = 345600000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        checkAvailableDayToRun();
    }

    //OnClick Button
    public void ajouterClient(View view) {
        startActivity(new Intent(MainActivity.this, AjouterClientActivity.class));

    }

    //OnClick Button
    public void consulterClient(View view) {
        startActivity(new Intent(MainActivity.this, RechercherClientActivity.class));
    }

    public void checkAvailableDayToRun() {
        firstCurrentTimeMillis = sharedPreferences.getLong("firstCurrentTimeMillis", 0);
        Log.d(TAG, "checkAvailableDayToRun: firstCurrentTimeMillis " + firstCurrentTimeMillis);
        long currentTimeMillis = System.currentTimeMillis();
        long restOfAvailableDay = currentTimeMillis - firstCurrentTimeMillis;

        if (restOfAvailableDay >= AVAILABLE_DAYS_IN_MILLIS) {
            openActivationDialog();
        }

        Log.d(TAG, "checkAvailableDayToRun: Invoked");

    }

    public void openActivationDialog() {
        DialogActivation dialogActivation = new DialogActivation();
        dialogActivation.show(getSupportFragmentManager(), "Activation Dialog");
        dialogActivation.setCancelable(false);
    }

}
