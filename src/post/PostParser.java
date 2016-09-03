package post;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Игорь
 */
public class PostParser {

    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    org.w3c.dom.Document doc;
    private String id;
    private int start;
    private int count;

    public PostParser(String url, int start, int count) {
        id = url;
        this.start = start;
        this.count = count;
    }

    /**
     * update List of posts
     *
     */
    public void updateList(List<Post> arr) throws ParserConfigurationException,
            IOException, DOMException, SAXException {
        int temp1 = count / 100;
        int temp2 = count % 100;
        for (int i = start; i <= temp2 && temp1 >= 0; i = temp2, temp2 += 100, temp1--) {
            if (temp2 == 0) {
                continue;
            }
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            String url1 = PostUtils.createPostURL(id, i, temp2 - i);
            doc = dBuilder.parse(url1);
            doc.getDocumentElement().normalize();
            NodeList nList = getNodeList();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Post post = new Post(null);
                    PostUtils.createPostByXml(nNode, post);
                    arr.add(post);
                }
            }
        }
    }

    private NodeList getNodeList() {
        NodeList nList = doc.getElementsByTagName("post");
        return nList;
    }
}
