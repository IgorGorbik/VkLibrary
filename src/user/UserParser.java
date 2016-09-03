package user;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Игорь
 */
public class UserParser {

    private String access;
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    org.w3c.dom.Document doc;

    private String id;

    public UserParser(String id, String access) {
        this.id = id;
        this.access = access;
    }
    
    /**
     * update List of users
     *
     */
    public void updateList(List<User> arr) throws ParserConfigurationException, SAXException, IOException {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        String url1 = null;
        if (access == null) {
            url1 = UserUtils.createFriendsListURL(id);
        } else {
            url1 = UserUtils.createFriendsListURL(id, access);
        }
        doc = dBuilder.parse(url1);
        doc.getDocumentElement().normalize();
        NodeList nList = getNodeList();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                String id1 = eElement.getTextContent();
                User u = User.getUserById(id1);
                arr.add(u);
            }
        }

    }

    private NodeList getNodeList() {
        NodeList nList = doc.getElementsByTagName("uid");
        return nList;
    }

}
