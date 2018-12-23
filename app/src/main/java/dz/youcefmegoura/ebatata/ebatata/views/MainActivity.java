package dz.youcefmegoura.ebatata.ebatata.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dz.youcefmegoura.ebatata.ebatata.R;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //OnClick Button
    public void ajouterClient(View view) {
        startActivity(new Intent(MainActivity.this, AjouterClientActivity.class));

    }

    //OnClick Button
    public void consulterClient(View view) {
        startActivity(new Intent(MainActivity.this, RechercherClientActivity.class));
    }
}
