package lsin1225.uclouvain.be.bartenders;

import android.app.Application;
import android.content.SharedPreferences;

import lsin1225.uclouvain.be.bartenders.dao.Dao;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;
import lsin1225.uclouvain.be.bartenders.model.Utilisateur;

/**
 * Created by xavier on 30/04/15.
 */
public class MyApplication extends Application {

    private static final String PREFERENCES_KEY = "bartender_settings";
    private static final String KEY_LOGIN = "utilisateur.login";
    private static final String KEY_MOT_DE_PASSE = "utilisateur.mot_de_passe";
    private static final String KEY_COMMANDE_ACTUELLE = "commande_actuelle";

    private DatabaseHelper mDbHelper;

    private Utilisateur mUtilisateurConnecte = null;
    private long mCommandeActuelle = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        mDbHelper = new DatabaseHelper(this);

        Dao.setDb(mDbHelper.getWritableDatabase());

        SharedPreferences settings = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
        String login = settings.getString(KEY_LOGIN, "");
        String motDePasse = settings.getString(KEY_MOT_DE_PASSE, "");
        mCommandeActuelle = settings.getLong(KEY_COMMANDE_ACTUELLE, 0);

        connexion(login, motDePasse, false);
    }

    public boolean connexion(String login, String motDePasse) {
        return connexion(login, motDePasse, true);
    }

    public boolean connexion(String login, String motDePasse, boolean sauvegarder) {
        // Compare le login/mot de passe avec la db
        boolean connexionReussie = UtilisateurDao.instance().authenticate(
                login, motDePasse
        );

        if (connexionReussie) {
            // Récupère l'utilisateur connecté et stocke le dans l'état de l'application
            mUtilisateurConnecte = UtilisateurDao.instance().find(login);


            // Si sauvegarder est true, alors on enregistre l'utilisateur connecté dans les
            // settings
            if (sauvegarder) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE).edit();
                editor.putString(KEY_LOGIN, login);
                editor.putString(KEY_MOT_DE_PASSE, motDePasse);
                editor.apply();
            }
        }

        return connexionReussie;
    }

    /**
     * Supprime l'état concernant l'utilisateur connecté.
     */
    public void deconnexion() {
        mUtilisateurConnecte = null;

        SharedPreferences settings = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
        settings.edit().remove(KEY_LOGIN);
        settings.edit().remove(KEY_MOT_DE_PASSE);
        settings.edit().apply();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        mDbHelper.close();
    }

    public Utilisateur utilisateurConnecte() {
        return mUtilisateurConnecte;
    }

    public long commandeActuelle() {
        return mCommandeActuelle;
    }

    public void setCommandeActuelle(long commandeActuelle) {
        mCommandeActuelle = commandeActuelle;

        SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE).edit();
        editor.putLong(KEY_COMMANDE_ACTUELLE, commandeActuelle);
        editor.apply();
    }
}
