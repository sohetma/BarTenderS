package lsin1225.uclouvain.be.bartenders.model;

import lsin1225.uclouvain.be.bartenders.dao.Dao;
import lsin1225.uclouvain.be.bartenders.dao.UtilisateurDao;

/**
 * représente un utilisateur de l'application
 * Created by alex on 4/6/15.
 */
public class Utilisateur extends Row {

    public static enum Role {
        CLIENT, SERVEUR, GESTIONNAIRE
    };


    private String login;
    private String motDePasse;
    private String nom;
    private Role role;

    /**
     * Instancie un nouvel Utilisateur
     * @param login Le login de l'utilisateur. Il sera utilisé pour se connecter
     *              Le login doit être String non vide et qui n'est pas encore utilisé.
     * @param motDePasse Le mot de passe de l'utilisateur. Il sera utilisé pour se connecter.
     *                 Le mot de passe doit être un String non vide.
     * @param nom Le nom de l'utilisateur. Il sert  juste à afficher le vrai nom de l'utilisateur
     *            au lieu de son login.
     *            Le nom doit être un String non vide.
     * @param role Le role de l'utilisateur.
     */
    public Utilisateur(String login, String motDePasse, String nom, Role role) {
        if (!login.isEmpty() /*TODO vérifier si le login est déjà utilisé*/
            && !motDePasse.isEmpty()
            && !nom.isEmpty()) {

            this.login = login;
            this.motDePasse = motDePasse;
            this.nom = nom;
            this.role = role;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String login() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String motDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String nom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Role role() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    protected Dao defaultDao() {
        return UtilisateurDao.instance();
    }
}
