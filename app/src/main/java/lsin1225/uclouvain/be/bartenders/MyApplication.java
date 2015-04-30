package lsin1225.uclouvain.be.bartenders;

import android.app.Application;

import lsin1225.uclouvain.be.bartenders.dao.Dao;

/**
 * Created by xavier on 30/04/15.
 */
public class MyApplication extends Application {

    private DatabaseHelper mDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        mDbHelper = new DatabaseHelper(this);

        Dao.setDb(mDbHelper.getWritableDatabase());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        mDbHelper.close();
    }
}
