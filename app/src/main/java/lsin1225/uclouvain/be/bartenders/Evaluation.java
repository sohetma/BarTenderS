package lsin1225.uclouvain.be.bartenders;

/**
 * représente une évaluation de boisson
 * Created by alex on 4/6/15.
 */
public class Evaluation {
    private static final int NOTE_MAX = 10;

    private int note;
    private Utilisateur utilisateur;

    /**
     * Instancie l'évaluation.
     * @param note un int entre 0 et NOTE_MAX.
     * @param utilisateur l'utilisateur qui a donné l'évaluation
     */
    public Evaluation(int note, Utilisateur utilisateur){
        if (note >= 0 && note <= NOTE_MAX) {
            this.note = note;
        } else {
            this.note = 0;
        }

        this.utilisateur = utilisateur;
    }

    public int note() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Utilisateur utilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
