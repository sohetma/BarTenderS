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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import lsin1225.uclouvain.be.bartenders.MyApplication;
import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.BoissonDao;
import lsin1225.uclouvain.be.bartenders.model.Boisson;

/**
 * Histoires d'utilisateur :
 * - commander une boisson
 * - consulter les informations d'une boisson
 * - évaluer une boisson
 */
public class InventaireDialogFragment extends DialogFragment {

    private static final String ARG_NOM_BOISSON = "nom_boisson";

    public static BoissonDialogFragment newInstance(String nomBoisson) {
        BoissonDialogFragment f = new BoissonDialogFragment();

        Bundle args = new Bundle();
        args.putString(ARG_NOM_BOISSON, nomBoisson);
        f.setArguments(args);

        return f;
    }

    public InventaireDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_modif_inventaire, null, false);

        EditText nom = (EditText) dialogView.findViewById(R.id.nom);
        EditText stock= (EditText) dialogView.findViewById(R.id.stock);
        EditText max= (EditText) dialogView.findViewById(R.id.max);
        EditText seuil= (EditText) dialogView.findViewById(R.id.seuil);
        EditText prix = (EditText) dialogView.findViewById(R.id.prix);
        ImageView imageView = (ImageView) dialogView.findViewById(R.id.icone);

        String nom_boisson = getArguments().getString("nom_boisson");
        Boisson boisson = null;
        if (nom_boisson != null) {
            boisson = BoissonDao.instance().find(nom_boisson);
        } else {
            this.getDialog().cancel();
            return null;
        }

        nom.setText(boisson.nom());
        stock.setText(boisson.stock());
        max.setText(boisson.stockMax());
        seuil.setText(boisson.stockSeuil());
        prix.setText(String.format(
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
                        InventaireDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
