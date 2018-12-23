package dz.youcefmegoura.ebatata.ebatata.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.controllers.SqliteManager;
import dz.youcefmegoura.ebatata.ebatata.models.Client;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class ConsulterClientActivity extends AppCompatActivity {

    private TextView nom_consulter_client, num1_consulter_client, num2_consulter_client;
    private Button button_consulter_client;

    private int idFromBundle;

    private SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_client);

        idFromBundle = getIntent().getExtras().getInt("id_client");

        sqliteManager = new SqliteManager(this);

        nom_consulter_client = (TextView) findViewById(R.id.nom_consulter_client);
        num1_consulter_client = (TextView) findViewById(R.id.num1_consulter_client);
        num2_consulter_client = (TextView) findViewById(R.id.num2_consulter_client);
        button_consulter_client = (Button) findViewById(R.id.button_consulter_client);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Client client = sqliteManager.show_client(idFromBundle);

        nom_consulter_client.setText(client.getNom());
        num1_consulter_client.setText(client.getNum1());
        num2_consulter_client.setText(client.getNum2());

        button_consulter_client.setText("  " + String.valueOf(sqliteManager.show_sum_transaction(idFromBundle)));


    }

    //onClick Button
    public void showTransactions(View view) {
        Intent intent = new Intent(ConsulterClientActivity.this, ConsulterTransactionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id_client", idFromBundle);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    //onClick ImageView
    public void modifier_client_consulter_client(View view) {
         Intent intent = new Intent(ConsulterClientActivity.this, ModifierClientActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id_client", idFromBundle);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
