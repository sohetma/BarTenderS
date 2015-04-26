package lsin1225.uclouvain.be.bartenders;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "Gestionnaire"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "Gestionnaire")
public class GestionnaireTO {

    @DatabaseField(id = true, canBeNull = false)
    private String login;
    @DatabaseField(canBeNull = false)
    private String nom_gestionnaire;

    public GestionnaireTO() {
        // pour ORMLite
    }

    public GestionnaireTO(String login, String nom_gestionnaire) {
        this.login = login;
        this.nom_gestionnaire = nom_gestionnaire;
    }

    public String getNom_gestionnaire() {
        return nom_gestionnaire;
    }

    public void setNom_gestionnaire(String nom_gestionnaire) {
        this.nom_gestionnaire = nom_gestionnaire;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
