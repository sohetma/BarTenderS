package lsin1225.uclouvain.be.bartenders.trash;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "Utilisateur"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "Utilisateur")
public class UtilisateurTO {

    @DatabaseField(id = true, canBeNull = false)
    private String login;
    @DatabaseField(canBeNull = false)
    private String mot_de_passe;

    public UtilisateurTO() {
        // pour ORMLite
    }

    public UtilisateurTO(String login, String mot_de_passe) {
        this.login = login;
        this.mot_de_passe = mot_de_passe;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
