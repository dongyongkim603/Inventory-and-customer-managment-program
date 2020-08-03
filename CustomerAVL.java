/**
 *
 * @author John
 */
public class CustomerAVL {
    // instance varriables
    public String name = "";

    public String customerId = "";

    public AVLTreeMap<String, video> rentedVideo = new AVLTreeMap<>();

    public int numberOfRented = 0;

    // Defualt constructor
    public CustomerAVL() {
    }

    public CustomerAVL(String n, String i) {
        name = n;
        customerId = i;
    }

    /**
     * constructor with parameters
     * 
     * @param n the name of the new customer object
     * @param i the customer id
     * @param v the video object associated with the customer
     */
    public CustomerAVL(String n, String i, video v) {
        name = n;
        customerId = i;
        rentedVideo.put(v.getId(), v);
        numberOfRented++;
    }

    // access methods

    /**
     * @return the name of the customer as a String
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id number of the customer as a String
     */
    public String getId() {
        return customerId;
    }

    /**
     * removes all the associated videos in the customer field variables by creating
     * a new SLL object containing the removed objects
     * 
     * @return a SLL of the removed videos will return null SLL if empty
     * 
     *         public TreeMap<String, video> getRentVideo () { TreeMap<String,
     *         video> temp = new TreeMap<>(); while(!rentedVideo.isEmpty()) {
     *         temp.addFirst(rentedVideo.removeFirst()); numberOfRented--; } return
     *         temp; }
     */

    /**
     * adds a single video object to the customer
     * 
     * @param v video object
     */
    public void setRentVideo(video v) {
        rentedVideo.put(v.getId(), v);
        numberOfRented++;
    }

    /**
     * removes all the associated videos in the customer field variables by creating
     * a new BST object containing the removed objects
     * 
     * @return a BST of the removed videos will return null SLL if empty
     */
    public AVLTreeMap<String, video> removeVideo() {
        AVLTreeMap<String, video> temp = new AVLTreeMap<>();
        while (!rentedVideo.isEmpty()) {
            Entry entry = rentedVideo.firstEntry();
            video v = rentedVideo.remove(entry.getKey().toString());
            temp.put(v.getId(), v);
            numberOfRented--;
        }
        return temp;
    }

    public void setCustomerName(String n) {
        name = n;
    }

    public void setCustomerId(String d) {
        customerId = d;
    }

    public int getNumberOfRented() {
        return numberOfRented;
    }
}
