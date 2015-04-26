package lsin1225.uclouvain.be.bartenders;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "Categorie"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "categorie")
public class CategorieTO {

    @DatabaseField(id = true, canBeNull = false)
    private String nom_categorie;
    @DatabaseField(canBeNull = false)
    private String icone;

    public CategorieTO() {
        // pour ORMLite
    }

    public CategorieTO(String nom_categorie, String icone) {
        this.nom_categorie = nom_categorie;
        this.icone = icone;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
