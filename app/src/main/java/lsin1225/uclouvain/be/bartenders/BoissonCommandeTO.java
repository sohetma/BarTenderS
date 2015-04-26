package lsin1225.uclouvain.be.bartenders;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "BoissonCommande"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "BoissonCommande")
public class BoissonCommandeTO {

    @DatabaseField(canBeNull = false, uniqueCombo = true)
    private String nom_boisson;
    @DatabaseField(canBeNull = false, uniqueCombo = true)
    private int numero_commande;
    @DatabaseField(canBeNull = false)
    private int quantite;

    public BoissonCommandeTO() {
        // pour ORMLite
    }

    public BoissonCommandeTO(String nom_boisson, int quantite, int numero_commande) {
        this.nom_boisson = nom_boisson;
        this.quantite = quantite;
        this.numero_commande = numero_commande;
    }

    public String getNom_boisson() {
        return nom_boisson;
    }

    public void setNom_boisson(String nom_boisson) {
        this.nom_boisson = nom_boisson;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getNumero_commande() {
        return numero_commande;
    }

    public void setNumero_commande(int numero_commande) {
        this.numero_commande = numero_commande;
    }
}
