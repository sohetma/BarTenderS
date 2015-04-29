package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import lsin1225.uclouvain.be.bartenders.model.Categorie;
import lsin1225.uclouvain.be.bartenders.model.Boisson;
import lsin1225.uclouvain.be.bartenders.model.Commande;
import lsin1225.uclouvain.be.bartenders.model.Row;

import static lsin1225.uclouvain.be.bartenders.dao.SqlConstants.*;

/**
 * Created by xavier on 28/04/15.
 */
public class BoissonDao extends Dao<Boisson> {

    private static BoissonDao ourInstance = new BoissonDao();

    public static BoissonDao instance() {
        return ourInstance;
    }

    private BoissonDao() {
        this.tableName = TABLE_BOISSON;
        this.idColumn = COL_NOM_BOISSON;
    }

    @Override
    ContentValues rowToContentValues(Boisson boisson) {
        ContentValues values = new ContentValues();

        values.put(COL_NOM_BOISSON, boisson.nom());
        values.put(COL_NOM_CATEGORIE, boisson.categorie().nom());
        values.put(COL_DESCRIPTION, boisson.description());
        values.put(COL_PRIX, boisson.prix());
        values.put(COL_STOCK, boisson.stock());
        values.put(COL_STOCK_MAX, boisson.stockMax());
        values.put(COL_STOCK_SEUIL, boisson.stockSeuil());

        return values;
    }

    @Override
    Boisson cursorToRow(Cursor cursor) {
        try {
            Categorie categorie = (Categorie) CategorieDao.instance()
                    .find(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOM_CATEGORIE)));

            return new Boisson(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_NOM_BOISSON)),
                    categorie,
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPTION)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(COL_PRIX)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_STOCK)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_STOCK_MAX)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_STOCK_SEUIL))
            );
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void addEvaluation(String nom_boisson, String login, int score) {
        ContentValues values = new ContentValues();
        values.put(COL_NOM_BOISSON, nom_boisson);
        values.put(COL_LOGIN, login);
        values.put(COL_SCORE, score);
        db.insert(TABLE_EVALUATION, null, values);
    }

    public void removeEvaluation(String nom_boisson, String login) {
        db.delete(TABLE_EVALUATION,
                "? = ? AND ? = ?", new String[]{
                        COL_NOM_BOISSON,
                        nom_boisson,
                        COL_LOGIN,
                        login
                }
        );
    }

    public int evaluationMoyenne(String nom_boisson) {
        Cursor cursor = db.rawQuery("SELECT Sum(e.?) / Count(e.*) FROM ? e WHERE e.? = ?",
                new String[]{
                        COL_SCORE,
                        TABLE_EVALUATION,
                        COL_NOM_BOISSON,
                        nom_boisson
                }
        );
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public List<Boisson> listBoissonsTable(int numero) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT b.* FROM ? b" +
                        "LEFT JOIN ? bc ON bc.? = b.?" +
                        "LEFT JOIN ? c ON c.? = bc.?" +
                        "WHERE" +
                        "c.? = ?;",
                new String[]{
                        TABLE_BOISSON,
                        TABLE_BOISSON_COMMANDE,
                        COL_NOM_BOISSON,
                        COL_NOM_BOISSON,
                        TABLE_COMMANDE,
                        COL_NUMERO_COMMANDE,
                        COL_NUMERO_COMMANDE,
                        COL_NUMERO_TABLE,
                        Integer.toString(numero)
                }
        );

        return cursorToRows(cursor);
    }
}
