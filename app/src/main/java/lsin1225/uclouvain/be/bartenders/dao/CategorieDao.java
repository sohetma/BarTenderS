package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;

import lsin1225.uclouvain.be.bartenders.model.Categorie;
import lsin1225.uclouvain.be.bartenders.model.Boisson;
import lsin1225.uclouvain.be.bartenders.model.Row;

import static lsin1225.uclouvain.be.bartenders.dao.SqlConstants.*;

/**
 * Created by xavier on 28/04/15.
 */
public class CategorieDao extends Dao<Categorie> {

    private static CategorieDao ourInstance = new CategorieDao();

    public static CategorieDao instance() {
        return ourInstance;
    }

    private CategorieDao() {
        this.tableName = TABLE_CATEGORIE;
        this.idColumn = COL_NOM_CATEGORIE;
    }

    @Override
    ContentValues rowToContentValues(Categorie categorie) {
        ContentValues values = new ContentValues();

        values.put(COL_ICONE, categorie.icone());
        values.put(COL_NOM_CATEGORIE, categorie.nom());

        return values;
    }

    @Override
    Categorie cursorToRow(Cursor cursor) {
        try {
            return new Categorie(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_NOM_CATEGORIE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_ICONE))
            );
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
