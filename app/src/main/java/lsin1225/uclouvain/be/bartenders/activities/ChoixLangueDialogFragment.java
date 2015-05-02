package lsin1225.uclouvain.be.bartenders.activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lsin1225.uclouvain.be.bartenders.R;

/**
 * Histoires d'utilisateur :
 * - accéder au choix de langue du programme
 */
public class ChoixLangueDialogFragment extends DialogFragment {

    /**
     * Crée une nouvelle instance du fragment.
     *
     * @return Une nouvelle instande du fragment ChoixLangueDialogFragment.
     */
    public static ChoixLangueDialogFragment newInstance(String param1, String param2) {
        ChoixLangueDialogFragment fragment = new ChoixLangueDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ChoixLangueDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_choix_langue, null, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(dialogView);

        return builder.create();
    }


}
