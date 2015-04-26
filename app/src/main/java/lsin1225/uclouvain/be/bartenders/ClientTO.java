package lsin1225.uclouvain.be.bartenders;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "Client"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "Client")
public class ClientTO {

    @DatabaseField(id = true, canBeNull = false)
    private String login;
    @DatabaseField(canBeNull = false)
    private String nom_client;
    @DatabaseField(canBeNull = false)
    private int numero_table;

    public ClientTO() {
        // pour ORMLite
    }

    public ClientTO(String login, String nom_client, int numero_table) {
        this.login = login;
        this.nom_client = nom_client;
        this.numero_table = numero_table;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getNumero_table() {
        return numero_table;
    }

    public void setNumero_table(int numero_table) {
        this.numero_table = numero_table;
    }
}
