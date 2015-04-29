package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import lsin1225.uclouvain.be.bartenders.model.Utilisateur;

import static lsin1225.uclouvain.be.bartenders.dao.SqlConstants.*;

/**
 * Created by xavier on 28/04/15.
 */
public class UtilisateurDao extends Dao<Utilisateur> {

    private static UtilisateurDao ourInstance = new UtilisateurDao();

    public static UtilisateurDao instance() {
        return ourInstance;
    }

    private UtilisateurDao() {
        this.tableName = TABLE_BOISSON;
        this.idColumn = COL_NOM_BOISSON;
    }

    @Override
    ContentValues rowToContentValues(Utilisateur utilisateur) {
        ContentValues values = new ContentValues();

        values.put(COL_LOGIN, utilisateur.login());
        values.put(COL_MOT_DE_PASSE, utilisateur.motDePasse());
        //values.put(COL_NOM, utilisateur.nom());

        return values;
    }

    @Override
    Utilisateur cursorToRow(Cursor cursor) {
        try {
            return new Utilisateur(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_LOGIN)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_MOT_DE_PASSE)),
                    "", Utilisateur.Role.CLIENT //TODO
                    //cursor.getString(cursor.getColumnIndexOrThrow(COL_NOM))
            );
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<Utilisateur> listClientsTable(int numero) {
        Cursor cursor = db.rawQuery("SELECT u.*" +
                        "FROM ? u" +
                        "LEFT JOIN ? c ON c.? = u.?" +
                        "WHERE c.? = ?",
                new String[]{
                        TABLE_UTILISATEUR,
                        TABLE_CLIENT,
                        COL_LOGIN,
                        COL_LOGIN,
                        COL_NUMERO_TABLE,
                        Integer.toString(numero)
                }
        );

        return cursorToRows(cursor);
    }

    public boolean authenticate(String login, String mot_de_passe) {
        Cursor cursor = db.rawQuery("SELECT Count(*)" +
                        "FROM ?" +
                        "WHERE ? = ? AND ? = ?",
                new String[]{
                        COL_LOGIN,
                        TABLE_UTILISATEUR,
                        COL_LOGIN,
                        login,
                        COL_MOT_DE_PASSE,
                        mot_de_passe
                }
        );

        cursor.moveToNext();
        return cursor.getInt(0) == 1;
    }
}
