/**
 * A class used to generate random requests by storing a request type, a video
 * object and a CustomerDLL
 * 
 * @author John
 */
public class RequestDLL {
    // instance variables
    public int requestSpecifier; // the type of request is either a 1, 2, 3
    public video vid = new video(); // video object of the request
    public CustomerDLL cust = new CustomerDLL(); // customerDLL of the request

    /**
     * default constructor
     */
    public RequestDLL() {
    }

    /**
     * constructor with a request int and video object passed in
     * 
     * @param req the request type
     * @param v   the video object
     */
    public RequestDLL(int req, video v) {
        requestSpecifier = req;
        vid = v;
    }

    /**
     * constructor with a request in, a customerDLL object, and video object passed
     * in
     * 
     * @param req the type of request
     * @param c   the customerDLL object
     * @param v   the video object
     */
    public RequestDLL(int req, CustomerDLL c, video v) {
        requestSpecifier = req;
        cust = c;
        vid = v;
    }

    /**
     * @return the video object
     */
    public video getVideo() {
        return vid;
    }

    /**
     * sets the video object of request
     * 
     * @param v
     */
    public void setVideo(video v) {
        vid = v;
    }

    /**
     * @return the customer object of request
     */
    public CustomerDLL getCust() {
        return cust;
    }

    /**
     * sets the customer instance variable to the new passed in customerDLL object
     * 
     * @param c
     */
    public void setCust(CustomerDLL c) {
        cust = c;
    }

    /**
     * @return the type of request
     */
    public int getRequestType() {
        return requestSpecifier;
    }

    /**
     * sets the request type to the passed in value
     * 
     * @param r an int of request type
     */
    public void setRequestType(int r) {
        requestSpecifier = r;
    }
}
