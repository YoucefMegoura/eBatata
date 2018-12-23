package dz.youcefmegoura.ebatata.ebatata.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;

import dz.youcefmegoura.ebatata.ebatata.models.Client;
import dz.youcefmegoura.ebatata.ebatata.models.Transaction;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class SqliteManager extends SQLiteOpenHelper {
    private final static String DB_NAME = "ebatata.db";
    private final static int DB_VERSION = 1;

    public SqliteManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creation de la table client
        String strSql_categorie = "CREATE TABLE client ("
                + "      id_client INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "      nom_client TEXT NOT NULL UNIQUE,"
                + "      num1_client TEXT,"
                + "      num2_client TEXT"
                + ") ;";
        db.execSQL(strSql_categorie);

        //Creation de la table transactions
        String strSql_niveau = "CREATE TABLE transactions ("
                + "      id_transactions INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "      a_donne_transactions INTEGER NOT NULL DEFAULT 0,"
                + "      a_pris_transactions INTEGER NOT NULL DEFAULT 0,"
                + "      date_transactions DATE NOT NULL,"
                + "      id_client_transactions INTEGER NOT NULL,"
                + "      FOREIGN KEY(id_client_transactions) REFERENCES client(id_client)"
                + ") ;";
        db.execSQL(strSql_niveau);

        Log.i("DATABASE", "onCreate invoked");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Rien a changer pour le moment ...
        Log.i("DATABASE", "onUpgrade invoked");
    }


    /****** Transactions ******/
    // fonctions ajout, delete, update de la table Transactions
    public void ajouter_transaction_db(Transaction transaction) {
        String strSql = "INSERT INTO transactions (a_donne_transactions, a_pris_transactions, date_transactions, id_client_transactions) " +
                "VALUES (" + transaction.getA_donne() + ", " + transaction.getA_pris() + ", '" + transaction.getDate() + "', " + transaction.getId_client_transaction() + ")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "ajouter_transaction_db invoked");
    }

    public void supprime_transaction_db(int id_transactions) {
        String strSql = "DELETE FROM transactions WHERE id_transactions = " + id_transactions;

        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "supprime_transaction_db invoked");
    }

    public void modifier_transactions_db(Transaction transaction) {
        String strSql = "UPDATE transactions SET a_donne_transactions = " + transaction.getA_donne() + ", a_pris_transactions = " + transaction.getA_pris() + ", date_transactions = '" + transaction.getDate() + "' WHERE id_transactions = " + transaction.getId();
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "modifier_transactions_db invoked");
    }

    //Récuperer la liste des transactions d'un client ordonée par date
    public ArrayList<Transaction> list_of_all_transactions(int id_client) {
        ArrayList<Transaction> arrayList_transactions = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query("transactions",
                new String[]{"id_transactions", "a_donne_transactions", "a_pris_transactions", "date_transactions", "id_client_transactions"},
                "id_client_transactions = " + id_client, null, null, null, "date_transactions", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Transaction transaction = new Transaction(cursor.getInt(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
            arrayList_transactions.add(transaction);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList_transactions;
    }

    //Récuperer la somme des transactions d'un seule client by ID
    public int show_sum_transaction(int id_client) {
        Cursor cursor = this.getReadableDatabase().query("transactions",
                new String[]{"SUM(a_donne_transactions)", "SUM(a_pris_transactions)"}, "id_client_transactions = " + id_client, null, null, null, null, null);
        cursor.moveToFirst();
        int somme_a_donne = cursor.getInt(0);
        int somme_a_pris = cursor.getInt(1);
        cursor.close();
        return somme_a_pris - somme_a_donne;
    }

    // Recuperer une seule transaction a partir du ID_Transaction
    public Transaction show_transaction(int id_transaction) {
        Cursor cursor = this.getReadableDatabase().query("transactions",
                new String[]{"id_transactions", "a_donne_transactions", "a_pris_transactions", "date_transactions", "id_client_transactions"}, "id_transactions = " + id_transaction, null, null, null, null, null);
        cursor.moveToFirst();
        Transaction transaction = new Transaction(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
        cursor.close();
        return transaction;
    }


    /****** Clients ******/
    // fonctions ajout, delete, update de la table Client
    public void ajouter_client_db(Client client) {
        String strSql = "INSERT INTO client ( nom_client, num1_client, num2_client) " +
                "VALUES ('" + client.getNom() + "', '" + client.getNum1() + "', '" + client.getNum2() + "')";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "ajouter_transaction_db invoked");
    }

    public void supprime_client_db(int id_client) {
        String strSql = "DELETE FROM client WHERE id_client = " + id_client;

        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "supprime_client_db invoked");
    }

    public void modifier_client_db(Client client) {
        String strSql = "UPDATE client SET nom_client = '" + client.getNom() + "', num1_client = '" + client.getNum1() + "', num2_client = '" + client.getNum2() + "' WHERE id_client = " + client.getId();
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "modifier_client_db invoked");
    }


    //Récuperer la liste des clients
    public ArrayList<Client> list_of_all_clients() {
        ArrayList<Client> arrayList_clients = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query("client",
                new String[]{"id_client", "nom_client", "num1_client", "num2_client"},
                null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Client client = new Client(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            arrayList_clients.add(client);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList_clients;
    }


    //Récuperer un seule client by ID
    public Client show_client(int id_client) {
        Cursor cursor = this.getReadableDatabase().query("client",
                new String[]{"id_client", "nom_client", "num1_client", "num2_client"}, "id_client = " + id_client, null, null, null, null, null);
        cursor.moveToFirst();
        Client client = new Client(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        cursor.close();
        return client;
    }

    // Verifier si le client existe dans la base de données a partir du NOM
    public boolean isClientExist(String nom) {
        ArrayList<Client> arrayList_clients = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query("client",
                new String[]{"id_client", "nom_client", "num1_client", "num2_client"},
                "nom_client = '" + nom + "'", null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Client client = new Client(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            arrayList_clients.add(client);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList_clients.isEmpty();
    }


}
