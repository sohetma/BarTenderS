package lsin1225.uclouvain.be.bartenders.model;

import android.os.Parcel;
import android.os.Parcelable;

import lsin1225.uclouvain.be.bartenders.dao.CategorieDao;
import lsin1225.uclouvain.be.bartenders.dao.Dao;

/**
 * représente une catégorie de boisson.
 * Created by alex on 4/13/15.
 */
public class Categorie extends Row {

    private static final String DEFAULT_ICON = "soda";

    private String nom;
    private String icone;

    /**
     * instancie la catégorie
     * @param nom un String non-vide avec le nom de la catégorie
     * @param icone un String non-vide avec l'adresse de l'icône.
     */
    public Categorie(String nom, String icone) {
        this.nom = nom;
        this.icone = icone;
    }

    /**
     * instancie la catégorie sans icône. L'icône par défaut sera utilisée.
     * @param nom un String non-vide avec le nom de la catégorie
     */
     public Categorie(String nom) {
        this(nom, DEFAULT_ICON);
     }

    public String nom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String icone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    @Override
    protected Dao defaultDao() {
        return CategorieDao.instance();
    }
}
