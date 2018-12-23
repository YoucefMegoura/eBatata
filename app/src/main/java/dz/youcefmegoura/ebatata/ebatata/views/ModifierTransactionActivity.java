package dz.youcefmegoura.ebatata.ebatata.views;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.controllers.SqliteManager;
import dz.youcefmegoura.ebatata.ebatata.models.Transaction;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class ModifierTransactionActivity extends AppCompatActivity {

    private int idTransactionFromBundle;
    private int idClientFromBundle;

    private SqliteManager sqliteManager;

    private String strDate;

    private CalendarView calendar_modifier_transaction;
    private EditText a_donne_modifier_transaction, a_pris_modifier_transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_transaction);

        idTransactionFromBundle = getIntent().getExtras().getInt("id_transaction");
        idClientFromBundle = getIntent().getExtras().getInt("id_client");

        sqliteManager = new SqliteManager(this);

        a_donne_modifier_transaction = (EditText) findViewById(R.id.a_donne_modifier_transaction);
        a_pris_modifier_transaction = (EditText) findViewById(R.id.a_pris_modifier_transaction);
        calendar_modifier_transaction = (CalendarView) findViewById(R.id.calendar_modifier_transaction);


        //Charger les ancienne informations
        Transaction transaction = sqliteManager.show_transaction(idTransactionFromBundle);
        a_donne_modifier_transaction.setText(String.valueOf(transaction.getA_donne()));
        a_pris_modifier_transaction.setText(String.valueOf(transaction.getA_pris()));
        calendar_modifier_transaction.setDate(stringDateToLong(transaction.getDate()));


        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        strDate = mdformat.format(stringDateToLong(transaction.getDate()));


    }

    public void effectuerButton(View view) {
        int combienIlAdonne = Integer.parseInt(a_donne_modifier_transaction.getText().toString());
        int combienIlApris = Integer.parseInt(a_pris_modifier_transaction.getText().toString());


        calendar_modifier_transaction.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                formatageDate(dayOfMonth, month, year);

            }
        });

        if (a_donne_modifier_transaction.getText().toString().isEmpty()) {
            a_donne_modifier_transaction.setError("Veillez entrer un nombre");
            a_donne_modifier_transaction.requestFocus();
            return;
        }
        if (Integer.valueOf(combienIlAdonne) > 1000) {
            a_donne_modifier_transaction.setError("Nombre trop elevé !");
            a_donne_modifier_transaction.requestFocus();
            return;
        }
        if (a_pris_modifier_transaction.getText().toString().isEmpty()) {
            a_pris_modifier_transaction.setError("Veillez entrer un nombre");
            a_pris_modifier_transaction.requestFocus();
            return;
        }
        if (Integer.valueOf(combienIlApris) > 1000) {
            a_pris_modifier_transaction.setError("Nombre trop elevé !");
            a_pris_modifier_transaction.requestFocus();
            return;
        }

        Transaction transaction = new Transaction(idClientFromBundle, combienIlAdonne, combienIlApris, strDate, idClientFromBundle);
        sqliteManager.modifier_transactions_db(transaction);

        Toast.makeText(this, "Transaction modifiée", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void formatageDate(int dayOfMonth, int month, int year) {
        //Juste une question de formatage dd/MM/yyyy
        if (month >= 10) {
            if (dayOfMonth >= 10)
                strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
            else
                strDate = year + "-" + (month + 1) + "-" + "0" + dayOfMonth;
        } else {
            if (dayOfMonth >= 10)
                strDate = year + "-" + "0" + (month + 1) + "-" + dayOfMonth;
            else
                strDate = year + "-" + "0" + (month + 1) + "-" + "0" + dayOfMonth;
        }
    }

    public Long stringDateToLong(String strDate) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = f.parse(strDate);
            long milliseconds = d.getTime();
            return milliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
