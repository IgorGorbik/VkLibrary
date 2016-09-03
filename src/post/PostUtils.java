package post;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 *
 * @author Игорь
 */
public class PostUtils {

    /**
     * parse XML of posts
     *
     */
    public static void createPostByXml(Node nNode, Post post) throws DOMException {
        org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

        String id = eElement.getElementsByTagName("id").item(0).getTextContent();
        post.setId(id);

        String from_id = eElement.getElementsByTagName("from_id").item(0).getTextContent();
        post.setFromId(from_id);

        String to_id = eElement.getElementsByTagName("to_id").item(0).getTextContent();
        post.setOwner_id(to_id);

        String date = eElement.getElementsByTagName("date").item(0).getTextContent();
        post.setDate(date);

        String text = eElement.getElementsByTagName("text").item(0).getTextContent();
        post.setText(text);

        String comments = ((org.w3c.dom.Element) eElement
                .getElementsByTagName("comments").item(0))
                .getElementsByTagName("count").item(0).getTextContent();
        post.setComments(comments);

        String likes = ((org.w3c.dom.Element) eElement
                .getElementsByTagName("likes").item(0))
                .getElementsByTagName("count").item(0).getTextContent();
        post.setLikes(likes);

        String reposts = ((org.w3c.dom.Element) eElement
                .getElementsByTagName("reposts").item(0))
                .getElementsByTagName("count").item(0).getTextContent();
        post.setReposts(reposts);
    }

    /**
     * create post's URL
     *
     * @return return URL by String
     */
    public static String createPostURL(String id, int from, int count) {
        String url = "https://api.vk.com/method/"
                + "wall.get.xml"
                + "?domain=" + id
                + "&count=" + count
                + "&offset=" + from;
        return url;
    }

}
