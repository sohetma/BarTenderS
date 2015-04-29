package lsin1225.uclouvain.be.bartenders.trash;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table "Table"
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "Table")
public class TableTO {

    @DatabaseField(id = true, canBeNull = false)
    private int numero_table;
    @DatabaseField(canBeNull = false)
    private float position_x;
    @DatabaseField(canBeNull = false)
    private float position_y;
    @DatabaseField
    private int etage;

    public TableTO() {
        // pour ORMLite
    }

    public TableTO(int numero_table, float position_x, float position_y, int etage) {
        this.numero_table = numero_table;
        this.position_x = position_x;
        this.position_y = position_y;
        this.etage = etage;
    }

    public int getNumero_table() {
        return numero_table;
    }

    public void setNumero_table(int numero_table) {
        this.numero_table = numero_table;
    }

    public float getPosition_x() {
        return position_x;
    }

    public void setPosition_x(float position_x) {
        this.position_x = position_x;
    }

    public float getPosition_y() {
        return position_y;
    }

    public void setPosition_y(float position_y) {
        this.position_y = position_y;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }
}
