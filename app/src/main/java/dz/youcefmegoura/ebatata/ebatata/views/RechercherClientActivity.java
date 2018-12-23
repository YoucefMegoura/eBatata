package dz.youcefmegoura.ebatata.ebatata.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.controllers.AdapterClient;
import dz.youcefmegoura.ebatata.ebatata.controllers.SqliteManager;
import dz.youcefmegoura.ebatata.ebatata.models.Client;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class RechercherClientActivity extends AppCompatActivity {

    private static final String TAG = "RechercherClientActivity";

    private EditText search_rechercher_client;

    private SqliteManager sqliteManager;

    private ArrayList<Client> clientsList;
    private ArrayList<Client> filtredList;

    private RecyclerView recyclerView;
    private AdapterClient adapterClient;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher_client);


        search_rechercher_client = (EditText) findViewById(R.id.search_rechercher_client);
        search_rechercher_client.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    filtredList.clear();
                }
                filter(s.toString());
            }
        });

        //Initialiser la base de données
        sqliteManager = new SqliteManager(this);




    }

    @Override
    protected void onResume() {
        super.onResume();



        //charger la liste de tout les clients dans la ArrayList
        clientsList = new ArrayList<>(sqliteManager.list_of_all_clients());

        filtredList = new ArrayList<>(clientsList);

        buildRecyclerView();

    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.list_rechercher_client_activity);
        layoutManager = new LinearLayoutManager(this);
        adapterClient = new AdapterClient(clientsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterClient);

        adapterClient.setOnItemClickListener(new AdapterClient.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                goToConsulterClientActivity(position);
            }
        });
    }

    public void goToConsulterClientActivity(int position) {
        Intent intent = new Intent(RechercherClientActivity.this, ConsulterClientActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id_client", filtredList.get(position).getId());
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void filter(String text) {
        for (Client item : clientsList) {
            if (item.getNom().toLowerCase().contains(text.toLowerCase())) {
                filtredList.add(item);
            }
        }
        adapterClient.filtredList(filtredList);
    }

    // onClick ImageView
    public void backButton(View view) {
        finish();
    }

}