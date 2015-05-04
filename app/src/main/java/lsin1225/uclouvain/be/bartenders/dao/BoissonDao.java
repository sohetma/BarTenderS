package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
            Categorie categorie = CategorieDao.instance()
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

        // On ajoute l'évaluation à la base de donnée, en spécifiant que, si une évaluation est déjà
        // présente pour ce couple boisson-utilisateur, l'ancienne doit être écrasée pour être
        // remplacée par la plus récente (c'est l'action de SQLiteDatabase.CONFLICT_REPLACE)
        db.insertWithOnConflict(TABLE_EVALUATION, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void removeEvaluation(String nom_boisson, String login) {
        db.delete(TABLE_EVALUATION,
                COL_NOM_BOISSON + " = ? AND " + COL_LOGIN + " = ?",
                new String[]{
                        nom_boisson,
                        login
                }
        );
    }

    public int evaluationMoyenne(String nom_boisson) {
        Cursor cursor = db.rawQuery("SELECT Sum(e." + COL_SCORE + ") / Count(e." + COL_SCORE + ")" +
                        " FROM " + TABLE_EVALUATION + " e" +
                        " WHERE e." + COL_NOM_BOISSON + " = ?" +
                        " GROUP BY e." + COL_NOM_BOISSON,
                new String[]{
                        nom_boisson
                }
        );

        int retval = 0;
        if (cursor.moveToNext()) {
            retval = cursor.getInt(0);
        }

        cursor.close();
        return retval;
    }

    public List<Boisson> listBoissonsTable(long numero) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT b.* FROM " + TABLE_BOISSON + " b" +
                        " LEFT JOIN " + TABLE_BOISSON_COMMANDE + " bc" +
                        " ON bc." + COL_NOM_BOISSON + " = b." + COL_NOM_BOISSON +
                        " LEFT JOIN " + TABLE_COMMANDE + " c" +
                        " ON c." + COL_NUMERO_COMMANDE + " = bc." + COL_NUMERO_COMMANDE +
                        " WHERE c." + COL_NUMERO_TABLE + " = ?",
                new String[]{
                        Long.toString(numero)
                }
        );

        List<Boisson> retval = cursorToRows(cursor);

        cursor.close();
        return retval;
    }
}
