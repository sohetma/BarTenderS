package lsin1225.uclouvain.be.bartenders.model;
import java.util.List;

import lsin1225.uclouvain.be.bartenders.dao.BoissonDao;
import lsin1225.uclouvain.be.bartenders.dao.CommandeDao;
import lsin1225.uclouvain.be.bartenders.dao.Dao;
import lsin1225.uclouvain.be.bartenders.dao.TableDao;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;

/**
 * représente une table du bar.
 * Created by alex on 4/15/15.
 */
public class Table extends Row {
    private int numero;
    private float x;
    private float y;
    private int etage;

    /**
     * Instancie une Table du bar.
     * @param numero le numéro idantifiant la table.
     * @param x la position en x de la table
     * @param y la position en y de la table
     * @param etage l'étage de la table
     */
    public Table(int numero, float x, float y, int etage) {
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.etage = etage;
    }

    /**
     * Instancie une nouvelle table.
     * Par défaut l'étage est 0, commandes et utilisateurs sont des listes vides.
     * @param numero le numéro idantifiant la table.
     * @param x la position en x de la table
     * @param y la position en y de la table
     */
    public Table(int numero, float x, float y) {
        this(numero, x, y, 0);
    }

    /**
     * Calcule l'addition de toutes les commandes non-payées de la table
     * @return la somme totale à payer pour la table
     */
    public float addition(){
        return TableDao.instance().addition(this.numero);
    }

    /**
     *
     * @return une ArrayList de toutes les boissons commandées à la table.
     */
    public List<Boisson> listeBoissons() {
        return BoissonDao.instance().listBoissonsTable(this.numero);
    }

    public List<Utilisateur> listeClients() {
        return UtilisateurDao.instance().listClientsTable(this.numero);
    }

    public List<Commande> listeCommandes() {
        return CommandeDao.instance().listCommandesTable(this.numero);
    }

    public int numero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int etage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public float y() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float x() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Override
    protected Dao defaultDao() {
        return TableDao.instance();
    }
}
