package post;

import comment.Comment;
import comment.CommentParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import utils.VkUtils;
import vkannotation.Open;

/**
 *
 * @author Игорь
 */
public class Post {

    private String id;
    private String from_id;
    private String owner_id;
    private String date;
    private String text;
    private String comments;
    private String likes;
    private String reposts;

    public Post(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{"
                + "  id=" + id
                + ", from_id=" + from_id
                + ", date=" + VkUtils.getData(date)
                + ", text=" + text
                + ", comments=" + comments
                + ", likes=" + likes
                + ", reposts=" + reposts
                + " }";
    }

    /**
     * get List of comments for this post
     *
     * @return List of comments for this post
     */
    @Open
    public List<Comment> getCommentList() throws SAXException,
            InterruptedException, ParserConfigurationException, DOMException, IOException {
        ArrayList<Comment> a = new ArrayList<>();
        new CommentParser(getOwner_id(), Integer.valueOf(getId()), 0, Integer.valueOf(comments)).updateList(a);
        return a;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the from_id
     */
    public String getFrom_id() {
        return from_id;
    }

    /**
     * @param from_id the from_id to set
     */
    public void setFromId(String from_id) {
        this.from_id = from_id;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the likes
     */
    public String getLikes() {
        return likes;
    }

    /**
     * @return the likescount
     */
    public int getLikesCount() {
        return Integer.valueOf(likes);
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(String likes) {
        this.likes = likes;
    }

    /**
     * @return the reposts
     */
    public String getReposts() {
        return reposts;
    }

    /**
     * @param reposts the reposts to set
     */
    public void setReposts(String reposts) {
        this.reposts = reposts;
    }

    /**
     * @return the owner_id
     */
    public String getOwner_id() {
        return owner_id;
    }

    /**
     * @param owner_id the owner_id to set
     */
    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

}
