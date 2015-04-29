package lsin1225.uclouvain.be.bartenders.trash;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "boisson"
 */
@DatabaseTable(tableName = "boisson")
public class BoissonTO {

    @DatabaseField(id = true, canBeNull = false)
    private String nom_boisson;
    @DatabaseField(canBeNull = false)
    private String nom_categorie;
    @DatabaseField(canBeNull = false)
    private String descrpition;
    @DatabaseField(canBeNull = false)
    private float prix;
    @DatabaseField(canBeNull = false)
    private int stock;
    @DatabaseField(canBeNull = false)
    private int stock_max;
    @DatabaseField(canBeNull = false)
    private int stock_seuil;

    BoissonTO() {
        // pour ORMLite
    }

    public BoissonTO(String nom_boisson, String nom_categorie, String descrpition, float prix, int stock, int stock_max, int stock_seuil) {
        this.nom_boisson = nom_boisson;
        this.nom_categorie = nom_categorie;
        this.descrpition = descrpition;
        this.prix = prix;
        this.stock = stock;
        this.stock_max = stock_max;
        this.stock_seuil = stock_seuil;
    }

    public String getNom_boisson() {
        return nom_boisson;
    }

    public void setNom_boisson(String nom_boisson) {
        this.nom_boisson = nom_boisson;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getDescrpition() {
        return descrpition;
    }

    public void setDescrpition(String descrpition) {
        this.descrpition = descrpition;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_max() {
        return stock_max;
    }

    public void setStock_max(int stock_max) {
        this.stock_max = stock_max;
    }

    public int getStock_seuil() {
        return stock_seuil;
    }

    public void setStock_seuil(int stock_seuil) {
        this.stock_seuil = stock_seuil;
    }
}