package lsin1225.uclouvain.be.bartenders.model;

import lsin1225.uclouvain.be.bartenders.dao.Dao;

/**
 * Created by xavier on 28/04/15.
 */
public abstract class Row {
    protected long rowid = 0;

    abstract protected Dao defaultDao();

    public void save() {
        if (rowid == 0) {
            rowid = defaultDao().insert(this);
        } else {
            defaultDao().update(this);
        }
    }

    public void destroy() {
        defaultDao().remove(this);
    }
}
