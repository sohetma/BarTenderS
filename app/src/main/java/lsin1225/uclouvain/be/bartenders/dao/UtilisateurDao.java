package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

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
        this.tableName = TABLE_UTILISATEUR;
        this.idColumn = COL_LOGIN;
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
            Utilisateur utilisateur = new Utilisateur(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_LOGIN)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_MOT_DE_PASSE)),
                    "", Utilisateur.Role.CLIENT //TODO
                    //cursor.getString(cursor.getColumnIndexOrThrow(COL_NOM))
            );
            return utilisateur;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<Utilisateur> listClientsTable(long numero) {
        Cursor cursor = db.rawQuery("SELECT u.*" +
                        " FROM " + TABLE_UTILISATEUR + " u" +
                        " LEFT JOIN " + TABLE_CLIENT + " c" +
                        " ON c." + COL_LOGIN + " = u." + COL_LOGIN +
                        " WHERE c." + COL_NUMERO_TABLE + " = ?",
                new String[]{
                        Long.toString(numero)
                }
        );

        List<Utilisateur> retval = cursorToRows(cursor);

        cursor.close();
        return retval;
    }

    public boolean authenticate(String login, String mot_de_passe) {
        Cursor cursor = db.rawQuery("SELECT Count(*)" +
                        " FROM " + TABLE_UTILISATEUR +
                        " WHERE " + COL_LOGIN + " = ?" +
                        " AND " + COL_MOT_DE_PASSE + " = ?",
                new String[]{
                        login,
                        mot_de_passe
                }
        );

        cursor.moveToNext();
        boolean retval = cursor.getInt(0) == 1;

        cursor.close();
        return retval;
    }
}
