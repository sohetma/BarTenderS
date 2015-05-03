package lsin1225.uclouvain.be.bartenders.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lsin1225.uclouvain.be.bartenders.MyApplication;
import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.CommandeDao;
import lsin1225.uclouvain.be.bartenders.model.Commande;

/**
 * Histoires d'utilisateur :
 * - obtenir l'addition d'une table
 * - confirmer un payement
 */
public class CommandeActivity extends ListActivity {

    private class BoissonCommandeListAdapter extends ArrayAdapter<Commande.BoissonCommande> {

        private static final int resource = R.layout.row_boisson_commande;

        private final Context context;
        private final List<Commande.BoissonCommande> values;

        public BoissonCommandeListAdapter(Context context,
                                          List<Commande.BoissonCommande> values) {
            super(context, resource, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(resource, parent, false);

            TextView nomView = (TextView) rowView.findViewById(R.id.nom);
            TextView prixView = (TextView) rowView.findViewById(R.id.prix);
            TextView quantiteView = (TextView) rowView.findViewById(R.id.quantite);

            nomView.setText(values.get(position).boisson().nom());
            prixView.setText(String.format(
                            getString(R.string.format_prix),
                            values.get(position).boisson().prix()
                    ));
            quantiteView.setText(Integer.toString(values.get(position).quantite()));

            return rowView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        TextView additionView = (TextView) findViewById(R.id.addition);

        Commande commande = CommandeDao.instance().find(
                ((MyApplication) getApplication()).commandeActuelle()
        );

        // S'il n'y a pas de commande avec ce numéro, on termine l'activité
        if (commande == null) {
            finish();
            Toast.makeText(getApplicationContext(), getString(R.string.error_commande_inexistante),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        additionView.setText(String.format(
                        getString(R.string.format_prix),
                        commande.addition()
                ));

        List<Commande.BoissonCommande> bcs = commande.listeBoissons();
        BoissonCommandeListAdapter adapter = new BoissonCommandeListAdapter(
                this, bcs
        );

        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commande, menu);
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
}
