package lsin1225.uclouvain.be.bartenders;
import java.util.ArrayList;

/**
 * représente une table du bar.
 * Created by alex on 4/15/15.
 */
public class Table {
    private int numero;
    private float x;
    private float y;
    private int etage;
    private ArrayList<Commande> commandes;
    private ArrayList<Utilisateur> utilisateurs;

    /**
     * Instancie une Table du bar.
     * @param numero le numéro idantifiant la table.
     * @param x la position en x de la table
     * @param y la position en y de la table
     * @param etage l'étage de la table
     * @param commandes la liste de commandes
     * @param utilisateurs la liste d'utilisateurs assis à la table.
     */
    public Table(int numero, float x, float y, int etage, ArrayList<Commande> commandes, ArrayList<Utilisateur> utilisateurs) {
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.etage = etage;
        this.commandes = commandes;
        this.utilisateurs = utilisateurs;
    }

    /**
     * Instancie une nouvelle table.
     * Par défaut, commandes et utilisateurs sont des listes vides.
     * @param numero le numéro idantifiant la table.
     * @param x la position en x de la table
     * @param y la position en y de la table
     * @param etage l'étage de la table
     */
    public Table(int numero, float x, float y, int etage) {
        this(numero, x, y, etage,  new ArrayList<Commande>(), new ArrayList<Utilisateur>());
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
        float total = 0;

        for (Commande c : commandes) {
            if(! c.estPayee()){
                total += c.addition();
            }
        }

        return total;
    }

    /**
     *
     * @return une ArrayList de toutes les boissons commandées à la table.
     */
    public ArrayList<Boisson> listeBoissons() {
        ArrayList<Boisson> liste = new ArrayList<Boisson>();

        for (Commande c : commandes)
            liste.addAll(c.boissons());

        return liste;
    }

    public int numero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Utilisateur> utilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public ArrayList<Commande> commandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
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
}
