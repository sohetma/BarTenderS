package lsin1225.uclouvain.be.bartenders.model;

import lsin1225.uclouvain.be.bartenders.dao.BoissonDao;
import lsin1225.uclouvain.be.bartenders.dao.Dao;

/**
 * Représente une boisson
 * Created by alex on 4/13/15.
 */
public class Boisson extends Row {
    private String nom;
    private Categorie categorie;
    private String description;
    private float prix;
    private int stock;
    private int stockMax;
    private int stockSeuil;

    public class Evaluation {
        private static final int NOTE_MAX = 5;

        private int score;
        private Utilisateur utilisateur;

        /**
         * Instancie l'évaluation.
         * @param score un int entre 0 et NOTE_MAX.
         * @param utilisateur l'utilisateur qui a donné l'évaluation
         */
        public Evaluation(int score, Utilisateur utilisateur){
            setScore(score);
            this.utilisateur = utilisateur;
        }

        public int score() {
            return score;
        }

        public void setScore(int score) {
            if (score < 0)
                this.score = 0;
            else if (score > NOTE_MAX)
                this.score = NOTE_MAX;
            else
                this.score = score;
        }

        public Utilisateur utilisateur() {
            return utilisateur;
        }

        public void setUtilisateur(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
        }
    }

    /**
     * instancie une nouvelle boisson.
     * @param nom un String non-vide
     * @param categorie
     * @param description
     * @param prix un float > 0;
     * @param stock un int > 0;
     * @param stockMax
     * @param stockSeuil
     */
    public Boisson(String nom, Categorie categorie, String description, float prix, int stock, int stockMax, int stockSeuil) {
        if (nom.isEmpty() || prix <= 0 || stock <= 0) {
            throw new IllegalArgumentException();
        }
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.stockMax = stockMax;
        this.stockSeuil = stockSeuil;
    }

    /**
     * Renvoie la moyenne de toutes les évaluations sur cette boisson.
     */
    public int evaluationMoyenne() {
        return BoissonDao.instance().evaluationMoyenne(this.nom);
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

    public void ajouterEvaluation(Evaluation evaluation) {
        BoissonDao.instance().addEvaluation(this.nom, evaluation.utilisateur().login(), evaluation.score());
    }

    public void supprimerEvaluation(Evaluation evaluation){
        BoissonDao.instance().removeEvaluation(this.nom, evaluation.utilisateur().login());
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

    public int stockSeuil() {
        return stockSeuil;
    }

    public void setStockSeuil(int stockSeuil) {
        this.stockSeuil = stockSeuil;
    }

    @Override
    public String toString() {
        return nom();
    }

    @Override
    protected Dao defaultDao() {
        return BoissonDao.instance();
    }
}
