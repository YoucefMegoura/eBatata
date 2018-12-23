package dz.youcefmegoura.ebatata.ebatata.models;


/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class Transaction {
    private int id;
    private int a_donne;
    private int a_pris;
    private String date;
    private int id_client_transaction;

    public Transaction(int id, int a_donne, int a_pris, String date, int id_client_transaction) {
        this.id = id;
        this.a_donne = a_donne;
        this.a_pris = a_pris;
        this.date = date;
        this.id_client_transaction = id_client_transaction;
    }

    public Transaction(int a_donne, int a_pris, String date, int id_client_transaction) {
        this.a_donne = a_donne;
        this.a_pris = a_pris;
        this.date = date;
        this.id_client_transaction = id_client_transaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getA_donne() {
        return a_donne;
    }

    public void setA_donne(int a_donne) {
        this.a_donne = a_donne;
    }

    public int getA_pris() {
        return a_pris;
    }

    public void setA_pris(int a_pris) {
        this.a_pris = a_pris;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_client_transaction() {
        return id_client_transaction;
    }

    public void setId_client_transaction(int id_client_transaction) {
        this.id_client_transaction = id_client_transaction;
    }
}
