package group;

import comment.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import post.PostParser;
import post.PostUtils;
import place.Place;
import post.Post;
import vkannotation.Open;

/**
 *
 * @author Игорь
 */
public class Group {

    private String groupId;
    private String groupName;
    private String groupScreenName;
    private String isClosed;
    private GroupType groupType;
    private String photoURL;
    private String photoMediumURL;
    private String photoBigURL;
    private Place location;
    private int membersCount;
    private boolean isDeactivated = false;
    private int postCount;

    @Override
    public String toString() {
        return "Group{"
                + " \n groupId=" + groupId
                + ",\n groupName=" + groupName
                + ",\n groupScreenName=" + groupScreenName
                + ",\n location=" + getLocation()
                + ",\n membersCount=" + membersCount
                + ",\n postCount=" + postCount
                + ",\n isClosed=" + isClosed
                + ",\n groupType=" + groupType
                + " \n}";
    }

    private Group(String groupId) {
        this.groupId = groupId;
    }

    /**
     * create Group by id
     *
     * @return return Group by id
     */
    @Open
    public static Group getGroupById(String id) throws IOException, CreateGroupException {
        String url = createGroupURL(id);
        return getGroupByURL(url, id);
    }

    private static String createGroupURL(String id) {
        String url = "https://api.vk.com/method/"
                + "groups.getById.xml"
                + "?group_id=" + id
                + "&fields=" + "city,country,members_count";
        return url;
    }

    private static Group getGroupByURL(String url, String id) throws IOException, CreateGroupException {
        Group g = new Group(null);
        Document groupXML = Jsoup.connect(url).get();
        checkGroupValid(groupXML);
        createGroup(groupXML, g);
        String postURL = PostUtils.createPostURL(id, 0, 1);
        Document postXML = Jsoup.connect(postURL).get();
        String postCount = postXML.getElementsByTag("count").get(0).text();
        g.setPostCount(Integer.valueOf(postCount));
        return g;
    }

    private static void checkGroupValid(Document html) throws CreateGroupException {
        String error = html.getElementsByTag("error").text();
        if (!error.isEmpty()) {
            if ("100".equals(error)) {
                throw new CreateGroupException("group_ids is undefined");
            } else {
                throw new CreateGroupException();
            }
        }
    }

    private static void createGroup(Document html, Group g) throws IOException {
        String gid = html.getElementsByTag("gid").text();
        g.setGroupId(gid);

        String name = html.getElementsByTag("name").text();
        g.setGroupName(name);

        String screen_Name = html.getElementsByTag("screen_name").text();
        g.setGroupScreenName(screen_Name);

        String is_Closed = html.getElementsByTag("is_closed").text();
        g.setIsClosed(is_Closed);

        String type = html.getElementsByTag("type").text();
        g.setGroupType(GroupType.valueOf(type));

        String photo = html.getElementsByTag("photo").text();
        g.setPhotoURL(photo);

        String photo_Medium = html.getElementsByTag("photo_medium").text();
        g.setPhotoMediumURL(photo_Medium);

        String photo_Big = html.getElementsByTag("photo_big").text();
        g.setPhotoBigURL(photo_Big);

        String deactivated = html.getElementsByTag("deactivated").text();
        if (!deactivated.isEmpty()) {
            g.setDeactivated(true);
        }

        String cityId = html.getElementsByTag("city").text();
        String countryId = html.getElementsByTag("country").text();
        Place place = Place.createPlace(cityId, countryId);
        g.setLocation(place);

        String count = html.getElementsByTag("members_count").text();
        g.setMembersCount(Integer.valueOf(count));
    }

    /**
     * get List of posts by interval for this user
     *
     * @return return URL by String
     */
    @Open
    public List<Post> getPostList(int start, int postCount) throws SAXException,
            InterruptedException, ParserConfigurationException, DOMException, IOException {
        ArrayList<Post> a = new ArrayList<>();
        new PostParser(groupScreenName, start, postCount).updateList(a);
        return a;
    }

    /**
     * Get the value of photoURL
     *
     * @return the value of photoURL
     */
    public String getPhotoURL() {
        return photoURL;
    }

    /**
     * Set the value of photoURL
     *
     * @param photoURL new value of photoURL
     */
    private void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    /**
     * Get the value of groupId
     *
     * @return the value of groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Set the value of groupId
     *
     * @param groupId new value of groupId
     */
    private void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    private void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the groupScreenName
     */
    public String getGroupScreenName() {
        return groupScreenName;
    }

    /**
     * @param groupScreenName the groupScreenName to set
     */
    private void setGroupScreenName(String groupScreenName) {
        this.groupScreenName = groupScreenName;
    }

    /**
     * @return the photoMediumURL
     */
    public String getPhotoMediumURL() {
        return photoMediumURL;
    }

    /**
     * @param photoMediumURL the photoMediumURL to set
     */
    private void setPhotoMediumURL(String photoMediumURL) {
        this.photoMediumURL = photoMediumURL;
    }

    /**
     * @return the photoBigURL
     */
    public String getPhotoBigURL() {
        return photoBigURL;
    }

    /**
     * @param photoBigURL the photoBigURL to set
     */
    private void setPhotoBigURL(String photoBigURL) {
        this.photoBigURL = photoBigURL;
    }

    /**
     * @return the isClosed
     */
    public String getIsClosed() {
        return isClosed;
    }

    /**
     * @param isClosed the isClosed to set
     */
    private void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    /**
     * @return the groupType
     */
    public GroupType getGroupType() {
        return groupType;
    }

    /**
     * @param groupType the groupType to set
     */
    private void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    /**
     * @return the isDeactivated
     */
    public boolean isDeactivated() {
        return isDeactivated;
    }

    /**
     * @param isDeactivated the isDeactivated to set
     */
    private void setDeactivated(boolean isDeactivated) {
        this.isDeactivated = isDeactivated;
    }

    /**
     * @return the menbersCount
     */
    public int getMenbersCount() {
        return membersCount;
    }

    /**
     * @param menbersCount the menbersCount to set
     */
    private void setMembersCount(int menbersCount) {
        this.membersCount = menbersCount;
    }

    /**
     * @return the location
     */
    public Place getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Place location) {
        this.location = location;
    }

    /**
     * @return the postCount
     */
    public int getPostCount() {
        return postCount;
    }

    /**
     * @param postCount the postCount to set
     */
    private void setPostCount(int postCount) {
        this.postCount = postCount;
    }

}
