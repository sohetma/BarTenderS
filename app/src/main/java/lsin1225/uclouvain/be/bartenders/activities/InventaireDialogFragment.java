package lsin1225.uclouvain.be.bartenders.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

    public static InventaireDialogFragment newInstance(String nomBoisson) {
        InventaireDialogFragment f = new InventaireDialogFragment();

        Bundle args = new Bundle();
        args.putString(ARG_NOM_BOISSON, nomBoisson);
        f.setArguments(args);

        return f;
    }

    public InventaireDialogFragment() {
    }

    private EditText nom;
    private EditText stock;
    private EditText max;
    private EditText seuil;
    private EditText prix;

    private Boisson boisson;

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //pour le bouton apply
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boisson.setNom(nom.getText().toString());
            boisson.setStock(Integer.parseInt(stock.getText().toString()));
            boisson.setStockMax(Integer.parseInt(max.getText().toString()));
            boisson.setStockSeuil(Integer.parseInt(seuil.getText().toString()));
            boisson.setPrix(Integer.parseInt(prix.getText().toString()));
        }
    };


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_modif_inventaire, null, false);

        nom = (EditText) dialogView.findViewById(R.id.nom);
        stock= (EditText) dialogView.findViewById(R.id.stock);
        max= (EditText) dialogView.findViewById(R.id.max);
        seuil= (EditText) dialogView.findViewById(R.id.seuil);
        prix = (EditText) dialogView.findViewById(R.id.prix);

        ImageView imageView = (ImageView) dialogView.findViewById(R.id.icone);
        Button button =(Button) dialogView.findViewById(R.id.apply);

        String nom_boisson = getArguments().getString("nom_boisson");
        boisson = null;
        if (nom_boisson != null) {
            boisson = BoissonDao.instance().find(nom_boisson);
        } else {
            this.getDialog().cancel();
            return null;
        }

        nom.setText(boisson.nom());
        stock.setText(Integer.toString(boisson.stock()));
        max.setText(Integer.toString(boisson.stockMax()));
        seuil.setText(Integer.toString(boisson.stockSeuil()));
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

        nom.addTextChangedListener(textWatcher);
        stock.addTextChangedListener(textWatcher);
        max.addTextChangedListener(textWatcher);
        seuil.addTextChangedListener(textWatcher);
        prix.addTextChangedListener(textWatcher);

        button.setOnClickListener(buttonListener);

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
