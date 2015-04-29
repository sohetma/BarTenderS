package lsin1225.uclouvain.be.bartenders.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;


/**
 * A login screen that offers login via email/password and via Google+ sign in.
 * <p/>
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */
public class LoginActivity extends Activity {

    // UI references.
    private EditText mLoginView;
    private EditText mMotDePasseView;
    private Button mConnexionBouton;
    private View mConnexionEchoueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginView = (EditText) findViewById(R.id.nom_utilisateur);
        mMotDePasseView = (EditText) findViewById(R.id.mot_de_passe);
        mConnexionBouton = (Button) findViewById(R.id.connexion_bouton);
        mConnexionEchoueeView = findViewById(R.id.connexion_echouee);
    }

    public void connexion(View view) {
        boolean connexionReussie = UtilisateurDao.instance().authenticate(
                mLoginView.getText().toString(),
                mMotDePasseView.getText().toString()
        );
        if (connexionReussie) { // La connexion a réussi
            Toast.makeText(getApplicationContext(), getString(R.string.alert_connexion_reussie),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.alert_connexion_echouee),
                    Toast.LENGTH_SHORT).show();
            mConnexionEchoueeView.setVisibility(View.VISIBLE);
            mMotDePasseView.setText("");
        }
    }
}



