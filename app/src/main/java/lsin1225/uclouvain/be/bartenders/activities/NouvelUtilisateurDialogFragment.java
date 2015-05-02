package lsin1225.uclouvain.be.bartenders.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lsin1225.uclouvain.be.bartenders.R;

/**
 * Histoires d'utilisateur :
 * - se créer un compte
 */
public class NouvelUtilisateurDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LOGIN = "login";
    private static final String ARG_MOT_DE_PASSE = "mot_de_passe";

    // TODO: Rename and change types of parameters
    private String mLogin;
    private String mMotDePasse;

    /**
     * Crée une nouvelle instance du fragment avec le login et mot de passe en paramètres.
     *
     * @param login Le login de l'utilisateur à créer.
     * @param motDePasse Le mot de passe de l'utilisateur à créer.
     * @return Une nouvelle instande du fragment NouvelUtilisateurDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(dialogView);

        return builder.create();
    }

}
