package lsin1225.uclouvain.be.bartenders.trash;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object pour la table Evaluation
 * Created by alex on 4/26/15.
 */
@DatabaseTable(tableName = "Evaluation")
public class EvaluationTO {

    @DatabaseField(canBeNull = false, uniqueCombo = true)
    private String nom_boisson;
    @DatabaseField(canBeNull = false, uniqueCombo = true)
    private String login;
    @DatabaseField(canBeNull = false)
    private int score;
}
