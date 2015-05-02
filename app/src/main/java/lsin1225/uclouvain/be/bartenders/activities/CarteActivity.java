package lsin1225.uclouvain.be.bartenders.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    private class BoissonListAdapter extends ArrayAdapter<Boisson> {

        private static final int resource = R.layout.carte_row;

        private final Context context;
        private final List<Boisson> values;

        public BoissonListAdapter(Context context, List<Boisson> values) {
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

        List<Boisson> boissons = BoissonDao.instance().findAll();

        ListAdapter adapter = new BoissonListAdapter(
                this,
                boissons
        );

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Boisson boisson = (Boisson) getListView().getItemAtPosition(position);

        BoissonDialogFragment.newInstance(boisson.nom()).show(
                getFragmentManager(),
                "boisson"
        );
    }

}
