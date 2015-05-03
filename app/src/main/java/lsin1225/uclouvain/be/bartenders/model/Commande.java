package lsin1225.uclouvain.be.bartenders.model;
import java.util.List;

import lsin1225.uclouvain.be.bartenders.dao.CommandeDao;
import lsin1225.uclouvain.be.bartenders.dao.Dao;
import lsin1225.uclouvain.be.bartenders.dao.TableDao;

/**
 * TODO changer l'arraylist de Boisson en arraylist de EntréeCommande,
 * comme ça on peut avoir un objet contenant {coca, 3} pour signifier 3 cocas,
 * au lieu d'avoir 3 fois l'objet coca dans la liste...
 * Created by alex on 4/15/15.
 */
public class Commande extends Row {
    private int numero = 0;
    private boolean estPayee = false;

    /**
     * Classe utilisée pour stocker une boisson et sa quantité dans une paire.
     */
    public static class BoissonCommande {
        private Boisson boisson;
        private int quantite;

        public BoissonCommande(Boisson boisson, int quantite) {
            this.boisson = boisson;
            this.quantite = quantite;
        }

        public Boisson boisson() {
            return boisson;
        }

        public void setBoisson(Boisson boisson) {
            this.boisson = boisson;
        }

        public int quantite() {
            return quantite;
        }

        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }
    }


    /**
     * Ajoute n fois
     * @param boisson la boisson à ajouter
     * @param quantite un entier >= 0 disant combien d'instances à ajouter
     */
    public void ajouterBoisson(Boisson boisson, int quantite) {
        if (quantite < 0)
            throw new IllegalArgumentException("Impossible d'ajouter un nombre négatif de boissons.");
        CommandeDao.instance().addBoisson(this.numero, boisson.nom(), quantite);
    }

    /**
     * supprime une Boisson de la commande. Si la boisson à été commandée plusieurs fois, ne retire qu'une fois la boisson.
     * Si la Boisson n'a pas été commandée, ne fait rien.
     * @param boisson la boisson à retirer.
     */
    public void supprimerBoisson(Boisson boisson) {
        CommandeDao.instance().removeBoisson(this.numero, boisson.nom());
    }

    public List<BoissonCommande> listeBoissons() {
        return CommandeDao.instance().listBoissonCommande(this.numero);
    }

    /**
     * Calcule le total à payer.
     * @return la somme totale à payer.
     */
    public float addition(){
        return CommandeDao.instance().addition(this.numero);
    }

    public Table table() {
        return TableDao.instance().tableCommande(this.numero);
    }


    public int numero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean estPayee() {
        return estPayee;
    }

    public void setEstPayee(boolean estPayee) {
        this.estPayee = estPayee;
    }

    @Override
    protected Dao defaultDao() {
        return CommandeDao.instance();
    }
}
