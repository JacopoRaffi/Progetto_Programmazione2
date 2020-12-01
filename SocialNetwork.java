package Social;
import java.util.*;

public interface SocialNetwork {
    /* OVERVIEW: SocialNetWork è un TDA mutabile costituito da:
    * un insieme di utenti registrati alla rete
    * una lista di post pubblicati dagli utenti della rete
    * un microblog dove ad ogni utente U è associato l'insieme degli utenti seguiti da U
    * una Map dove ad ogni post della rete è associato il numero di like ricevuti
    *  */

    /*
     * @REQUIRES: newUser != null
     * @MODIFIES: this.socialUsers
     * @THROWS: IllegalArgumentException
     * @EFFECTS: viene registrato l'utente passato come parametro
     * @RETURNS: true se l'utente è stato aggiunto
     */
     boolean addUser(MyUser newUser);

    /*
     * @REQUIRES: user is in socialUsers, text != null && text != empty && 1 <= text.lenght <= 140
     * @MODIFIES: this.socialPosts, this.socialUsers
     * @THROWS: IllegalArgumentException
     * @EFFECTS: viene aggiunto il post scritto dall'utente
     * @RETURNS: true se il post è stato aggiunto
     */
     boolean addPost(MyUser user, String text);

    /*
     * @REQUIRES: followed != null && follower != null && followed is in socialUsers && follower is in socialUser
     * @MODIFIES: this.microblog
     * @THROWS: IllegalArgumentException
     * @EFFECTS: viene aggiunto l'utente followed all'insieme degli utenti seguiti da follower
     * @RETURNS: true se l'utente è stato aggiunto
     */
     boolean follow(MyUser followed, MyUser follower);

    /*
     * @REQUIRES: unfollowed != null && unfollower != null && unfollowed is in socialUsers && unfollower is in socialUser
     * @MODIFIES: this.microblog
     * @THROWS: IllegalArgumentException
     * @EFFECTS: viene rimosso l'utente unfollowed dall'insieme degli utenti seguiti da unfollower
     * @RETURNS: true se l'utente è stato aggiunto
     */
     boolean unfollow(MyUser unfollowed, MyUser unfollower);

    /*
     * @REQUIRES: List of posts != null
     * @MODIFIES: none
     * @THROWS: NullPointerException
     * @EFFECTS: viene restituita una rete sociale(microblog) derivata dalla lista di post passata come parametro
     * @RETURNS: Map<String, Set<String>>
     */
     Map<String, Set<String>> guessFollowers(List<MyPost> posts);

    /*
     * @REQUIRES: Map<String, Set<String>> != null
     * @MODIFIES: none
     * @THROWS: NullPointerException
     * @EFFECTS: viene restituita una lista contenente i nomi delle persone più influenti, data la rete sociale passata come parametro,  la cui dimensione massima è 10
     * @RETURNS: List<String>
     */
     List<String> influencers(Map<String, Set<String>> followers);

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: viene restituita una lista contenente i nomi delle persone più influenti del socialnetwork la cui dimensione massima è 10
     * @RETURNS: List<String>
     */
     List<String> influencers();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: viene resituito l'insieme dei nomi degli utenti che hanno pubblicato almeno un post
     * @RETURNS: Set<String>
     */
     Set<String> getAuthorsUser();

    /*
     * @REQUIRES: List of posts != null
     * @MODIFIES: none
     * @THROWS: NullPointerException
     * @EFFECTS: viene restituita una rete sociale(microblog) derivata dalla lista di post passata come parametro
     * @RETURNS: Map<String, Set<String>>
     */
     Set<String> getAuthorsUser(List<MyPost> ps);

    /*
     * @REQUIRES: List of posts != null
     * @MODIFIES: none
     * @THROWS: NullPointerException
     * @EFFECTS: viene restituito l'insieme dei nomi degli utenti menzionati nei post della lista("@" + nomeUtente)
     * @RETURNS: Set<String>
     */
     Set<String> getMentionedUser(List<MyPost> ps);

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: NullPointerException
     * @EFFECTS: viene restituito l'insieme dei nomi degli utenti menzionati nei post del social("@" + nomeUtente)
     * @RETURNS: Set<String>
     */
     Set<String> getMentionedUser();

    /*
     * @REQUIRES: List of posts != null && String != null && String != empty
     * @MODIFIES: none
     * @THROWS: NullPointerException, IllegalArgumentException
     * @EFFECTS: viene restituito la lista dei post scritti dall'utente passato come parametro derivata dalla lista passata anch'essa come parametro
     * @RETURNS: List<MyPost>
     */
     List<MyPost> writtenBy(List<MyPost> ps, String username);

    /*
     * @REQUIRES: String != null && String != empty && Username is in socialUsers
     * @MODIFIES: none
     * @THROWS: NullPointerException, IllegalArgumentException
     * @EFFECTS: viene restituito la lista dei post nel social scritti dall'utente passato come parametro
     * @RETURNS: List<MyPost>
     */
     List<MyPost> writtenBy(String username);

    /*
     * @REQUIRES: words != null && words != empty
     * @MODIFIES: none
     * @THROWS: NullPointerException, IllegalArgumentException
     * @EFFECTS: viene restituito la lista dei post del social che contengono almeno una tra le parole contenute nella lista passata come parametro
     * @RETURNS: List<MyPost>
     */
     List<MyPost> containing(List<String> words);

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia dell'insieme degli utenti del social
     * @RETURNS: this.socialUsers.clone
     */
     Set<MyUser> getSocialUsers();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia del microBlog
     * @RETURNS: this.microBlog.clone
     */
     Map<String, Set<String>> getMicroBlog();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia della mappa che associa ad ogni post il numero di like ricevuti
     * @RETURNS: this.likes.clone
     */
     Map<MyPost, Integer> getLikesPosts();

    /*
     * @REQUIRES: true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce una copia della lista di post del social
     * @RETURNS: this.socialPosts.clone
     */
     List<MyPost> getSocialPosts();

    /*
     * @REQUIRES: us != null && ps != null &6 us is in socialUsers && ps is in socialPosts
     * @MODIFIES: this.likesPosts, this.socialUsers
     * @THROWS: IllegalArgumentException
     * @EFFECTS: incrementa di 1 il numero di like associato al post passato come parametro e lo aggiunge alla lista di post piaciuti dell'utente
     * @RETURNS: true se viene messo like
     */
     boolean like(MyUser us, MyPost ps);

    /*
     * @REQUIRES: us != null && ps != null &6 us is in socialUsers && ps is in socialPosts
     * @MODIFIES: this.likesPosts, this.socialUsers
     * @THROWS: IllegalArgumentException
     * @EFFECTS: decrementa di 1 il numero di like associato al post passato come parametro e lo rimuove dalla lista di post piaciuti dell'utente
     * @RETURNS: true se viene rimosso il like
     */
     boolean removeLike(MyUser us, MyPost ps);
}
