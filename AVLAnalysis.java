import java.util.Random;

/**
 * A class of the video store that generates random videos, customers, and
 * requests and then takes a measurement of the time to process all requests
 * 
 * @author John
 */
public class AVLAnalysis {
    // instance vairables
    boolean EXIT; // the boolean that is checked in runMenu loop to create exit condition

    // class instance object variable of the DoublyLinkedList data structure with
    // type videoCheck. Lists all videos currently in store
    private final AVLTreeMap<String, video> BSTvideo = new AVLTreeMap<>();

    // class instance object variable of the DoublyLinkedList data structure with
    // type customer
    private final AVLTreeMap<String, CustomerAVL> BSTcustomer = new AVLTreeMap<>();

    //// class instance object variable of the DoublyLinkedList data structure with
    //// type videoCheck. List all total videos in store records
    private final AVLTreeMap<String, video> BSTTotalVideos = new AVLTreeMap<>();

    protected video comparatorVideo = new video(); // used as a temporary video object
    protected CustomerAVL comparatorCustomer = new CustomerAVL(); // used as a temporary CustomerAVL object
    public int vids = 0; // number of create video requests passed in
    public int custs = 0; // number of create customer requests passed in
    public int actions = 0; // number of create customer requests passed in
    private MyQueue<RequestAVL> queue = new MyQueue<>(); // a queue used to hold the requests

    // -----------------------menu
    // constructions-------------------------------------
    /**
     * default constructor
     */
    public AVLAnalysis() {
    }

    /**
     * constructor with parameters that set the corresponding instance variables
     * 
     * @param v the number of video objects to be generated
     * @param c the number of customer objects to be generated
     * @param a the number of request objects to be generated
     */
    public AVLAnalysis(int v, int c, int a) {
        vids = v;
        custs = c;
        actions = a;
    }

    /**
     * calls mainMenu and performs loop waiting for input. Input is provided by the
     * call to getInputInt(). generates a number of videos equal to instance
     * variable vids, generates a number of customers equal to the instance variable
     * custs, and generates a number of requests equal to the instance variable
     * actions. All id's and names are set to random and unique numbers. Requests
     * hold a set of requests 1,2,3 and customer/video objects taken from the newly
     * generated video and customer lists. The requests are then stored in the queue
     */
    public void runMenu() {
        Random randomVideo = new Random(); // Random object for setting vidRand int
        int vidRand = 0; // integer for the video id
        for (int i = vids; i > 0; i--) // for the vids to 0 add videos
        {
            vidRand = randomVideo.nextInt(i) + 1; // creates random video values
            String x = Integer.toString(vidRand); // casts j into a string
            video v = new video(x, x); // creates new video object with x as id and name
            addVideo(v); // adds them to lists
        }
        int custRand = 0; // integer for the customer id
        Random randomCustomer = new Random(); // Random object for setting custRand int
        for (int i = custs; 0 < i; i--) // for cust to 0 gernerate customers
        {
            custRand = randomCustomer.nextInt(i) + 1; // creates random customer values
            String x = Integer.toString(custRand); // casts j into String x
            CustomerAVL custom = new CustomerAVL(x, x); // creates new customerSLL object with name and id set to x
            addCustomer(custom); // adds customer to list
        }
        int actionRand = 0; // action integer
        Random randomAction = new Random(); // Random object for setting action int
        for (int i = actions; i > 0; i--) // for actions to 0 generate random request objects
        {
            actionRand = randomAction.nextInt(3) + 1; // gives int of either 1, 2, or 3
            custRand = randomCustomer.nextInt(custs); // gives random int of customer id between 0 and list size
            String CR = Integer.toString(custRand + 1); // creates String set to the custRand value
            CustomerAVL CRIn = new CustomerAVL(CR, CR); // creates new CustomerAVL object with CR as name and id
            vidRand = randomVideo.nextInt(vids); // gives random int of customer id between 0 and list size
            String VR = Integer.toString(vidRand + 1); // creates String set to the vidRand value
            video VRIn = new video(VR, VR); // creates new video object with VR as name and id
            RequestAVL req = new RequestAVL(actionRand, CRIn, VRIn); // creates new request object with random field
                                                                     // variables
            queue.enqueue(req); // puts request into queue
        }
    }

    /**
     * processes the requests stored in the queue and takes a measurement of the
     * system time before the requests are processed. Each request has a requestType
     * int that is either a 1, 2, or 3 which will correspond to the proper method
     * call. The method call will have values taken from the request object passed
     * in. After all requests are processed, a measurement of the system time in
     * nano is taken and this value is subtracted from the previous value to get a
     * total experimental process time. This time is them printed
     */
    public void runRequests() {
        long beforeTime = System.nanoTime(); // access system time before processes and create long
        while (!queue.isEmpty()) // while there are requests to be processed
        {
            RequestAVL requestType = new RequestAVL(); // create new RequestAVL object
            requestType = queue.dequeue(); // request is set to the dequeued value
            if (requestType.getRequestType() == 1) // process the request type
            {
                checkInStore(requestType.getVideo());
            } else if (requestType.getRequestType() == 2) {
                checkOutVideo(requestType.getVideo(), requestType.getCust());
            } else if (requestType.getRequestType() == 3) {
                checkInVideo(requestType.getVideo());
            }
        }
        long afterTime = System.nanoTime(); // access system time after processes and create long
        long elapsedTime = afterTime - beforeTime; // get an elapsed time by subtracting before from after

        // print the time
        System.out.println("the total run time of all requests in AVL was: " + elapsedTime + " nano seconds");
        exit();
    }

    // ------------------------user option
    // methods-----------------------------------

    /**
     * allows user to add video object to BST TreeMap by calling the getInputString
     * method to create name and id String variables and constructing video object.
     * This object is then passed to the put method where it will add video to tree
     */
    public void addVideo(video v) {
        String id = v.getId(); // get the id of the video as a String to be used as key
        BSTvideo.put(id, v); // puts video object in in-store tree at key of video id
        BSTTotalVideos.put(id, v); // puts video object in total video tree at key of video id
    }

    /**
     * allows user to add in to data structure BSTcustomer, new customer object
     * calls getInputString method to create String to be passed in constructor of
     * customer object
     * 
     * @return the newly added CustomerBST object
     */
    public void addCustomer(CustomerAVL c) {
        String id = c.getId(); // get the id of the customer as a String to be used as key
        BSTcustomer.put(id, c); // adds the newly created customer object to the BSTcustomer tree
    }

    /**
     * allows user to enter a video id and title which is converted to string
     * variables. these variables are then used to create a comparator video object.
     * If the get method cannot find the node with String id, then it will create a
     * null comparator. If the comparator is not null the video was found and the
     * user is informed. Else the video is not in the BSTvideo tree (in store) and
     * the user is informed
     * 
     * @return true if video is in store false if it is not
     */
    public boolean checkInStore(video v) {
        String id = v.getId();
        comparatorVideo = BSTvideo.get(id); // uses get method to look for matching video in tree
        if (comparatorVideo != null) // if the video was found comparator will equal video
        {
            return true;
        } else // else the video was not found therefore comparator will not equal video
        {
            return false;
        }
    }

    /**
     * allows the user to enter a customer name and id. these values are turned into
     * Strings variables. The id variable is passed to the get method which checks
     * the BSTcustomer tree for a matching key value. A new customer object is
     * created from the get methods return value. If the comparator value is not
     * null the user is informed the customer exists else the user is told they do
     * not.
     * 
     * @return true if customer is a member
     */
    public boolean checkCustomer(CustomerAVL c) {
        String id = c.getId();
        comparatorCustomer = BSTcustomer.get(id); // calls the get methods with customer id as parameters to create new
                                                  // CustomerBST object
        if (comparatorCustomer != null) // if the customerComparator has a value
        {
            return true;
        } else // else there is no customer
        {
            return false;
        }
    }

    /**
     * allows user to rent movies to customers. inStore is boolean of checkInStore
     * method. If it returns false the video cannot be rented and the user is
     * informed return false. If inStore is true then a boolean customerInStore is
     * created from the return value of checkCustomer method. If false the
     * addCustomer method is invoked and the user is told to try the rental again
     * return false. if customerInStore is true a new CustomerBST object is created
     * by removing the matching customer entry from BSTcustomer tree. A video object
     * outVid is then created by removing the corresponding entry from the BSTvideo
     * tree. the outVid is then added to the customerBST and then the customer is
     * added back into the BSTcustomer tree return true.
     * 
     * @return true if both video and customer are in store.
     */
    public boolean checkOutVideo(video v, CustomerAVL c) {
        boolean inStore = checkInStore(v); // Boolean created by invoking checkInStore method
        if (inStore != true) // if video is not in store
        {
            return false;
        } else // else video is in store
        {
            boolean customerInStore = checkCustomer(c); // Boolean created by invoking the checkCustomer method
            if (customerInStore == false) // customer is not a member
            {
                return false;
            } else // customer is a member
            {
                CustomerAVL hold = BSTcustomer.remove(comparatorCustomer.getId());// creates new CustomerBST object by
                                                                                  // removing matching object from tree
                video outVid = BSTvideo.remove(comparatorVideo.getId()); // creates new video object by removing
                                                                         // matching oject from BSTvideo
                hold.numberOfRented++; // increment numberOfRented
                hold.rentedVideo.put(comparatorVideo.getId(), outVid); // adds video to the customer
                BSTcustomer.put(hold.getId(), hold); // re-adds the customer back to the tree
                return true;
            }
        }
    }

    /**
     * Allows the user to enter the title and an id and return the video if it is
     * being rented. It first uses the name and id String variables to update the
     * comparatorVideo variable. The BSTcustomer tree is checked if empty return
     * false if not the int j will bet set to the size of the BSTcustomer tree.
     * While j does not equal 0. and Entry object is made and a String key is set
     * from entry key. The comparatorCustomer variable is set to the customer at
     * key. if the customerComparator rentedVideo tree .get returns a non-null
     * value, comparatorVideo is set to the the removed video from the customer. The
     * video is then put into the BSTvideo list. The user is informed. If the loop
     * is exited the video is not being rented. return false
     * 
     * @return true if customer in BSTcustomer has video false if no customers or
     *         the video is not being rented
     */
    public boolean checkInVideo(video v) {
        if (v == null) // if there is no values in video v
        {
            return false;
        } else {
            String id = v.getId();
            if (BSTcustomer.isEmpty()) // if there are no customers
            {
                return false;
            } else // there are customers
            {
                int j = BSTcustomer.size(); // gets the size of the BSTcustomer tree
                Entry entry = BSTcustomer.firstEntry(); // gets the entry of key with least value
                while (j != 0) // while there are customers to be checked
                {
                    String key = entry.getKey().toString(); // String of the current key
                    comparatorCustomer = BSTcustomer.get(key); // sets the comparatorCustomer to the customer at key in
                                                               // tree
                    if (null != comparatorCustomer.rentedVideo.get(id)) // if the customer has the mataching id of the
                                                                        // video
                    {
                        comparatorVideo = comparatorCustomer.rentedVideo.remove(id);// sets the comparatorVideo variable
                                                                                    // to the returned removed video
                                                                                    // from customer
                        BSTvideo.put(comparatorVideo.getId(), comparatorVideo); // re-adds the video to the BSTvideo
                                                                                // list ie the video is checked in
                        return true;
                    } else // the video was not checkout by this customer
                    {
                        entry = BSTcustomer.higherEntry(key); // the entry variable is set to next highest key value
                        j--; // decrement j
                    }
                }
                return false; // the video was not found to be with any customer
            }
        }
    }

    public void exit() {
        EXIT = true;
    }
}
