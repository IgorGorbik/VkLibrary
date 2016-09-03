package messages;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import vkannotation.AccessToken;

/**
 *
 * @author Игорь
 */
public class Message {

    private String id;
    private String from_id;
    private String date;
    private String body;
    private String read_state;

    public Message(String id) {
        this.id = id;
    }

    /**
     * create Message by id
     *
     * @return return Message by id
     */
    @AccessToken
    public static Message getMessageById(String id, String access) throws IOException {
        String url = createMessageURL(id, access);
        return getMessageByURL(url, id);
    }

    private static String createMessageURL(String id, String access) {
        String url = "https://api.vk.com/method/"
                + "messages.getById.xml"
                + "?message_ids=" + id
                + "&access_token=" + access;
        return url;
    }

    private static Message getMessageByURL(String url, String id) throws IOException {
        Message g = new Message(null);
        Document groupXML = Jsoup.connect(url).get();
        createMessage(groupXML, g);
        return g;
    }

    private static void createMessage(Document html, Message user) throws IOException {
        String id = html.getElementsByTag("mid").text();
        user.setId(id);

        String from = html.getElementsByTag("uid").text();
        user.setFrom_id(from);

        String d = html.getElementsByTag("date").text();
        user.setDate(d);

        String b = html.getElementsByTag("body").text();
        user.setBody(b);

        String r = html.getElementsByTag("read_state").text();
        user.setRead_state(r);
    }

    /**
     * Get the value of read_state
     *
     * @return the value of read_state
     */
    public String getRead_state() {
        return read_state;
    }

    /**
     * Set the value of read_state
     *
     * @param read_state new value of read_state
     */
    public void setRead_state(String read_state) {
        this.read_state = read_state;
    }

    /**
     * Get the value of body
     *
     * @return the value of body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the value of body
     *
     * @param body new value of body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the value of from_id
     *
     * @return the value of from_id
     */
    public String getFrom_id() {
        return from_id;
    }

    /**
     * Set the value of from_id
     *
     * @param from_id new value of from_id
     */
    public void setFrom_id(String from_id) {
        this.from_id = from_id;
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
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

}
