package dz.youcefmegoura.ebatata.ebatata.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.controllers.SqliteManager;
import dz.youcefmegoura.ebatata.ebatata.models.Client;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class AjouterClientActivity extends AppCompatActivity {

    // Variables du Layout
    EditText nom_ajouter_client;
    EditText num1_ajouter_client;
    EditText num2_ajouter_client;

    //Variables membre
    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_client);

        nom_ajouter_client = (EditText) findViewById(R.id.nom_ajouter_client);
        num1_ajouter_client = (EditText) findViewById(R.id.num1_ajouter_client);
        num2_ajouter_client = (EditText) findViewById(R.id.num2_ajouter_client);

        sqliteManager = new SqliteManager(this);

    }

    //onClick Button
    public void ajouterClient(View view) {
        String nom = nom_ajouter_client.getText().toString();
        String numero1 = num1_ajouter_client.getText().toString();
        String numero2 = num2_ajouter_client.getText().toString();

        if (nom.isEmpty()) {
            nom_ajouter_client.setError("Veillez entrer un nom");
            nom_ajouter_client.requestFocus();
            return;
        }
        if (nom.length() <= 3) {
            nom_ajouter_client.setError("Veillez entrer un nom valide");
            nom_ajouter_client.requestFocus();
            return;
        }
        if (!sqliteManager.isClientExist(nom)) {
            nom_ajouter_client.setError("Le nom existe déja");
            nom_ajouter_client.requestFocus();
            return;
        }




        Client client = new Client(nom, numero1, numero2);
        sqliteManager.ajouter_client_db(client);

        Toast.makeText(this, "Nouveau client ajouté", Toast.LENGTH_SHORT).show();
        finish();
    }


}
