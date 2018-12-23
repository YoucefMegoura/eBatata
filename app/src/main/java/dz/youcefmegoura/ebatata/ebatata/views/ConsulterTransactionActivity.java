package dz.youcefmegoura.ebatata.ebatata.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.controllers.AdapterTransaction;
import dz.youcefmegoura.ebatata.ebatata.controllers.SqliteManager;
import dz.youcefmegoura.ebatata.ebatata.models.Transaction;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class ConsulterTransactionActivity extends AppCompatActivity {
    private static final String TAG = "ConsulterTransactionAct";

    private int idClientFromBundle;
    private SqliteManager sqliteManager;

    private ArrayList<Transaction> transactionsList;

    //RecyclerView Variable
    private RecyclerView recyclerView;
    private AdapterTransaction adapterTransaction;
    private RecyclerView.LayoutManager layoutManager;

    private TextView box_consulter_transaction;
    private TextView nom_consulter_transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_transaction);

        // Get le id_client du bundle
        idClientFromBundle = getIntent().getExtras().getInt("id_client");

        //Initialiser la base de données
        sqliteManager = new SqliteManager(this);

        box_consulter_transaction = (TextView) findViewById(R.id.box_consulter_transaction);
        nom_consulter_transaction = (TextView) findViewById(R.id.nom_consulter_transaction);
    }

    @Override
    protected void onResume() {
        super.onResume();
        box_consulter_transaction.setText(String.valueOf(sqliteManager.show_sum_transaction(idClientFromBundle)));
        nom_consulter_transaction.setText(String.valueOf(sqliteManager.show_client(idClientFromBundle).getNom()));

        //charger la liste de tout les transactions dans la ArrayList
        transactionsList = new ArrayList<>(sqliteManager.list_of_all_transactions(idClientFromBundle));
        Log.d(TAG, "onResume: " + transactionsList.size());
        buildRecyclerView();
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_consulter_trasaction);
        layoutManager = new LinearLayoutManager(this);
        adapterTransaction = new AdapterTransaction(transactionsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTransaction);
        adapterTransaction.setOnItemClickListener(new AdapterTransaction.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                goToModifierTransactionActivity(position);
            }
        });
    }

    //onListClick Methode sur un élément de la liste des Transactions
    public void goToModifierTransactionActivity(int position) {
        Intent intent = new Intent(ConsulterTransactionActivity.this, ModifierTransactionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id_client", idClientFromBundle);
        bundle.putInt("id_transaction", transactionsList.get(position).getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //onClick Button
    public void ajouterTransaction(View view) {

        Intent intent = new Intent(ConsulterTransactionActivity.this, AjouterTransactionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id_client", idClientFromBundle);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
