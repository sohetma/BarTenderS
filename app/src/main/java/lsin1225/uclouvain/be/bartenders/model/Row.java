package lsin1225.uclouvain.be.bartenders.model;

import lsin1225.uclouvain.be.bartenders.dao.Dao;

/**
 * Created by xavier on 28/04/15.
 */
public abstract class Row {
    private boolean saved = false;

    abstract protected Dao defaultDao();

    public void save() {
        if (!saved) {
            defaultDao().insert(this);
            saved = true;
        } else {
            defaultDao().update(this);
        }
    }

    public void destroy() {
        defaultDao().remove(this);
    }
}
