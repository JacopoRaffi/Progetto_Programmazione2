package Social;
import java.util.*;

public class MySocialNetWork implements SocialNetwork{
    /* REPINV:
     * microblog != null && socialPosts != null && socialUsers != null && likesPosts != null
     * forAll Users U in socialUser ==> (U.nickname is a key of microBlog && User.nickname is unique)
     * forAll Posts P in socialPosts ==> P is a key of likesPosts
     * forAll key K in microBlog ==> microBlog.get(K) != null
     * forAll key K in likesPosts ==> likesPosts.get(K) >= 0
     * forAll posts P in socialPosts ==> Exists and is unique X t.c. X is in socialUsers && X.nickname == P.Author
     * socialPosts == U(i=0, socialUsers.size()) User[i].UserPosts
     */

    /* @ABSFUNCT Funzione α t.c.:
     * α(HashMap<String, Set<String>>) -> microBlog del social dove a ogni chaive è associateìo l'insieme degli utenti seguiti
     * α(Listt<MyPost> socialPosts) -> post della rete sociale
     * α(TreeSet<MyUser> socialUsers) -> utenti registrati nel SocialNetWork
     * α(Map<MyPost, Integer> reportedPosts) -> una mappa che associa ad ogni post il numero di like
     */

    private final HashMap<String, TreeSet<String>> microBlog;
    protected final LinkedList<MyPost> socialPosts;
    protected final TreeSet<MyUser> socialUsers;
    private final HashMap<MyPost, Integer> likesPosts;

    /*
     * @REQUIRES : none
     * @MODIFIES: microBlog, socialPosts, socialUsers, likesPosts
     * @THROWS: none
     * @EFFECTS: creates a new SocialNetWork
     */
    public MySocialNetWork(){
        microBlog = new HashMap<>();
        socialUsers = new TreeSet<>();
        socialPosts = new LinkedList<>();
        likesPosts = new HashMap<>();
    }

    public boolean addUser(MyUser newUser) throws IllegalArgumentException{
        if(checkUser(newUser.getNickname()))
            throw new IllegalArgumentException("User already exists");

        if(socialUsers.add(newUser)){
            microBlog.put(newUser.getNickname(), new TreeSet<>());
            return true;
        }

        return false;
    }

    public boolean addPost(MyUser user, String text) throws IllegalArgumentException{
        MyPost ps = new MyPost(text, user.getNickname());
        if(!checkUser(user.getNickname()))
            throw new IllegalArgumentException("User non registered in this social network");

        if(socialPosts.add(ps)) {
            user.addPost(text);
            likesPosts.put(ps, 0); //inizialmente il post avrà 0 like
            return true;
        }

        return false;
    }

    public boolean follow(MyUser followed, MyUser follower) throws IllegalArgumentException{
        if(!socialUsers.contains(followed) || !socialUsers.contains(follower))
            throw new IllegalArgumentException("User doesn't exist in this network");

        if(follower.addFollowed(followed)) {
            TreeSet<String> temp = microBlog.get(follower.getNickname());
            temp.add(followed.getNickname()); //nuovo TreeSet associato alla chiave
            microBlog.replace(follower.getNickname(), temp);
            return true;
        }

        return false;
    }

    public boolean unfollow(MyUser unfollowed, MyUser unfollower) throws IllegalArgumentException{
        if(!socialUsers.contains(unfollowed) || !socialUsers.contains(unfollower))
            throw new IllegalArgumentException("User doesn't exist in this network");

        if(unfollower.removeFollowed(unfollowed)){
            TreeSet<String> temp = microBlog.get(unfollower.getNickname());
            temp.remove(unfollowed.getNickname());
            microBlog.replace(unfollower.getNickname(), temp);
            return true;
        }

        return false;
    }

    public Map<String, Set<String>> guessFollowers(List<MyPost> posts) throws NullPointerException{

        if(posts == null)
            throw new NullPointerException("parameter must be != null");
        ListIterator<MyPost> iter = posts.listIterator();
        Iterator<MyUser> iteriter;
        Map<String, Set<String>> socialMap = new HashMap<>();

        while (iter.hasNext()) {

            MyPost tempPost = iter.next();
            iteriter = socialUsers.iterator();

            while(iteriter.hasNext()) {

                MyUser tempUser = iteriter.next();

                if(tempPost.getAuthor().equals(tempUser.getNickname())) {
                    socialMap.put(tempUser.getNickname(), tempUser.getFollowed());
                    break;
                }
            }
        }

        return socialMap;

    }

    public List<String> influencers(Map<String, Set<String>> followers) throws NullPointerException{
        if(followers == null)
            throw new NullPointerException();

        Map<String, Integer> support = new HashMap<>(); //ad ogni nome associo il numero di followers

        for (String key: followers.keySet()) {
            support.put(key, 0); //inizializzo i followers di tutti i nomi a 0
        }

        for (String keyS: support.keySet()) { //keyS chiave di support
            for (String keyF: followers.keySet()) { //keyF chiave di followers
                if(followers.get(keyF).contains(keyS))
                    support.replace(keyS, support.get(keyS) + 1); //incremento di 1 il follower

            }

        }

        List<Map.Entry<String, Integer> > list = new ArrayList<Map.Entry<String, Integer> >(support.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        ArrayList<String> topInfluencers = new ArrayList<>();

        for (Map.Entry<String, Integer> cc: list)  {
            topInfluencers.add(cc.getKey());
        }
            return (List)topInfluencers.subList(0, Math.min(topInfluencers.size(), 9));

    }

    public List<String> influencers(){
        return influencers(this.getMicroBlog());
    }

    public Set<String> getAuthorsUser(){
      return this.getAuthorsUser(socialPosts);
    }

    public Set<String> getAuthorsUser(List<MyPost> ps) throws NullPointerException{
        if(ps == null)
            throw new NullPointerException();

        Set<String> authorsUsers = new TreeSet<>();

        for (Post post: ps) {
            authorsUsers.add(post.getAuthor());
        }

        return authorsUsers;
    }

    public Set<String> getMentionedUser(List<MyPost> ps) throws NullPointerException{
        if(ps == null)
            throw new NullPointerException();
        Set<String> mentionedUsers = new TreeSet<>();

        for (Post post : ps) {
            for (User us: this.socialUsers) {
                 if(post.getText().contains("@" + us.getNickname()))
                     mentionedUsers.add(us.getNickname());
            }
        }
        return mentionedUsers;
    }

    public Set<String> getMentionedUser(){
        return this.getMentionedUser(socialPosts);
    }

    public List<MyPost> writtenBy(List<MyPost> ps, String username) throws NullPointerException, IllegalArgumentException{
        if(ps == null)
            throw new NullPointerException();
        if(username == null)
            throw new NullPointerException();
        if(username.isEmpty())
            throw new IllegalArgumentException("username can't be empty");

        List<MyPost> list = new LinkedList<>();
        if(!checkUser(username))
            throw new IllegalArgumentException("User doesn't exixts");

        for (MyPost post: ps) {
            if(post.getAuthor().equals(username))
                list.add(post);

        }

        return list;
    }

    public List<MyPost> writtenBy(String username){
        return this.writtenBy(this.socialPosts, username);
    }

    public List<MyPost> containing(List<String> words) throws NullPointerException, IllegalArgumentException{
        if(words == null)
            throw new NullPointerException();

        List<MyPost> list = new LinkedList<>();
        if(words.isEmpty())
            throw new IllegalArgumentException("List of words must has at least 1 word");

        for (MyPost post: this.socialPosts) {
            Iterator<String> iter = words.iterator();

            while(iter.hasNext()){
                if(post.getText().contains(iter.next())){
                    list.add(post);
                    break;
                }
            }
        }

        return list;
    }

    public Set<MyUser> getSocialUsers() {
        return (TreeSet<MyUser>) this.socialUsers.clone();
    }

    public Map<String, Set<String>> getMicroBlog() {
        return (Map<String, Set<String>>) this.microBlog.clone();
    }

    public Map<MyPost, Integer> getLikesPosts() {
        return (HashMap<MyPost, Integer>) this.likesPosts.clone();
    }

    public List<MyPost> getSocialPosts() {
        return (LinkedList<MyPost>) this.socialPosts.clone();
    }

    private boolean checkUser(String name){
        Iterator<MyUser> iter = socialUsers.iterator();

        while(iter.hasNext()) {
            if(iter.next().getNickname().equals(name))
                return true;
        }

        return false; //nessun utente con quel nome è stato trovato
    }

    public boolean like(MyUser us, MyPost ps) throws IllegalArgumentException{
        if(!socialUsers.contains(us))
            throw new IllegalArgumentException("User doesn't exist in this social network");
        if(!socialPosts.contains(ps))
            throw new IllegalArgumentException("Post is not in this social network");

        if(us.addLikedPost(ps)) {
            likesPosts.replace(ps, likesPosts.get(ps) + 1);
            return true;
        }

        return false;
    }

    public boolean removeLike(MyUser us, MyPost ps) throws IllegalArgumentException{
        if(!socialUsers.contains(us))
            throw new IllegalArgumentException("User doesn't exist in this social network");
        if(!socialPosts.contains(ps))
            throw new IllegalArgumentException("Post is not in this social network");

        if(us.removeLikedPost(ps)){
            likesPosts.replace(ps, likesPosts.get(ps) - 1);
            return true;
        }

        return false;
    }

}
