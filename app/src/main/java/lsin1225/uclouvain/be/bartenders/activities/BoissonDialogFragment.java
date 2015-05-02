package lsin1225.uclouvain.be.bartenders.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.BoissonDao;
import lsin1225.uclouvain.be.bartenders.model.Boisson;

/**
 * Histoires d'utilisateur :
 * - commander une boisson
 * - consulter les informations d'une boisson
 * - évaluer une boisson
 */
public class BoissonDialogFragment extends DialogFragment {

    private static final String ARG_NOM_BOISSON = "nom_boisson";

    public static BoissonDialogFragment newInstance(String nomBoisson) {
        BoissonDialogFragment f = new BoissonDialogFragment();

        Bundle args = new Bundle();
        args.putString(ARG_NOM_BOISSON, nomBoisson);
        f.setArguments(args);

        return f;
    }

    public BoissonDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_carte_boisson, null, false);

        TextView nomView = (TextView) dialogView.findViewById(R.id.nom);
        TextView descriptionView = (TextView) dialogView.findViewById(R.id.description);
        View descriptionLayout = dialogView.findViewById(R.id.description_layout);
        TextView prixView = (TextView) dialogView.findViewById(R.id.prix);
        ImageView imageView = (ImageView) dialogView.findViewById(R.id.icone);

        String nom_boisson = getArguments().getString("nom_boisson");
        Boisson boisson = null;
        if (nom_boisson != null) {
            boisson = BoissonDao.instance().find(nom_boisson);
        } else {
            Log.e("BoissonDialogFragment", "boisson == null");
            this.getDialog().cancel();
            return null;
        }

        nomView.setText(boisson.nom());
        if (!boisson.description().isEmpty()) {
            descriptionView.setText(boisson.description());
        } else {
            descriptionLayout.setVisibility(View.GONE);
        }
        prixView.setText(String.format(
                getString(R.string.format_prix),
                boisson.prix()
        ));

        int imageid = getResources().getIdentifier(
                "icon_" + boisson.categorie().icone(),
                "drawable", getActivity().getPackageName()
        );
        if (imageid != 0) {
            imageView.setImageDrawable(getResources().getDrawable(imageid));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(dialogView)
                .setPositiveButton(R.string.action_ajouter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO ajouter à la commande
                    }
                })
                .setNegativeButton(R.string.action_retour, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        BoissonDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
