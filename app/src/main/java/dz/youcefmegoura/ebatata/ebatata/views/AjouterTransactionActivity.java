package dz.youcefmegoura.ebatata.ebatata.views;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.controllers.SqliteManager;
import dz.youcefmegoura.ebatata.ebatata.models.Transaction;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class AjouterTransactionActivity extends AppCompatActivity {

    private static final String TAG = "AjouterTransactionActiv";

    private int idClientFromBundle;
    private SqliteManager sqliteManager;
    private String strDate;

    private CalendarView calendarView;
    private EditText a_donne_ajouter_transaction, a_pris_ajouter_transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_transaction);

        idClientFromBundle = getIntent().getExtras().getInt("id_client");

        sqliteManager = new SqliteManager(this);

        a_donne_ajouter_transaction = (EditText) findViewById(R.id.a_donne_ajouter_transaction);
        a_pris_ajouter_transaction = (EditText) findViewById(R.id.a_pris_ajouter_transaction);
        calendarView = (CalendarView) findViewById(R.id.calendar_ajouter_transaction);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        strDate = mdformat.format(calendar.getTime());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                formatageDate(dayOfMonth, month, year);

            }
        });

    }

    //onClick Button
    public void effectuerButton(View view) {
        String combienIlAdonne = a_donne_ajouter_transaction.getText().toString();
        String combienIlApris = a_pris_ajouter_transaction.getText().toString();

        if (a_donne_ajouter_transaction.getText().toString().isEmpty()) {
            a_donne_ajouter_transaction.setError("Veillez entrer un nombre");
            a_donne_ajouter_transaction.requestFocus();
            return;
        }
        if (Integer.valueOf(combienIlAdonne) > 1000) {
            a_donne_ajouter_transaction.setError("Nombre trop elevé !");
            a_donne_ajouter_transaction.requestFocus();
            return;
        }
        if (a_pris_ajouter_transaction.getText().toString().isEmpty()) {
            a_pris_ajouter_transaction.setError("Veillez entrer un nombre");
            a_pris_ajouter_transaction.requestFocus();
            return;
        }
        if (Integer.valueOf(combienIlApris) > 1000) {
            a_donne_ajouter_transaction.setError("Nombre trop elevé !");
            a_donne_ajouter_transaction.requestFocus();
            return;
        }


        Transaction transaction = new Transaction(Integer.valueOf(combienIlApris), Integer.valueOf(combienIlAdonne), strDate, idClientFromBundle);
        sqliteManager.ajouter_transaction_db(transaction);

        Toast.makeText(this, "Nouvelle transaction ajoutée", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void formatageDate(int dayOfMonth, int month, int year){
        //Juste une question de formatage dd/MM/yyyy
        if (month >= 10) {
            if (dayOfMonth >= 10)
                strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
            else
                strDate = year + "-" + (month + 1) + "-" + "0" + dayOfMonth;
        } else {
            if (dayOfMonth >= 10)
                strDate = year + "-" + "0" +(month + 1) + "-" + dayOfMonth;
            else
                strDate = year + "-" + "0" +(month + 1) + "-" + "0" + dayOfMonth;
        }
    }

}
