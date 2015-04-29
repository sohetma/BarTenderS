package lsin1225.uclouvain.be.bartenders.trash;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import lsin1225.uclouvain.be.bartenders.R;

/**
 * Created by alex on 4/26/15.
 * Nécéssaire pour qu'android crée et mette à jour la base de donnée et les DAO
 * cfr http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_4.html#Use-With-Android
 * point 4.1.1
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "BarTender";
    private static final int DATABASE_VERSION = 1;

    /**
     * Data Access Objects:
     * structure: Dao<TO, K> où TO est le Transfer Object
     * et K est la classe de la clé principale de la table.
     */
    private Dao<BoissonTO, String> boissonDAO;
    private Dao<CategorieTO, String> categorieDAO;
    private Dao<CommandeTO, Integer> commandeDAO;
    private Dao<BoissonCommandeTO, Void> boissonCommandeDAO;
    private Dao<TableTO, Integer> tableDAO;
    private Dao<UtilisateurTO, String> utilisateurDAO;
    private Dao<ClientTO, String> clientDAO;
    private Dao<ServeurTO, String> serveurDAO;
    private Dao<GestionnaireTO, String> gestionnaireDAO;
    private Dao<EvaluationTO, Void> evaluationDAO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // création de la table
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // mise à jour de l'application
    }

    /**
     * Returns an instance of the data access object
     * @return
     * @throws SQLException
     */
    public Dao<BoissonTO, String> getBoissonDAO() throws SQLException {
        if(boissonDAO == null) {
            boissonDAO = getDao(BoissonTO.class);
        }
        return boissonDAO;
    }

    public Dao<CategorieTO, String> getCategorieDAO() {
        return categorieDAO;
    }

    public Dao<CommandeTO, Integer> getCommandeDAO() {
        return commandeDAO;
    }

    public Dao<BoissonCommandeTO, Void> getBoissonCommandeDAO() {
        return boissonCommandeDAO;
    }

    public Dao<TableTO, Integer> getTableDAO() {
        return tableDAO;
    }

    public Dao<UtilisateurTO, String> getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public Dao<ClientTO, String> getClientDAO() {
        return clientDAO;
    }

    public Dao<ServeurTO, String> getServeurDAO() {
        return serveurDAO;
    }

    public Dao<GestionnaireTO, String> getGestionnaireDAO() {
        return gestionnaireDAO;
    }

    public Dao<EvaluationTO, Void> getEvaluationDAO() {
        return evaluationDAO;
    }
}
