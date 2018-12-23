package dz.youcefmegoura.ebatata.ebatata.models;

/**
 * Created by Youcef Mégoura on 20/12/2018.
 */

public class Client {
    private int id;
    private String nom;
    private String num1;
    private String num2;

    public Client(int id, String nom, String num1, String num2) {
        this.id = id;
        this.nom = nom;
        this.num1 = num1;
        this.num2 = num2;
    }
    public Client(String nom, String num1, String num2) {
        this.nom = nom;
        this.num1 = num1;
        this.num2 = num2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }
}
