package lsin1225.uclouvain.be.bartenders.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    protected SQLiteDatabase db;

    abstract ContentValues rowToContentValues(T row);

    abstract T cursorToRow(Cursor cursor);

    List<T> cursorToRows(Cursor cursor) {
        ArrayList<T> rows = new ArrayList<>();
        while (cursor.moveToNext()) {
            rows.add(cursorToRow(cursor));
        }
        return rows;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(T row) {
        ContentValues values = rowToContentValues(row);
        db.insert(tableName, null, values);
    }

    public void update(T row) {
        ContentValues values = rowToContentValues(row);
        db.update(tableName, values,
                idColumn + "=?", new String[]{values.getAsString(idColumn)});
    }

    public T find(String id) {
        Cursor cursor = db.query(tableName, null,
                idColumn + "=?", new String[]{id},
                null, null, null, "1");
        cursor.moveToNext();
        return cursorToRow(cursor);
    }

    public List<T> findAll() {
        Cursor cursor = db.query(tableName, null,
                null, null,
                null, null, null);
        return cursorToRows(cursor);
    }

    public void remove(T row) {
        ContentValues values = rowToContentValues(row);
        db.delete(tableName,
                idColumn+"=?", new String[]{values.getAsString(idColumn)});
    }
}
