package lsin1225.uclouvain.be.bartenders.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lsin1225.uclouvain.be.bartenders.MyApplication;
import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;
import lsin1225.uclouvain.be.bartenders.model.Utilisateur;


/**
 * Histoires d'utilisateur :
 * - se connecter à l'application
 */
public class LoginActivity extends Activity {

    // UI references.
    private EditText mLoginView;
    private EditText mMotDePasseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginView = (EditText) findViewById(R.id.nom_utilisateur);
        mMotDePasseView = (EditText) findViewById(R.id.mot_de_passe);

        // Si un utilisateur est déjà connecté, on passe directement à l'activité Dispatch
        if (((MyApplication) getApplication()).utilisateurConnecte() != null) {
            Intent intent = new Intent(this, DispatchActivity.class);
            startActivity(intent);
        }
    }

    public void connexion(View view) {
        // Essaie de se connecter avec le login/mot de passe entrés par l'utilisateur
        boolean connexionReussie = ((MyApplication) getApplication()).connexion(
                mLoginView.getText().toString(),
                mMotDePasseView.getText().toString()
        );

        if (connexionReussie) {
            // Affiche un toast indiquant que la connexion a réussie
            Toast.makeText(getApplicationContext(), getString(R.string.alert_connexion_reussie),
                    Toast.LENGTH_SHORT).show();

            // Démarrer l'activité DispatchActivity
            Intent intent = new Intent(this, DispatchActivity.class);
            startActivity(intent);
        } else {
            // Si la connexion a échoué, indiquer un message d'erreur sur les champs
            mLoginView.setError(getString(R.string.error_connexion_echouee));
            mMotDePasseView.setError(getString(R.string.error_connexion_echouee));

            // Vider le champ mot de passe
            mMotDePasseView.setText("");
        }
    }

    public void inscription(View view) {
        NouvelUtilisateurDialogFragment.newInstance(
                mLoginView.getText().toString(),
                mMotDePasseView.getText().toString()
        ).show(
                getFragmentManager(),
                "nouvel_utilisateur"
        );
    }

}
