package lsin1225.uclouvain.be.bartenders.activities;


import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import lsin1225.uclouvain.be.bartenders.R;

/**
 * Gère l'affichage du menu principal de l'application, présentée après le login
 *
 * @author Degomme Bruno
 * @version 1
 */
public class DispatchActivity extends Activity {
    private OnClickListener clickListenerBoutonCar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            carte(v);
        }
    };

    private OnClickListener clickListenerBoutonCom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            commande(v);
        }
    };

    private OnClickListener clickListenerBoutonAdd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addition(v);
        }
    };

    private OnClickListener clickListenerBoutonInv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            inventaire(v);
        }
    };

    Button car = null;
    Button com = null;
    Button add = null;
    Button inv = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        car = (Button)findViewById(R.id.carte_bouton);
        com = (Button)findViewById(R.id.commande_bouton);
        add = (Button)findViewById(R.id.addition_bouton);
        inv = (Button)findViewById(R.id.inventaire_bouton);

        car.setOnClickListener(clickListenerBoutonCar);
        com.setOnClickListener(clickListenerBoutonCom);
        add.setOnClickListener(clickListenerBoutonAdd);
        inv.setOnClickListener(clickListenerBoutonInv);
    }


    /**
     * @note Les méthodes c sont appelées lors d'un clic sur les boutons
     * correspondant grâce à l'attribut onClick présent dans les fichiers de layout.
     *
     * Lire http://developer.android.com/reference/android/R.attr.html#onClick
     */

    /**
     * Lance l'activité d'affichage de la la carte des boissons
     */
    public void carte(View v) {
        Intent intent = new Intent(this, CarteActivity.class);
        startActivity(intent);
    }

    /**
     * Lance l'activité de commande des boissons
     */
    public void commande(View v) {
        Intent intent = new Intent(this, CommandeActivity.class);
        startActivity(intent);
    }


    /**
     * Lance l'activité de récuppération de l'addition.
     */
    public void addition(View v) {
        Intent intent = new Intent(this, AdditionActivity.class);
        startActivity(intent);
    }


    /**
     * Lance l'activité de visionage des additions
     */
    public void inventaire(View v) {
        Intent intent = new Intent(this, InventaireActivity.class);
        startActivity(intent);
    }
}