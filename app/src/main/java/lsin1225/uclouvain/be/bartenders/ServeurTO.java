package lsin1225.uclouvain.be.bartenders;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "Serveur"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "Serveur")
public class ServeurTO {

    @DatabaseField(id = true, canBeNull = false)
    private String login;
    @DatabaseField(canBeNull = false)
    private String nom_serveur;

    public ServeurTO() {
        // pour ORMLite
    }

    public ServeurTO(String login, String nom_serveur) {
        this.login = login;
        this.nom_serveur = nom_serveur;
    }

    public String getNom_serveur() {
        return nom_serveur;
    }

    public void setNom_serveur(String nom_serveur) {
        this.nom_serveur = nom_serveur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
