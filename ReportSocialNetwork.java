package Social;
import java.util.*;

public interface ReportSocialNetwork extends SocialNetwork{
    /* @OVERVIEW: ReportSocialNetWork è un'estensione gerarchica del TDA MySocialNetWork. In aggiunta alla superclasse permette di segnalare un post e associa ad ogni post
    il numero di segnalazioni ricevute. */

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia della mappa che associa ai post le relative segnalazioni
     * @RETURNS: Map<MyPot, Integer>
     * */
     Map<MyPost, Integer> getPostReports();

    /*
     * @REQUIRES: us != null && ps != null && us is in social && ps is in social && ps isn't already reported by us
     * @MODIFIES: postReports
     * @THROWS:  none
     * @EFFECTS: incrementa di 1 il numero di segnalazioni il post se è già stato segnalato, altrimenti lo inserisce nella map con valore associato 1
     * @RETURNS: true se viene segnalato il post
     * */
     boolean report(MyUser us, MyPost ps);

    /*
     * @REQUIRES: ps != null && ps is in social
     * @MODIFIES: none
     * @THROWS: IllegalArgumentEception NullPointerException
     * @EFFECTS: restituisce il numero di segnalazioni associate al post
     * @RETURNS: Integer che rappresenta le segnalazioni del post
     * */
     int checkPostBan(MyPost ps);
}
