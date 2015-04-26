package lsin1225.uclouvain.be.bartenders;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "commande"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "commande")
public class CommandeTO {

    @DatabaseField(id = true, canBeNull = false)
    private int numero_commande;
    @DatabaseField(canBeNull = false)
    private int numero_table;
    @DatabaseField(canBeNull = false)
    private boolean payee;

    public CommandeTO() {
        // pour ORMLite
    }

    public CommandeTO(int numero_commande, int numero_table, boolean payee) {
        this.numero_commande = numero_commande;
        this.numero_table = numero_table;
        this.payee = payee;
    }

    public int getNumero_commande() {
        return numero_commande;
    }

    public void setNumero_commande(int numero_commande) {
        this.numero_commande = numero_commande;
    }

    public int getNumero_table() {
        return numero_table;
    }

    public void setNumero_table(int numero_table) {
        this.numero_table = numero_table;
    }

    public boolean isPayee() {
        return payee;
    }

    public void setPayee(boolean payee) {
        this.payee = payee;
    }
}
