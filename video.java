/**
 * A class for the video object type. video type has identifier id and a title
 * 
 * @author John
 * @param <video>
 */
public class video<video> {

    // instance varribles
    private String title = null;
    private String id = null;

    // default constructor
    public video() {
    }

    /**
     * constructs a new video object with parameters
     * 
     * @param name   the name of the video
     * @param number the id number associated with the object
     */
    public video(String name, String number) {
        title = name;
        id = number;
    }

    // access methods

    /**
     * @return the title of the video
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the id of the title
     */
    public String getId() {
        return id;
    }

    /**
     * sets the title of the video to the passed in String name
     * 
     * @param name the new title
     */
    public void setTitle(String name) {
        title = name;
    }

    /**
     * sets the id of the video to the passed in String i
     * 
     * @param i
     */
    public void setId(String i) {
        id = i;
    }
}
