public class Blog {
    private String domain;
    private String username;
    private int numOfArticles;
    private int numOfComments;
    private long PV;
    private String email;
    private String platform;
    public void post() {
        this.numOfArticles += 1;
    }
    public void comment() {
        this.numOfComments += 1;
    }
    public void read() {
        this.PV += 1;
    }
    public Blog(String domain, String username) {
        this.domain = domain;
        this.username = username;
        System.out.println("Your blog is set. " + this.username + ", Welcome.");
    }
}
