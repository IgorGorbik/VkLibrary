package comment;

import utils.VkUtils;

/**
 *
 * @author Игорь
 */
public class Comment {
    
    private String id;
    private String from_id;
    private String date;
    private String text;
    private String likes;
    
    public Comment(String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Comment[" + "id=" + id
                + ", from_id=" + from_id
                + ", date=" + VkUtils.getData(date)
                + ", text=" + text
                + ", likes=" + likes
                + "]";
    }

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
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
     * @return the from_id
     */
    public String getFrom_id() {
        return from_id;
    }

    /**
     * @param from_id the from_id to set
     */
    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    /**
     * @return the likes
     */
    public String getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(String likes) {
        this.likes = likes;
    }
    
}
