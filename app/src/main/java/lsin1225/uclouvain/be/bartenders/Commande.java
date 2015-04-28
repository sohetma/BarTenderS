package lsin1225.uclouvain.be.bartenders;
import java.util.ArrayList;

/**
 * TODO changer l'arraylist de Boisson en arraylist de EntréeCommande,
 * comme ça on peut avoir un objet contenant {coca, 3} pour signifier 3 cocas,
 * au lieu d'avoir 3 fois l'objet coca dans la liste...
 * Created by alex on 4/15/15.
 */
public class Commande {
    private ArrayList<Boisson> boissons;
    private boolean estPayee;

    /**
     * Instancie une Commande avec l'ArrayList de Boissons fournée, et
     * @param boissons une ArrayList de Boissons dans la commande.
     * @param estPayee un booléen déterminant si la commande à été payée (true) ou pas (false)
     */
    public Commande(ArrayList<Boisson> boissons, boolean estPayee) {
        this.boissons = boissons;
        this.estPayee = estPayee;
    }

    /**
     * Instancie une Commande, non-payée
     *
     * @param boissons une ArrayList de Boissons dans la commande.
     */
    public Commande(ArrayList<Boisson> boissons) {
        this(boissons, false);
    }

    /**
     * Instancie une commande vide, non-payée
     */
    public Commande() {
        this(new ArrayList<Boisson>(), false);
    }

    /**
     * Ajoute n fois
     * @param boisson la boisson à ajouter
     * @param quantite un entier >= 0 disant combien d'instances à ajouter
     */
    public void ajouterBoisson(Boisson boisson, int quantite) {
        if (quantite < 0)
            throw new IllegalArgumentException("Impossible d'ajouter un nombre négatif de boissons.");
        for (int i = 0; i < quantite; i++) {
            boissons.add(boisson);
        }
    }

    /**
     * supprime une Boisson de la commande. Si la boisson à été commandée plusieurs fois, ne retire qu'une fois la boisson.
     * Si la Boisson n'a pas été commandée, ne fait rien.
     * @param boisson la boisson à retirer.
     */
    public void supprimerBoisson(Boisson boisson) {
        boissons.remove(boisson);
    }

    /**
     * Calcule le total à payer.
     * @return la somme totale à payer.
     */
    public float addition(){
        float total = 0;
        for (Boisson b : boissons) {
            total += b.prix();
        }

        return total;
    }

    public ArrayList<Boisson> boissons() {
        return boissons;
    }

    public void setBoissons(ArrayList<Boisson> boissons) {
        this.boissons = boissons;
    }

    public boolean estPayee() {
        return estPayee;
    }

    public void setEstPayee(boolean estPayee) {
        this.estPayee = estPayee;
    }
}
