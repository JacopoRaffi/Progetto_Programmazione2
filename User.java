package Social;
import java.util.*;

public interface User {
    /* @OVERVIEW: User è un tipo di dato astratto mutabile, è caratterizzato da:
    * un nickname
    * una collezione di post pubblicati dall'utente e dalla relativa cardinalità
    * una collezione di utenti seguiti
    * una collezione di utenti che seguono(followers) e dalla relativa cardinalità
    * una collezione di post a cui l'utente ha messo like
    * */

    /*
    * @REQUIRES:
    * @MODIFIES:
    * @THROWS:
    * @EFFECTS:
    * @RETURNS:
    * */
     String getNickname();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce il numerro di followers
     * @RETURNS: this.numFollowers
     * */
     int getNumFollowers();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce il numerro di post pubblicati
     * @RETURNS: this.countPosts
     * */
     int getNumPosts();

    /*
     * @REQUIRES: parameter != null
     * @MODIFIES: none
     * @THROWS: NullPointerException
     * @EFFECTS: restituisce un valore che indica il confronti tra due istanza di due Utenti
     * @RETURNS: Integer, nickname.compareTo(parameter.nickanme)
     * */
     int compareTo(MyUser us);

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia dei seguiti dall'utente
     * @RETURNS: this.followed
     * */
     Set<String> getFollowed();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia dei post a cui l'utente ha messo like
     * @RETURNS: this.likedPosts
     * */
     Set<MyPost> getLikedPosts();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia dei followers dell'utente
     * @RETURNS: this.followers
     * */
     Set<String> getFollowers();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce i post pubblicati dall'utente
     * @RETURNS: this.userPosts
     * */
     Set<MyPost> getPosts();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce i post segnlati dall'utente
     * @RETURNS: this.reportedPosts
     * */
     Set<MyPost> getReportedPosts();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce il nickname dell'utente
     * @RETURNS: this.nickname
     * */
     String toString();

    /*
     * @REQUIRES: parameter != null && parameter isInstanceof MyUser == true
     * @MODIFIES: none
     * @THROWS: IllegalArgumentException
     * @EFFECTS: restituisce true se sono lo stesso Utente, false altrimenti
     * @RETURNS: this.nickname.equals(parameter.nickname)
     * */
     boolean equals(Object o);

}
