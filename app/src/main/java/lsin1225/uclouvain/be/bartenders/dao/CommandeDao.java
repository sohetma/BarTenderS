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

    public Commande find(int numero) {
        return this.find(Integer.toString(numero));
    }

    public void addBoisson(int numero_commande, String nom_boisson, int quantite) {
        ContentValues values = new ContentValues();
        values.put(COL_NUMERO_COMMANDE, numero_commande);
        values.put(COL_NOM_BOISSON, nom_boisson);
        values.put(COL_QUANTITE, quantite);
        if (db.insert(tableName, null, values) == -1) { // Le couple commande-boisson existe déjà
            db.execSQL("UPDATE ? SET ?=(?+?) WHERE ?=? AND ?=?",
                    new String[]{
                            TABLE_BOISSON_COMMANDE,
                            COL_QUANTITE,
                            COL_QUANTITE,
                            Integer.toString(quantite),
                            COL_NUMERO_COMMANDE,
                            Integer.toString(numero_commande),
                            COL_NOM_BOISSON,
                            nom_boisson
                    }
            );
        }
    }

    public void removeBoisson(int numero_commande, String nom_boisson) {
        db.delete(TABLE_BOISSON_COMMANDE,
                "?=? AND ?=?", new String[]{
                        COL_NUMERO_COMMANDE,
                        Integer.toString(numero_commande),
                        COL_NOM_BOISSON,
                        nom_boisson
                }
        );
    }

    public List<Commande.BoissonCommande> listBoissonCommande(int numero) {
        Cursor cursor = db.rawQuery("SELECT b.*, bc.? AS quantite " +
                        "FROM ? bc " +
                        "LEFT JOIN ? b ON b.? = bc.? " +
                        "WHERE bc.? = ?; ",
                new String[]{
                        COL_QUANTITE,
                        TABLE_BOISSON_COMMANDE,
                        TABLE_BOISSON,
                        COL_NOM_BOISSON,
                        COL_NOM_BOISSON,
                        COL_NUMERO_COMMANDE,
                        Integer.toString(numero)
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
        return rows;
    }

    public List<Commande> listCommandesTable(int numero) {
        Cursor cursor = db.query(TABLE_COMMANDE, null,
                COL_NUMERO_TABLE + " = ?", new String[]{
                        Integer.toString(numero)
                },
                null, null, null);

        return cursorToRows(cursor);
    }

    public float addition(int numero) {
        Cursor cursor = db.rawQuery("SELECT Sum(b." + COL_PRIX + ")" +
                        " FROM " + TABLE_BOISSON + " b" +
                        " LEFT JOIN " + TABLE_BOISSON_COMMANDE + " bc" +
                        " ON bc." + COL_NOM_BOISSON + " = b." + COL_NOM_BOISSON +
                        " WHERE bc." + COL_NUMERO_COMMANDE + " = ?",
                new String[]{
                        Integer.toString(numero)
                }
        );

        cursor.moveToNext();
        return cursor.getInt(0);
    }
}
