package user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import vkannotation.Open;
import java.util.Objects;

/**
 *
 * @author Игорь
 */
public class User {

    private String id;
    private String first_name;
    private String last_name;
    private boolean hidden;
    private boolean deactivated;

    public User(String id) {
        this.id = id;
    }

    public User(String id, String first_name, String last_name, boolean hidden, boolean deactivated) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hidden = hidden;
        this.deactivated = deactivated;
    }

    @Override
    public int hashCode() {
        int uid = Integer.valueOf(id);
        return uid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return first_name
                + " "
                + last_name;
    }

    /**
     * get List of Friends for this User
     *
     * @return List of Friends for this User
     */
    public List<User> getFriends(String access) throws Exception {
        ArrayList<User> a = new ArrayList<>();
        new UserParser(id, access).updateList(a);
        return a;
    }

    /**
     * create User by id
     *
     * @return return User by id
     */
    @Open
    public static User getUserById(String id) throws IOException {
        String url = createUserURL(id);
        return getUserByURL(url, id);
    }

    private static String createUserURL(String id) {
        String url = "https://api.vk.com/method/"
                + "users.get.xml"
                + "?user_ids=" + id;
        return url;
    }

    private static User getUserByURL(String url, String id) throws IOException {
        User g = new User(null);
        Document groupXML = Jsoup.connect(url).get();
        createUser(groupXML, g);
        return g;
    }

    private static void createUser(Document html, User user) throws IOException {
        String id = html.getElementsByTag("uid").text();
        user.setId(id);

        String name = html.getElementsByTag("first_name").text();
        user.setFirst_name(name);

        if ("DELETED".equals(name)) {
            return;
        }

        String surname = html.getElementsByTag("last_name").text();
        user.setLast_name(surname);

        String deactivated = html.getElementsByTag("deactivated").text();
        if (!deactivated.isEmpty()) {
            user.setDeactivated(true);
        }

        String h = html.getElementsByTag("hidden").text();
        if (!h.isEmpty()) {
            user.setHidden(true);
        }
    }

    /**
     * Get the value of hidden
     *
     * @return the value of hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Set the value of hidden
     *
     * @param hidden new value of hidden
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Get the value of last_name
     *
     * @return the value of last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Set the value of last_name
     *
     * @param last_name new value of last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Get the value of first_name
     *
     * @return the value of first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Set the value of first_name
     *
     * @param first_name new value of first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the deactivated
     */
    public boolean isDeactivated() {
        return deactivated;
    }

    /**
     * @param deactivated the deactivated to set
     */
    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
