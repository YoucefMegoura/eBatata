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

public class ModifierClientActivity extends AppCompatActivity {

    // Variables du Layout
    private EditText nom_modifier_client;
    private EditText num1_modifier_client;
    private EditText num2_modifier_client;

    //Variables membre
    private SqliteManager sqliteManager;
    private int idFromBundle;
    private String ancienNom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_client);
        idFromBundle = getIntent().getExtras().getInt("id_client");

        nom_modifier_client = (EditText) findViewById(R.id.nom_modifier_client);
        num1_modifier_client = (EditText) findViewById(R.id.num1_modifier_client);
        num2_modifier_client = (EditText) findViewById(R.id.num2_modifier_client);

        sqliteManager = new SqliteManager(this);

        Client client = sqliteManager.show_client(idFromBundle);
        ancienNom = client.getNom();
        nom_modifier_client.setText(client.getNom());
        num1_modifier_client.setText(client.getNum1());
        num2_modifier_client.setText(client.getNum2());

   }

    // onClick Button
    public void modifierClient(View view) {
        String nom = nom_modifier_client.getText().toString();
        String numero1 = num1_modifier_client.getText().toString();
        String numero2 = num2_modifier_client.getText().toString();

        if (nom.isEmpty()) {
            nom_modifier_client.setError("Veillez entrer un nom");
            nom_modifier_client.requestFocus();
            return;
        }
        if (nom.length() <= 3) {
            nom_modifier_client.setError("Veillez entrer un nom valide");
            nom_modifier_client.requestFocus();
            return;
        }
        if (!nom.equals(ancienNom)){
            if (!sqliteManager.isClientExist(nom)) {
                nom_modifier_client.setError("Le nom existe déja");
                nom_modifier_client.requestFocus();
                return;
            }
        }


        Client client = new Client(idFromBundle, nom, numero1, numero2);
        sqliteManager.modifier_client_db(client);

        Toast.makeText(this, "Client Modifié", Toast.LENGTH_SHORT).show();
        finish();
    }


}
