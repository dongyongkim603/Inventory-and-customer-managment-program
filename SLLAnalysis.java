import java.util.Random;

/**
 * A class of the video store that generates random videos, customers, and
 * requests and then takes a measurement of the time to process all requests
 * 
 * @author John
 */
public class SLLAnalysis {
    // instance vairables
    boolean EXIT; // the boolean that is checked in runMenu loop to create exit condition

    // class instance object variable of the SinglyLinkedList data structure with
    // type videoCheck. Lists all videos currently in store
    public final SinglyLinkedList<video> SLLvideo = new SinglyLinkedList<>();

    // class instance object variable of the SinglyLinkedList data structure with
    // type customer
    public final SinglyLinkedList<CustomerSLL> SLLcustomer = new SinglyLinkedList<>();

    //// class instance object variable of the SinglyLinkedList data structure with
    //// type videoCheck. List all total videos in store records
    public final SinglyLinkedList<video> SLLTotalVideos = new SinglyLinkedList<>();

    public int vids = 0; // number of create video requests passed in
    public int custs = 0; // number of create customer requests passed in
    public int actions = 0; // number of create customer requests passed in
    private MyQueue<RequestSLL> queue = new MyQueue<>(); // a queue used to hold the requests
    private CustomerSLL tempCustomer = new CustomerSLL(); // used as a temporary CustomerSLL object
    private video tempVideo = new video(); // used as a temporary video object

    // -----------------------menu
    // constructions-------------------------------------

    /**
     * default constructor
     */
    public SLLAnalysis() {
    }

    /**
     * constructor with parameters that set the corresponding instance variables
     * 
     * @param v the number of video objects to be generated
     * @param c the number of customer objects to be generated
     * @param a the number of request objects to be generated
     */
    public SLLAnalysis(int v, int c, int a) {
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
        int vidRand; // integer for the video id
        Random randomVideo = new Random(); // Random object for setting vidRand int
        for (int i = vids; i > 0; i--) // for the vids to 0 add videos
        {
            String x = Integer.toString(i); // casts j into a string
            video v = new video(x, x); // creates new video object with x as id and name
            addVideo(v); // adds them to lists
        }
        int custRand; // integer for the customer id
        Random randomCustomer = new Random(); // Random object for setting custRand int
        for (int i = custs; 0 < i; i--) // add customers
        {
            String x = Integer.toString(i); // casts j into String x
            CustomerSLL custom = new CustomerSLL(x, x); // creates new customerSLL object with name and id set to x
            addCustomer(custom); // adds customer to list
        }
        int actionRand; // action integer
        Random randomAction = new Random(); // Random object for setting action int
        for (int i = actions; i > 0; i--) // for actions to 0 generate random request objects
        {
            actionRand = randomAction.nextInt(3); // gives int of either 1, 2, or 3
            custRand = randomCustomer.nextInt(custs); // gives random int of customer id between 0 and list size
            String CR = Integer.toString(custRand + 1); // creates String set to the custRand value
            CustomerSLL CRIn = new CustomerSLL(CR, CR); // creates new CustomerSLL object with CR as name and id
            vidRand = randomVideo.nextInt(vids); // gives random int of customer id between 0 and list size
            String VR = Integer.toString(vidRand + 1); // creates String set to the vidRand value
            video VRIn = new video(VR, VR); // creates new video object with VR as name and id
            RequestSLL req = new RequestSLL(actionRand + 1, CRIn, VRIn); // creates new request object with random field
                                                                         // variables
            queue.enqueue(req);
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
            RequestSLL requestType = new RequestSLL(); // create new RequestSLL object
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
        System.out.println("the total run time of all requests in SLL was: " + elapsedTime + " nano seconds");
        exit();
    }

    // ------------------------helper search
    // methods---------------------------------

    /**
     * looks for a videoCheck within the SLLvideo list by walking through list and
     * comparing the name and id of the stored node elements with the provided
     * videoCheck name and id parameters
     * 
     * @param key
     * @param list
     * @return the index of the node where element is found return -1 if it does not
     *         exist
     */
    public video containsVideo(video key, SinglyLinkedList<video> list) {
        int i = list.getSize(); // gets the size of the list
        if (i != 0) // if the list is not empty
        {
            while (i != 0) // from i to 0
            {
                tempVideo = list.removeFirst(); // set tempVideo to return of removed item
                if (key.getId().equals(tempVideo.getId()) && // if tempVideo equals the key
                        key.getTitle().equals(tempVideo.getTitle())) {
                    return tempVideo; // return tempVideo
                } else // else no match
                {
                    list.addLast(tempVideo); // re-add to last in list
                    i--; // decrement i
                }
            }
        }
        return null; // if video could not be found
    }

    /**
     * checks the SLLcustomer list to see if the customer exists and if so where in
     * the list they appear
     * 
     * @param key the customer to be searched for
     * @return the location in the list.
     */
    public CustomerSLL containsCustomer(CustomerSLL key) {
        int i = SLLcustomer.getSize(); // get size of list
        if (i != 0) // if list is not empty
        {
            while (i != 0) // from i to 0
            {
                tempCustomer = SLLcustomer.removeFirst(); // set tempCustomer to return of removeFirst from list
                if (key.getId().equals(tempCustomer.getId())) // if the tempCustomer equals the key
                {
                    return tempCustomer; // return the tempCustomer
                } else // else no match
                {
                    SLLcustomer.addLast(tempCustomer); // re-add customer to end of list
                    i--; // decrement i
                }
            }
        }
        return null; // cannot find match return null
    }

    // ------------------------user option
    // methods-----------------------------------

    /**
     * method allows user to add/create new videoCheck object and add it to the
     * SLLvideo.
     */
    private void addVideo(video v) {
        SLLvideo.addFirst(v); // adds the newly created videoCheck object to the SLLvideo list
        SLLTotalVideos.addFirst(v); // adds the newly created videoCheck object to the SLLTotalVideos list
    }

    /**
     * allows user to add in to data structure SLLcustomer, new customer object
     * calls getInputString method to create String to be passed in constructor of
     * customer object
     *
     * @param c
     * @return the newly created customer object
     */
    public CustomerSLL addCustomer(CustomerSLL c) {
        SLLcustomer.addFirst(c); // adds newly created customer object to SLL customer list
        return c;
    }

    /**
     * checks it the videoCheck exists in the SLLvideo list ie if the videoCheck is
     * in the store prints the name of the videoCheck if found else prints false if
     * it was not
     *
     * @param v
     * @return the status of videoCheck being in store
     */
    public boolean checkInStore(video v) {
        tempVideo = containsVideo(v, SLLvideo); // calls containsVideo method to return index of videoCheck if exists if
                                                // not return-1
        if (tempVideo != null) // if tempVideo has a value
        {
            SLLvideo.addFirst(tempVideo); // add tempVideo to the store SLL list
            return true; // index is non-negitive therefor the videoCheck exists return true
        } else // else the video is not in store
        {
            return false; // index is negitive therefore the videoCheck does not exist return false
        }
    }

    /**
     * allows the user to rent videos to customers. will first ask user to enter
     * name and id. It will look to see if the videoCheck exists in the store or
     * SLLvideo list if it does it will then ask user to enter customer information
     * else it will inform the user the videoCheck is not in the store. If the
     * customer is a member it will use the created customer object with the an
     * update that sets the videoCheck object to the customer. It will then access
     * the list and replace the old customer element with the new one that has the
     * rented videoCheck update.
     *
     * @param v
     * @param c
     * @return true if videoCheck is in store and customer is member or has been
     *         added else will return false if the videoCheck is not in the store
     */
    public boolean checkOutVideo(video v, CustomerSLL c) {
        tempVideo = containsVideo(v, SLLvideo); // creates an integer of index of the videoCheck object if found -1 if
                                                // not
        if (tempVideo != null) // if tempVideo has value
        {
            tempCustomer = containsCustomer(c); // creates int k by calling containsCustomer which returns index of
                                                // customer or -1 if not
            if (tempCustomer == null) // if tempCustomer is null
            {
                return false; // it is not in store return false
            }
            tempCustomer.setRentVideo(v); // sets the videoCheck object in customer feild to the videoCheck object
            SLLcustomer.addFirst(tempCustomer); // updates the customer object in SLLcustomer list
            return true; // returns true
        } else // else video passed in has no value
        {
            return false; // returns false if the videoCheck does not exist
        }
    }

    /**
     * allows the user to enter the name and id of a videoCheck to create a
     * videoCheck object this videoCheck object is to check if any customer in the
     * list has rented this videoCheck. If the videoCheck is found the system will
     * inform the user that it has been returned and by the cooresponding customer.
     * The customer object is then updated to have no rented videoCheck associated
     * with it.
     *
     * @param v
     * @return will return true if the customer with the associated videoCheck is
     *         found will return false otherwise
     */
    public boolean checkInVideo(video v) {
        tempVideo = containsVideo(v, SLLTotalVideos); // calls the hasVideo method returns the index of customer with
                                                      // assocciated videoCheck
        if (tempVideo != null) // is not a video of our store
        {
            SLLTotalVideos.addFirst(tempVideo); // add to total list tempVideo
            tempVideo = null; // clear the tempVideo
            int i = SLLcustomer.getSize(); // get size of customer list
            if (i != 0) // if there are customers
            {
                while (i != 0) // from i to 0
                {
                    tempCustomer = SLLcustomer.removeFirst(); // tempCustomer is set to the removedFirst value of
                                                              // customer list
                    int j = tempCustomer.getNumberOfRented(); // get the number of videos customer has rented
                    while (j != 0) // while customer has videos
                    {
                        tempVideo = containsVideo(v, tempCustomer.rentedVideo); // tempVideo is set to return of
                                                                                // contains video
                        if (tempVideo != null) // if tempVideo is not null video was found
                        {
                            SLLvideo.addFirst(tempVideo); // re-add the video from customer to store
                            return true; // return true
                        }
                        j--; // decrement j
                    }
                    SLLcustomer.addLast(tempCustomer); // re-add tempCustomer to customer list
                    i--; // decrement i
                }
            } else // video is not being rented
            {
                return false; // return false
            }
        } // video is not in part of store
        return false; // the videoCheck was not out or doesnt exist return false
    }

    public void exit() {
        EXIT = true;
    }
}
