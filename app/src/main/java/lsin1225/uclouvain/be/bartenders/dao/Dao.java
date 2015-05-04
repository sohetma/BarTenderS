package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lsin1225.uclouvain.be.bartenders.model.Boisson;
import lsin1225.uclouvain.be.bartenders.model.Row;

/**
 * Created by xavier on 28/04/15.
 */
abstract public class Dao<T extends Row> {
    protected String tableName;
    protected String idColumn;
    protected static SQLiteDatabase db;

    public static void setDb(SQLiteDatabase theDb) {
        db = theDb;
    }


    abstract ContentValues rowToContentValues(T row);

    abstract T cursorToRow(Cursor cursor);

    List<T> cursorToRows(Cursor cursor) {
        ArrayList<T> rows = new ArrayList<>();
        while (cursor.moveToNext()) {
            rows.add(cursorToRow(cursor));
        }
        return rows;
    }

    public long insert(T row) {
        ContentValues values = rowToContentValues(row);
        return db.insert(tableName, null, values);
    }

    public void update(T row) {
        ContentValues values = rowToContentValues(row);
        db.update(tableName, values,
                idColumn + "=?", new String[]{values.getAsString(idColumn)});
    }

    public void remove(T row) {
        ContentValues values = rowToContentValues(row);
        db.delete(tableName,
                idColumn + "=?", new String[]{values.getAsString(idColumn)});
    }

    public T find(String id) {
        Cursor cursor = db.query(tableName, null,
                idColumn + "=?", new String[]{id},
                null, null, null, "1");

        T retval = null;
        if (cursor.moveToNext()) {
            retval = cursorToRow(cursor);
        }
        if (retval == null) {
            Log.e("Dao.find", tableName + ": retval == null");
        }

        cursor.close();
        return retval;
    }

    public T find(long id) {
        Cursor cursor = db.query(tableName, null,
                "ROWID = ?", new String[]{Long.toString(id)},
                null, null, null, "1");

        T retval = null;
        if (cursor.moveToNext()) {
            retval = cursorToRow(cursor);
        }

        cursor.close();
        return retval;
    }

    public List<T> findAll(boolean desc) {
        Cursor cursor = db.query(
                tableName,
                null, null, null, null, null,
                idColumn + (desc ? " DESC " : ""));
        List<T> retval = cursorToRows(cursor);

        cursor.close();
        return retval;
    }

    public List<T> findAll() {
        return findAll(false);
    }
}
