package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import lsin1225.uclouvain.be.bartenders.model.Boisson;
import lsin1225.uclouvain.be.bartenders.model.Categorie;
import lsin1225.uclouvain.be.bartenders.model.Row;
import lsin1225.uclouvain.be.bartenders.model.Table;

import static lsin1225.uclouvain.be.bartenders.dao.SqlConstants.*;

/**
 * Created by xavier on 28/04/15.
 */
public class TableDao extends Dao<Table> {

    private static TableDao ourInstance = new TableDao();

    public static TableDao instance() {
        return ourInstance;
    }

    private TableDao() {
        this.tableName = TABLE_TABLE;
        this.idColumn = COL_NUMERO_TABLE;
    }

    @Override
    ContentValues rowToContentValues(Table table) {
        ContentValues values = new ContentValues();

        values.put(COL_NUMERO_TABLE, table.numero());
        values.put(COL_POSITION_X, table.x());
        values.put(COL_POSITION_Y, table.y());
        values.put(COL_ETAGE, table.etage());

        return values;
    }

    @Override
    Table cursorToRow(Cursor cursor) {
        try {
            return new Table(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_NUMERO_TABLE)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(COL_POSITION_X)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(COL_POSITION_Y)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_ETAGE))
            );
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Table find(int numero) {
        return this.find(Integer.toString(numero));
    }

    public float addition(int numero) {
        Cursor cursor = db.rawQuery("SELECT Sum(b.?)" +
                        "FROM ? b" +
                        "LEFT JOIN ? bc ON bc.? = c.?" +
                        "LEFT JOIN ? c ON c.? = ?",
                new String[]{
                        COL_PRIX,
                        TABLE_BOISSON,
                        TABLE_BOISSON_COMMANDE,
                        COL_NUMERO_COMMANDE,
                        COL_NUMERO_COMMANDE,
                        TABLE_COMMANDE,
                        COL_NUMERO_TABLE,
                        Integer.toString(numero)
                }
        );

        cursor.moveToNext();
        return cursor.getInt(0);
    }
}
