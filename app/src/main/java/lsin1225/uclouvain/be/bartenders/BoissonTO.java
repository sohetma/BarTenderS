package lsin1225.uclouvain.be.bartenders;

@DatabaseTable(tableName = "boisson")
public class BoissonTO {
    @DatabaseField(id = true, canBeNull = false)
    private String nom_boisson;
    @DatabaseField(canBeNull = false)
    private String nom_categorie;
    @DatabaseField(canBeNull = false)
    private String descrpition;
    @DatabaseField(canBeNull = false)
    private float prix;
    @DatabaseField(canBeNull = false)
    private int stock;
    @DatabaseField(canBeNull = false)
    private int stock_max;
    @DatabaseField(canBeNull = false)
    private int stock_seuil;

    BoissonTO() {
        // pour ORMLite
    }
}