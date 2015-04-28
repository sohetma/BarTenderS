package lsin1225.uclouvain.be.bartenders;

import java.util.ArrayList;

/**
 * Représente un inventaire
 * Created by bruno on 28-04-15.
 */
public class Inventaire {
    private ArrayList<Boisson> boissons;

    /**
     * instancie un nouvel inventaire.
     * @param boissons une liste de boissons
     */
    public Inventaire(ArrayList<Boisson> boissons){
        this.boissons=boissons;
    }

    public void ajouterBoisson(Boisson boisson){
        boissons.add(boisson);
    }

    public void supprimerBoisson(Boisson boisson){
        boissons.remove(boisson);
    }

    public ArrayList<Boisson> getBoissons(){
        return boissons;
    }

    public ArrayList<Boisson> getBoissonsTriees(****){ //utilisation d'un regex?
        return ****;
    }
}
