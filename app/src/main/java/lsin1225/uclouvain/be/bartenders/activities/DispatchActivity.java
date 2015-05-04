package lsin1225.uclouvain.be.bartenders.activities;


import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import lsin1225.uclouvain.be.bartenders.MyApplication;
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

    private OnClickListener clickListenerBoutonListeCommandes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listeCommandes(v);
        }
    };

    private OnClickListener clickListenerBoutonInv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            inventaire(v);
        }
    };

    private OnClickListener clickListenerBoutonDeco = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            deconnexion(v);
        }
    };

    private OnClickListener clickListenerBoutonCompte = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            monCompte(v);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        Button carteBouton = (Button) findViewById(R.id.carte_bouton);
        Button commandeBouton = (Button) findViewById(R.id.commande_bouton);
        Button listeCommandesBouton = (Button) findViewById(R.id.liste_commandes_bouton);
        Button inventaireBouton = (Button) findViewById(R.id.inventaire_bouton);
        Button deconnexionBouton = (Button) findViewById(R.id.deconnexion_bouton);
        Button monCompteBouton = (Button) findViewById(R.id.compte_bouton);

        carteBouton.setOnClickListener(clickListenerBoutonCar);
        commandeBouton.setOnClickListener(clickListenerBoutonCom);
        listeCommandesBouton.setOnClickListener(clickListenerBoutonListeCommandes);
        inventaireBouton.setOnClickListener(clickListenerBoutonInv);
        deconnexionBouton.setOnClickListener(clickListenerBoutonDeco);
        monCompteBouton.setOnClickListener(clickListenerBoutonCompte);
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
     * Lance l'activité d'affichage de la liste des commandes.
     */
    public void listeCommandes(View v) {
        Intent intent = new Intent(this, ListeCommandesActivity.class);
        startActivity(intent);
    }


    /**
     * Lance l'activité de visionage des additions
     */
    public void inventaire(View v) {
        Intent intent = new Intent(this, InventaireActivity.class);
        startActivity(intent);
    }


    /**
     * Déconnecte l'utilisateur.
     */
    public void deconnexion(View v) {
        // Déconnecte l'utilisateur
        ((MyApplication) getApplication()).deconnexion();

        // Lance l'activité de login
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

        // Ferme l'activité de dispatch
        finish();
    }

    /**
     * Ouvre les paramètres du compte
     */
    public void monCompte(View v) {
        Intent intent = new Intent(this, MonCompteActivity.class);
        startActivity(intent);
    }
}