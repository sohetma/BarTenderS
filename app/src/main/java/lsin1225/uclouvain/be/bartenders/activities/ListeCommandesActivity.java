package lsin1225.uclouvain.be.bartenders.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lsin1225.uclouvain.be.bartenders.MyApplication;
import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.CommandeDao;
import lsin1225.uclouvain.be.bartenders.model.Commande;

/**
 * Histoires d'utilisateur :
 * - enregistrer une nouvelle commande
 * - associer une commande à une table
 */
public class ListeCommandesActivity extends TableActivity {

    private class CommandeTableAdapter extends ArrayAdapter<Commande> {

        private static final int resource = R.layout.row_commande;

        private final Context context;
        private final ArrayList<Commande> values;

        public CommandeTableAdapter(Context context, List<Commande> values) {
            super(context, resource, values);
            this.context = context;
            this.values = new ArrayList<>(values);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(resource, parent, false);

            TextView numeroCommandeView = (TextView) rowView.findViewById(R.id.numero_commande);
            TextView numeroTableView = (TextView) rowView.findViewById(R.id.numero_table);
            TextView additionView = (TextView) rowView.findViewById(R.id.addition);
            CheckBox estPayeeView = (CheckBox) rowView.findViewById(R.id.est_payee);

            numeroCommandeView.setText(Long.toString(values.get(position).numero()));
            numeroTableView.setText(Long.toString(values.get(position).table().numero()));
            additionView.setText(String.format(
                            getString(R.string.format_prix),
                            values.get(position).addition())
            );
            estPayeeView.setChecked(values.get(position).estPayee());

            return rowView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_commandes);


        List<Commande> commandes = CommandeDao.instance().findAll(true);

        CommandeTableAdapter adapter = new CommandeTableAdapter(this, commandes);

        // Ajouter le header
        getTableView().addView(getLayoutInflater().inflate(R.layout.header_commande, null));
        getTableView().addView(getSeparatorView());

        setTableAdapter(adapter);
    }

    public void onNouvelleCommandeClick(View v) {
        Commande commande = new Commande();
        commande.save();

        ouvrirCommande(commande);
    }

    @Override
    protected void onTableItemClick(TableLayout t, Adapter adapter, View v, int position, long id) {
        Commande commande = (Commande) adapter.getItem(position);

        ouvrirCommande(commande);
    }

    private void ouvrirCommande(Commande commande) {
        ((MyApplication) getApplication()).setCommandeActuelle(commande.numero());

        // Lance l'activité d'affichage de commande
        Intent intent = new Intent(this, CommandeActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

        // Ferme cette activité
        finish();
    }
}
