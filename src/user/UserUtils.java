package user;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import vkannotation.*;

/**
 *
 * @author Игорь
 */
public class UserUtils {

    /**
     * create friends's URL
     *
     * @return return URL by String
     */
    @Open
    public static String createFriendsListURL(String id) {
        String url1 = "https://api.vk.com/method/"
                + "friends.get.xml"
                + "?user_id=" + id;
        return url1;
    }

    /**
     * create friends's List URL
     *
     * @return return URL by String
     */
    @AccessToken
    public static String createFriendsListURL(String id, String access) {
        String url1 = "https://api.vk.com/method/"
                + "friends.get.xml"
                + "?user_id=" + id
                + "&access_token=" + access;
        return url1;
    }

    /**
     * create User's URL
     *
     * @return return URL by String
     */
    @AccessToken
    public static String createAppUserURL(String access) {
        String url1 = "https://api.vk.com/method/"
                + "users.get.xml"
                + "?access_token=" + access;
        return url1;
    }

    /**
     * create own User
     *
     * @return return own User
     */
    @AccessToken
    public static User getAppUser(String access) throws IOException {
        User user = new User(null);
        String url = createAppUserURL(access);
        Document groupXML = Jsoup.connect(url).get();
        String id = groupXML.getElementsByTag("uid").get(0).text();
        user = User.getUserById(id);
        return user;
    }

}
