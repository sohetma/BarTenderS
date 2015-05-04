package lsin1225.uclouvain.be.bartenders.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import lsin1225.uclouvain.be.bartenders.Closable;
import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;
import lsin1225.uclouvain.be.bartenders.model.Utilisateur;

/**
 * Histoires d'utilisateur :
 * - se créer un compte
 */
public class NouvelUtilisateurDialogFragment extends DialogFragment implements Closable {
    private static final String ARG_LOGIN = "login";
    private static final String ARG_MOT_DE_PASSE = "mot_de_passe";

    private static final int LONGUEUR_LOGIN_MIN = 4;
    private static final int LONGUEUR_LOGIN_MAX = 40;
    private static final int LONGUEUR_MOT_DE_PASSE_MIN = 6;
    private static final int LONGUEUR_MOT_DE_PASSE_MAX = 25;
    private static final int LONGUEUR_NOM_MIN = 4;
    private static final int LONGUEUR_NOM_MAX = 100;

    /**
     * Crée une nouvelle instance du fragment avec le login et mot de passe en paramètres.
     *
     * @param login Le login de l'utilisateur à créer.
     * @param motDePasse Le mot de passe de l'utilisateur à créer.
     * @return Une nouvelle instance du fragment NouvelUtilisateurDialogFragment.
     */
    public static NouvelUtilisateurDialogFragment newInstance(String login, String motDePasse) {
        NouvelUtilisateurDialogFragment fragment = new NouvelUtilisateurDialogFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LOGIN, login);
        args.putString(ARG_MOT_DE_PASSE, motDePasse);
        fragment.setArguments(args);
        return fragment;
    }

    public NouvelUtilisateurDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_nouvel_utilisateur, null, false);

        final EditText loginView = (EditText) dialogView.findViewById(R.id.nom_utilisateur);
        final EditText motDePasseView = (EditText) dialogView.findViewById(R.id.mot_de_passe);
        final EditText motDePasse2View = (EditText) dialogView.findViewById(R.id.mot_de_passe2);
        final EditText nomView = (EditText) dialogView.findViewById(R.id.nom);

        loginView.setText(getArguments().getString(ARG_LOGIN));
        motDePasseView.setText(getArguments().getString(ARG_MOT_DE_PASSE));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final AlertDialog dialog = builder.setView(dialogView)
                .setTitle(R.string.action_inscription)
                .setPositiveButton(R.string.action_valider, null)
                .setNegativeButton(R.string.action_retour, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NouvelUtilisateurDialogFragment.this.getDialog().cancel();
                    }
                })
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
                new NouvelUtilisateurClickListener(loginView, motDePasseView, motDePasse2View, nomView, null, getActivity(), this));

        return dialog;
    }

    @Override
    public void closeIt() {
        dismiss();
    }
}

/*
        Utilisateur utilisateur
                = UtilisateurDao.instance().find(mLoginView.getText().toString());

        if (utilisateur != null) {
            Toast.makeText(getApplicationContext(), getString(R.string.erreur_login_deja_pris),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        */