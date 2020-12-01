package Social;
import java.util.*;

public class MyUser implements User, Comparable<MyUser>{
    /* REPINV:
    * nickname != null && != empty
    * Set<MyPost> UserPosts != null && UserPosts.size == countPosts
    * Set<MyPost> likedPosts != null
    * Set<MyPost> reportedPosts != null
    * Set<String> followed != null
    * Set<String> followers != null && followers.size == numFollowers
    * countPosts >= 0, numFollowers >= 0
    */

    /* @ABSFUNCT Funzione α t.c.:
    * α(String nickname) -> nome univoco dell'utente
    * α(TreeSet<MyPost> UserPosts) -> post dell'utente
    * α(TreeSet<MyPost> likedPosts) -> post a cui l'utente ha messo like(non i propri)
    * α(TreeSet<MyPost> reportedPosts) -> post segnalati dall'utente
    * α(TreeSet<String> followed) -> utenti seguiti dall'utente
    * α(TreeSet<String> followers) -> utenti che seguono l'utente
    * α(Integer countPosts ) -> numero di Post pubblicati
    * α(Integer numFollowers) -> numero di followers dell'utente
    */
    private final String nickname;
    private final TreeSet<MyPost> UserPosts;
    private int countPosts;
    private int numFollowers;
    private final TreeSet<String> followed;
    private final TreeSet<String> followers;
    private final TreeSet<MyPost> likedPosts;
    private final TreeSet<MyPost> reportedPosts;

    /*
    * @REQUIRES: nickname != null && != empty
    * @MODIFIES: nickname, userPosts, countPosts, numFollowers, followed, followers, likedPosts, reportedPosts
    * @THROW: NullPointerException, IllegalArgumentException
    * @EFFECTS: Crea una nuova istanza di MyUser
    * */
    public MyUser(String nickname) throws NullPointerException, IllegalArgumentException{

        if(nickname == null)
            throw new NullPointerException("nickname is not initialized");

        if(nickname.isEmpty())
            throw new IllegalArgumentException("nickname can not be empty");

        this.nickname = nickname;
        UserPosts = new TreeSet<>();
        countPosts = 0;
        numFollowers = 0;
        followed = new TreeSet<>();
        followers = new TreeSet<>();
        likedPosts = new TreeSet<>();
        reportedPosts = new TreeSet<>();


    }

    public String getNickname(){
        return nickname;
    }

    boolean addPost(String text) throws NullPointerException, IllegalArgumentException{
        MyPost ps = new MyPost(text, nickname);
        if(UserPosts.add(ps)) {
            countPosts++;

            return true;
        }

        return false;
    }

    public int getNumPosts(){
        return countPosts;
    }

    public int getNumFollowers(){
        return this.numFollowers;
    }

    boolean addFollowed(MyUser user) throws NullPointerException{

        if(user == null)
            throw new NullPointerException("user is not initialized");

        if(!this.nickname.equals(user.getNickname())) {
            followed.add(user.getNickname());
            user.addFollower(this.nickname);

            return true;
        }

        return false;
    }

    boolean removeFollowed(MyUser user) throws NullPointerException{
        if(user == null)
            throw new NullPointerException("user is not initialized");

        if(!nickname.equals(user.getNickname())) {
            followed.remove(user.getNickname());
            user.removeFollower(this.nickname);

            return true;
        }

        return false;
    }

    private void addFollower(String follower) throws IllegalArgumentException{

        if(follower.equals(this.nickname))
            throw new IllegalArgumentException("User can't be followed by itself");

        if(followers.add(follower))
            numFollowers++;

    }

    private void removeFollower(String follower) throws IllegalArgumentException{
        if(follower.equals(this.nickname))
            throw new IllegalArgumentException("User can't be removed by itself");

        if(followers.remove(follower))
            numFollowers--;
    }

    public int compareTo(MyUser us){
        return nickname.compareTo(us.getNickname());
    }

    public Set<String> getFollowed(){
        return (Set<String>) this.followed.clone();
    }

    public Set<MyPost> getLikedPosts(){
        return (Set<MyPost>)this.likedPosts.clone();
    }

    public Set<String> getFollowers(){
        return (Set<String>) this.followers.clone();
    }

    public Set<MyPost> getPosts(){
        return (Set<MyPost>) this.UserPosts.clone();
    }

    boolean addLikedPost(MyPost ps) throws NullPointerException{
        return this.likedPosts.add(ps);
    }

    boolean removeLikedPost(MyPost ps)throws NullPointerException{
        return this.likedPosts.remove(ps);
    }

    boolean report(MyPost ps)throws NullPointerException{
        return this.reportedPosts.add(ps);
    }

    public Set<MyPost> getReportedPosts(){
        return (Set<MyPost>)this.reportedPosts.clone();
    }

    @Override
    public String toString(){
        return this.nickname;
    }

    @Override
    public boolean equals(Object o) throws IllegalArgumentException{
        if(! (o instanceof MyUser))
            throw new IllegalArgumentException("parameter must be an instance of MyUser");
        return this.nickname.equals(((MyUser) o).getNickname() );
    }

}
