package lsin1225.uclouvain.be.bartenders.trash;

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

    public ArrayList<Boisson> getBoissonsTriees(String str){ //utilisation d'un regex?
        ArrayList<Boisson> toreturn=new ArrayList<Boisson>();
        for(int i=1; i <= boissons.size(); i++){
            String nomboisson=(boissons.get(i)).nom();
            String nomcategorie=((boissons.get(i)).categorie()).nom();
            String nomdescription=(boissons.get(i)).description();
            if(nomboisson.contains(str) || nomcategorie.contains(str) ||nomdescription.contains(str)){
                toreturn.add(boissons.get(i));
            }
        }
        return toreturn;
    }
}
