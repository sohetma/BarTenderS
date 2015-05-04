package lsin1225.uclouvain.be.bartenders.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import lsin1225.uclouvain.be.bartenders.Closable;
import lsin1225.uclouvain.be.bartenders.MyApplication;
import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.model.Utilisateur;

/**
 * Histoires d'utilisateur :
 * - mettre à jour les informations personnelles de mon compte
 */
public class MonCompteActivity extends Activity implements Closable {

    private EditText mLogin;
    private EditText mPassword;
    private EditText mPassword2;
    private EditText mName;

    private Button mAnnuler;
    private Button mModifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_compte);

        mLogin = (EditText) findViewById(R.id.editTextLogin);
        mPassword = (EditText) findViewById(R.id.editTextMDP);
        mPassword2 = (EditText) findViewById(R.id.editTextMDP2);
        mName = (EditText) findViewById(R.id.editTextNom);

        mAnnuler = (Button) findViewById(R.id.boutonAnnuler);
        mModifier = (Button) findViewById(R.id.boutonModifier);

        Utilisateur curUser = ((MyApplication) getApplication()).utilisateurConnecte();

        mLogin.setText(curUser.nom());
        mPassword.setText(curUser.motDePasse());
        mPassword2.setText(curUser.motDePasse());
        mName.setText(curUser.nom());

        mAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mModifier.setOnClickListener(
                new NouvelUtilisateurClickListener(mLogin, mPassword, mPassword2, mName, curUser, this, this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mon_compte, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void closeIt() {
        this.finish();
    }
}
