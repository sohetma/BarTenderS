package lsin1225.uclouvain.be.bartenders.model;

import java.util.List;

import lsin1225.uclouvain.be.bartenders.dao.BoissonDao;

/**
 * Représente un inventaire
 * Created by bruno on 28-04-15.
 */
public class Inventaire {

    public void ajouterBoisson(Boisson boisson) {
        BoissonDao.instance().insert(boisson);
    }

    public void supprimerBoisson(Boisson boisson) {
        BoissonDao.instance().remove(boisson);
    }

    public List<Boisson> boissons() {
        return BoissonDao.instance().findAll();
    }

    //TODO
    /*
    public ArrayList<Boisson> filtreBoissons(****) { //utilisation d'un regex?
        return ****;
    }
    */
}
