package messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vkannotation.AccessToken;

/**
 *
 * @author Игорь
 */
public class MessageUtils {

    static DocumentBuilderFactory dbFactory;
    static DocumentBuilder dBuilder;
    static org.w3c.dom.Document doc;

    /**
     * send Message to user
     *
     */
    @AccessToken
    public static void sendMessage(String str, String id, String access_token) throws MalformedURLException, IOException {
        String utf8String = URLEncoder.encode(str, "UTF-8");
        String url1 = "https://api.vk.com/method/"
                + "messages.send.xml"
                + "?user_id=" + id
                + "&message=" + utf8String
                + "&access_token=" + access_token;
        URL url = new URL(url1);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        try (Reader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

            int a = 0;

            while ((a = buff.read()) != -1) {
                System.out.print((char) a);
            }

        }
    }

    /**
     * get last received message
     *
     * @return messaged's id
     */
    @AccessToken
    public static int getLastMessage(String access_token) throws Exception {
        String url = "https://api.vk.com/method/"
                + "messages.get.xml"
                + "?offset=0"
                + "&count=1"
                + "&access_token=" + access_token;
        Document groupXML = Jsoup.connect(url).get();
        String id = groupXML.getElementsByTag("mid").text();
        System.out.println(id);
        int i = Integer.valueOf(id);
        return i;
    }

    /**
     * get last received messages
     *
     * @return List of last messages by count
     */
    @AccessToken
    public static List<Message> getLastMessages(String access_token, int count) throws Exception {
        List<Message> list = new ArrayList<>();
        String url = "https://api.vk.com/method/"
                + "messages.get.xml"
                + "?offset=0"
                + "&count=" + count
                + "&access_token=" + access_token;
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(url);
        doc.getDocumentElement().normalize();
        NodeList nList = getNodeList();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                String id1 = eElement.getTextContent();
                Message u = Message.getMessageById(id1, access_token);
                list.add(u);
            }
        }
        return list;
    }

    private static NodeList getNodeList() {
        NodeList nList = doc.getElementsByTagName("mid");
        return nList;
    }

}
