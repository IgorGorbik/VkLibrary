package comment;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 *
 * @author Игорь
 */
public class CommentUtils {

    /**
     * parse XML of comments
     *
     */
    public static void createCommentByXml(Node nNode, Comment com) throws DOMException {
        org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

        String id = eElement.getElementsByTagName("cid").item(0).getTextContent();
        com.setId(id);

        String from_id = eElement.getElementsByTagName("from_id").item(0).getTextContent();
        com.setFrom_id(from_id);

        String date = eElement.getElementsByTagName("date").item(0).getTextContent();
        com.setDate(date);

        String text = eElement.getElementsByTagName("text").item(0).getTextContent();
        com.setText(text);

        String likes = ((org.w3c.dom.Element) eElement
                .getElementsByTagName("likes").item(0))
                .getElementsByTagName("count").item(0).getTextContent();
        com.setLikes(likes);
    }

    /**
     * create comment's URL
     *
     * @return return URL by String
     */
    public static String createCommentURL(String ownid, int postId, int start, int count) {
        String url = "https://api.vk.com/method/"
                + "wall.getComments.xml"
                + "?owner_id=" + ownid
                + "&post_id=" + postId
                + "&offset=" + start
                + "&preview_length=" + 0
                + "&count=" + 100
                + "&need_likes=" + 1;
        return url;
    }

}
