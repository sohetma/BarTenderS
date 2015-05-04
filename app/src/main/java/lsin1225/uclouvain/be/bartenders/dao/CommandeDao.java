package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import lsin1225.uclouvain.be.bartenders.model.Commande;

import static lsin1225.uclouvain.be.bartenders.dao.SqlConstants.*;

/**
 * Created by xavier on 28/04/15.
 */
public class CommandeDao extends Dao<Commande> {

    private static CommandeDao ourInstance = new CommandeDao();

    public static CommandeDao instance() {
        return ourInstance;
    }

    private CommandeDao() {
        this.tableName = TABLE_COMMANDE;
        this.idColumn = COL_NUMERO_COMMANDE;
    }

    @Override
    protected ContentValues rowToContentValues(Commande commande) {
        ContentValues values = new ContentValues();

        values.put(COL_NUMERO_COMMANDE, commande.numero());
        values.put(COL_PAYEE, commande.estPayee());

        return values;
    }

    @Override
    protected Commande cursorToRow(Cursor cursor) {
        try {
            Commande commande = new Commande();
            commande.setNumero(cursor.getInt(cursor.getColumnIndexOrThrow(COL_NUMERO_COMMANDE)));
            commande.setEstPayee(cursor.getInt(cursor.getColumnIndexOrThrow(COL_PAYEE)) != 0);
            return commande;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void addBoisson(long numero_commande, String nom_boisson, int quantite) {
        ContentValues values = new ContentValues();
        values.put(COL_NUMERO_COMMANDE, numero_commande);
        values.put(COL_NOM_BOISSON, nom_boisson);
        values.put(COL_QUANTITE, quantite);
        if (db.insert(tableName, null, values) == -1) { // Le couple commande-boisson existe déjà
            db.execSQL("UPDATE " + TABLE_BOISSON_COMMANDE + "" +
                            " SET " + COL_QUANTITE + "=(" + COL_QUANTITE + "+" + Integer.toString(quantite) + ")" +
                            " WHERE " + COL_NUMERO_COMMANDE + " = ?" +
                            " AND " + COL_NOM_BOISSON + " = ?",
                    new String[]{
                            Long.toString(numero_commande),
                            nom_boisson
                    }
            );
        }
    }

    public void removeBoisson(long numero_commande, String nom_boisson) {
        db.delete(TABLE_BOISSON_COMMANDE,
                "?=? AND ?=?", new String[]{
                        COL_NUMERO_COMMANDE,
                        Long.toString(numero_commande),
                        COL_NOM_BOISSON,
                        nom_boisson
                }
        );
    }

    public List<Commande.BoissonCommande> listBoissonCommande(long numero) {
        Cursor cursor = db.rawQuery("SELECT b.*, bc." + COL_QUANTITE + " AS quantite" +
                        " FROM " + TABLE_BOISSON_COMMANDE + " bc" +
                        " LEFT JOIN " + TABLE_BOISSON + " b" +
                        " ON b." + COL_NOM_BOISSON + " = bc." + COL_NOM_BOISSON +
                        " WHERE bc." + COL_NUMERO_COMMANDE + " = ?;",
                new String[]{
                        Long.toString(numero)
                }
        );

        ArrayList<Commande.BoissonCommande> rows = new ArrayList<>();
        while (cursor.moveToNext()) {
            Commande.BoissonCommande boissonCommande = new Commande.BoissonCommande(
                    BoissonDao.instance().cursorToRow(cursor),
                    cursor.getInt(cursor.getColumnIndex(COL_QUANTITE))
            );
            rows.add(boissonCommande);
        }

        cursor.close();
        return rows;
    }

    public List<Commande> listCommandesTable(long numero) {
        Cursor cursor = db.query(TABLE_COMMANDE, null,
                COL_NUMERO_TABLE + " = ?", new String[]{
                        Long.toString(numero)
                },
                null, null, null);

        List<Commande> retval = cursorToRows(cursor);

        cursor.close();
        return retval;
    }

    public float addition(long numero) {
        Cursor cursor = db.rawQuery("SELECT Sum(b." + COL_PRIX + " * bc." + COL_QUANTITE + ")" +
                        " FROM " + TABLE_BOISSON + " b" +
                        " LEFT JOIN " + TABLE_BOISSON_COMMANDE + " bc" +
                        " ON bc." + COL_NOM_BOISSON + " = b." + COL_NOM_BOISSON +
                        " WHERE bc." + COL_NUMERO_COMMANDE + " = ?",
                new String[]{
                        Long.toString(numero)
                }
        );

        float retval = 0;
        if (cursor.moveToNext()) {
            retval = cursor.getInt(0);
        }

        cursor.close();
        return retval;
    }
}
