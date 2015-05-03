package lsin1225.uclouvain.be.bartenders.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lsin1225.uclouvain.be.bartenders.R;
import lsin1225.uclouvain.be.bartenders.dao.BoissonDao;
import lsin1225.uclouvain.be.bartenders.model.Boisson;

/**
 * Histoires d'utilisateur :
 * - consulter la carte
 * - rechercher une boisson par son nom
 */
public class CarteActivity extends ListActivity {

    private EditText aRechercher;

    private List<Boisson> boissons;

    private BoissonListAdapter adapter;

    private class BoissonListAdapter extends ArrayAdapter<Boisson> {

        private static final int resource = R.layout.carte_row;

        private final Context context;
        private final ArrayList<Boisson> values;

        public BoissonListAdapter(Context context, List<Boisson> values) {
            super(context, resource, values);
            this.context = context;
            this.values = new ArrayList<Boisson>(values);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(resource, parent, false);

            TextView nomView = (TextView) rowView.findViewById(R.id.nom);
            TextView prixView = (TextView) rowView.findViewById(R.id.prix);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icone);

            nomView.setText(values.get(position).nom());
            prixView.setText(String.format(
                    getString(R.string.format_prix),
                    values.get(position).prix())
            );

            int imageid = getResources().getIdentifier(
                    "icon_" + values.get(position).categorie().icone(),
                    "drawable", getPackageName()
            );
            if (imageid != 0) {
                imageView.setImageDrawable(getResources().getDrawable(imageid));
            }

            return rowView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);

        aRechercher=(EditText) findViewById(R.id.editText);
        Button recherche=(Button) findViewById(R.id.button);

        boissons = BoissonDao.instance().findAll();

        adapter = new BoissonListAdapter(this, boissons);

        setListAdapter(adapter);
        recherche.setOnClickListener(rechercheListener);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Boisson boisson = (Boisson) getListView().getItemAtPosition(position);

        BoissonDialogFragment.newInstance(boisson.nom()).show(
                getFragmentManager(),
                "boisson"
        );
    }

    private View.OnClickListener rechercheListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {//rechercher les boissons
            String nomBoisson = aRechercher.getText().toString();
            adapter.clear();
            if(nomBoisson.equals("")){ //si le string de recherche est vide
                adapter.addAll(boissons);
            }
            else {
                for (int i = 1; i <= boissons.size(); i++) {
                    if (boissons.get(i).contains(nomBoisson)) {
                        adapter.add(boissons.get(i));
                    }
                }
            }
            adapter.notifyDataSetChanged();
        } //a vérifier : cette adapter.clear supprime l'instance de boissons interne a l'adapter
        // pas celle de cette classe-ci?
    };
}