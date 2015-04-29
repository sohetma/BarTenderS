package lsin1225.uclouvain.be.bartenders.model;

import java.util.List;

import lsin1225.uclouvain.be.bartenders.dao.TableDao;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;

/**
 * Classe générale qui contient une instance de tous les autres objets nécéssaires à un Bar.
 * Created by alex on 4/6/15.
 */
public class Bar {
    private Inventaire inventaire;

    public void ajouterTable(Table table) {
        TableDao.instance().insert(table);
    }

    public void supprimerTable(Table table) {
        TableDao.instance().remove(table);
    }

    public List<Table> tables() {
        return TableDao.instance().findAll();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        UtilisateurDao.instance().insert(utilisateur);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
        UtilisateurDao.instance().remove(utilisateur);
    }

    public List<Utilisateur> utilisateurs() {
        return UtilisateurDao.instance().findAll();
    }

    public Inventaire inventaire() {
        return inventaire;
    }
}
