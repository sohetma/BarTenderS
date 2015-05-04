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

        if (table.numero() != 0) {
            values.put(COL_NUMERO_TABLE, table.numero());
        }
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

    public Table find(long numero) {
        return this.find(Long.toString(numero));
    }

    public float addition(long numero) {
        Cursor cursor = db.rawQuery("SELECT Sum(b." + COL_PRIX + " * bc." + COL_QUANTITE + ")" +
                        " FROM " + TABLE_BOISSON + " b" +
                        " LEFT JOIN " + TABLE_BOISSON_COMMANDE + " bc" +
                        " ON bc." + COL_NUMERO_COMMANDE + " = c." + COL_NUMERO_COMMANDE +
                        " LEFT JOIN " + TABLE_COMMANDE + " c" +
                        " ON c." + COL_NUMERO_TABLE + " = ?",
                new String[]{
                        Long.toString(numero)
                }
        );

        int retval = 0;
        if (cursor.moveToNext()) {
            retval = cursor.getInt(0);
        }

        cursor.close();
        return retval;
    }

    public Table tableCommande(long numero_commande) {
        Cursor cursor = db.rawQuery("SELECT t.*" +
                        " FROM \"" + TABLE_TABLE + "\" t" +
                        " LEFT JOIN " + TABLE_COMMANDE + " c" +
                        " ON c." + COL_NUMERO_TABLE + " = t." + COL_NUMERO_TABLE +
                        " WHERE c." + COL_NUMERO_COMMANDE + " = ?",
                new String[]{
                        Long.toString(numero_commande)
                }
        );

        Table retval = null;
        if (cursor.moveToNext()) {
            retval = cursorToRow(cursor);
        }

        cursor.close();
        return retval;
    }
}
