package lsin1225.uclouvain.be.bartenders;

import java.util.ArrayList;

/**
 * Représente une boisson
 * Created by alex on 4/13/15.
 */
public class Boisson {
    private String nom;
    private Categorie categorie;
    private String description;
    private float prix;
    private int stock;
    private int stockMax;
    private int seuil;
    private ArrayList<Evaluation> evaluations;

    /**
     * instancie une nouvelle boisson.
     * @param nom un String non-vide
     * @param categorie
     * @param description
     * @param prix un float > 0;
     * @param stock un int > 0;
     * @param stockMax
     * @param seuil
     */
    public Boisson(String nom, Categorie categorie, String description, float prix, int stock, int stockMax, int seuil) {
        if (nom.isEmpty() || prix <= 0 || stock <= 0) {
            throw new IllegalArgumentException();
        }
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.stockMax = stockMax;
        this.seuil = seuil;
    }

    /**
     * Renvoie la moyenne de toutes les évaluations sur cette boisson.
     */
    public int evaluationMoyenne() {
        int somme = 0;
        for (Evaluation eva : evaluations){
            somme += eva.note();
        }

        if (somme == 0){
            return 0;
        } else {
            return somme / evaluations.size();
        }
    }

    @Override
    /**
     * deux boissons sont les mêmes si elles ont le même nom.
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o instanceof Boisson) {
            Boisson autre = (Boisson) o;

            return autre.nom().equals(this.nom);
        } else {
            return false;
        }
    }

    public void ajouterEvaluation(Evaluation evaluation){
        evaluations.add(evaluation);
    }

    public void supprimerEvaluation(Evaluation evaluation){
        evaluations.remove(evaluation);
    }

    public String nom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie categorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float prix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int stock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int stockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public int seuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }
}
