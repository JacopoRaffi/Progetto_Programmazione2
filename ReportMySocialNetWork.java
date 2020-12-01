package Social;
import java.util.*;

public class ReportMySocialNetWork extends MySocialNetWork implements ReportSocialNetwork{
    /* @REPINV:
    * postReports != null && forAll key K in postReports ==> postReports.get(K) >= 1 && K is in socialPosts
    *  */

    /*@ABSFUN: α t.c.:
    * α(HashMap<MyPost, Integer>) -> una map che associa ad ogni post, segnalato almeno 1 volta, il numero di segnalazioni
     * */

    private final HashMap<MyPost, Integer> postReports;

    public ReportMySocialNetWork(){
        super();
        postReports = new HashMap<>();
    }

    public Map<MyPost, Integer> getPostReports() {
        return (Map) this.postReports.clone();}

    public boolean report(MyUser us, MyPost ps) throws NullPointerException, IllegalArgumentException{
        if(us == null)
            throw new NullPointerException();
        if(ps == null)
            throw new NullPointerException();
        if(us.report(ps) && super.socialPosts.contains(ps) && super.socialUsers.contains(us)){
            if(!postReports.keySet().contains(ps)) {
                postReports.put(ps, 1);
            }
            else{
                postReports.replace(ps, postReports.get(ps) + 1);
            }
            return true;
        }

        return false;
    }

    public int checkPostBan(MyPost ps) throws NullPointerException, IllegalArgumentException{
        if(ps == null)
            throw new NullPointerException("ps is null");
        if(!super.socialPosts.contains(ps))
            throw new IllegalArgumentException("post is not in the social");
        return postReports.get(ps);
    }

}
