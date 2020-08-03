/**
 * am implementation of the CustomerDLL. the class allows the creation of
 * customer objects that store name and id of customer as a string and use a
 * DoublyLinkedList to hold video objects
 * 
 * @author John
 */
public class CustomerDLL {

    // instance varriables
    public String name = null;

    public String customerId = null;

    public DoublyLinkedList<video> rentedVideo = new DoublyLinkedList<>();

    public int numberOfRented = 0;

    // Defualt constructor
    public CustomerDLL() {
    }

    // constructor with name and id no associated video objects
    public CustomerDLL(String n, String i) {
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
    public CustomerDLL(String n, String i, video v) {
        name = n;
        customerId = i;
        rentedVideo.addFirst(v);
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
     */
    public DoublyLinkedList<video> getRentVideo() {
        DoublyLinkedList<video> temp = new DoublyLinkedList<>();
        while (!rentedVideo.isEmpty()) {
            temp.addFirst(rentedVideo.removeFirst());
            numberOfRented--;
        }
        return temp;
    }

    /**
     * adds a single video object to the customer
     * 
     * @param v video object
     */
    public void setRentVideo(video v) {
        rentedVideo.addFirst(v);
        numberOfRented++;
    }

    /**
     * allows multiple video objects in the form of a SinglyLinkedList of videos, to
     * be added to the customer
     * 
     * @param video SinglyLinkedList of videos
     */
    public void setMultiRentVideo(DoublyLinkedList<video> video) {
        while (!video.isEmpty()) {
            rentedVideo.addFirst(video.removeFirst());
            numberOfRented++;
        }
    }

    /**
     * removes all the associated videos in the customer field variables by creating
     * a new SLL object containing the removed objects
     * 
     * @return a SLL of the removed videos will return null SLL if empty
     */
    public DoublyLinkedList<video> removeVideo() {
        DoublyLinkedList<video> temp = new DoublyLinkedList<>();
        while (numberOfRented != 0) {
            temp.addFirst(rentedVideo.removeFirst());
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
