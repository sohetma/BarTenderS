package lsin1225.uclouvain.be.bartenders.activities;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lsin1225.uclouvain.be.bartenders.Closable;
import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;
import lsin1225.uclouvain.be.bartenders.model.Utilisateur;

/**
 * Created by alex on 5/4/15.
 * Effectue toutes les vérifications nécéssaires à la création ou la modification d'un utilisateur.
 * Si utilisateur = null, un nouvel utilisateur sera créé,
 * Si utilisateur ≠ null, il sera modifié.
 *
 */
public class NouvelUtilisateurClickListener implements View.OnClickListener {

    private static final int LONGUEUR_LOGIN_MIN = 4;
    private static final int LONGUEUR_LOGIN_MAX = 40;
    private static final int LONGUEUR_MOT_DE_PASSE_MIN = 6;
    private static final int LONGUEUR_MOT_DE_PASSE_MAX = 25;
    private static final int LONGUEUR_NOM_MIN = 4;
    private static final int LONGUEUR_NOM_MAX = 100;

    private EditText loginView;
    private EditText motDePasseView;
    private EditText motDePasse2View;
    private EditText nomView;

    private Utilisateur utilisateur;

    private Context context;

    private Closable closable;

    public NouvelUtilisateurClickListener(EditText loginView, EditText motDePasse2View, EditText motDePasseView, EditText nomView, Utilisateur utilisateur, Context context, Closable closable) {
        this.loginView = loginView;
        this.motDePasse2View = motDePasse2View;
        this.motDePasseView = motDePasseView;
        this.nomView = nomView;
        this.utilisateur = utilisateur;
        this.context = context;
        this.closable = closable;
    }

    @Override
    public void onClick(View v) {
        String login = loginView.getText().toString(),
                motDePasse = motDePasseView.getText().toString(),
                motDePasse2 = motDePasse2View.getText().toString(),
                nom = nomView.getText().toString();

        // si on veut créer un nouvel utilisateur mais le login est déjà pris:
        if (utilisateur == null
                && UtilisateurDao.instance().find(login) != null) {
            loginView.setError(context.getString(R.string.erreur_login_deja_pris));
            return;
        }

        if (login.isEmpty()) {
            loginView.setError(context.getString(R.string.erreur_champ_vide));
            return;
        }

        if (motDePasse.isEmpty()) {
            motDePasseView.setError(context.getString(R.string.erreur_champ_vide));
            return;
        }

        if (nom.isEmpty()) {
            nomView.setError(context.getString(R.string.erreur_champ_vide));
            return;
        }

        if (login.length() < LONGUEUR_LOGIN_MIN) {
            loginView.setError(String.format(
                    context.getString(R.string.erreur_champ_trop_court),
                    LONGUEUR_LOGIN_MIN
            ));
            return;
        }

        if (login.length() > LONGUEUR_LOGIN_MAX) {
            motDePasseView.setError(String.format(
                    context.getString(R.string.erreur_champ_trop_long),
                    LONGUEUR_LOGIN_MAX
            ));
            return;
        }

        if (motDePasse.length() < LONGUEUR_MOT_DE_PASSE_MIN) {
            motDePasseView.setError(String.format(
                    context.getString(R.string.erreur_champ_trop_court),
                    LONGUEUR_MOT_DE_PASSE_MIN
            ));
            return;
        }
        if (motDePasse.length() > LONGUEUR_MOT_DE_PASSE_MAX) {
            motDePasseView.setError(String.format(
                    context.getString(R.string.erreur_champ_trop_long),
                    LONGUEUR_MOT_DE_PASSE_MAX
            ));
            return;
        }

        if (nom.length() < LONGUEUR_NOM_MIN) {
            nomView.setError(String.format(
                    context.getString(R.string.erreur_champ_trop_court),
                    LONGUEUR_NOM_MIN
            ));
            return;
        }
        if (nom.length() > LONGUEUR_NOM_MAX) {
            nomView.setError(String.format(
                    context.getString(R.string.erreur_champ_trop_long),
                    LONGUEUR_NOM_MAX
            ));
            return;
        }

        if (!motDePasse.equals(motDePasse2)) {
            motDePasse2View.setError(context.getString(R.string.erreur_mot_de_passes_differents));
            return;
        }

        if (utilisateur == null) {
            utilisateur = new Utilisateur(login, motDePasse, nom, Utilisateur.Role.CLIENT);
        } else {
            utilisateur.setLogin(login);
            utilisateur.setMotDePasse(motDePasse);
            utilisateur.setNom(nom);
        }

        utilisateur.save();


        Toast.makeText(context, context.getString(R.string.alert_inscription_reussie),
                Toast.LENGTH_SHORT).show();
    }
}
