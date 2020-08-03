import java.util.Scanner;

/**
 * A Menu class that utilizes AVL trees to hold values in video store
 * 
 * @author John
 */
public class MenuAVL {
    // instance vairables
    boolean EXIT; // the boolean that is checked in runMenu loop to create exit condition

    // class instance object variable of the DoublyLinkedList data structure with
    // type videoCheck. Lists all videos currently in store
    private final AVLTreeMap<String, video> AVLvideo = new AVLTreeMap<>();

    // class instance object variable of the DoublyLinkedList data structure with
    // type customer
    private final AVLTreeMap<String, CustomerAVL> AVLcustomer = new AVLTreeMap<>();

    //// class instance object variable of the DoublyLinkedList data structure with
    //// type videoCheck. List all total videos in store records
    private final AVLTreeMap<String, video> AVLTotalVideos = new AVLTreeMap<>();

    protected video comparatorVideo = new video(); // used as a temporary video object
    protected CustomerAVL comparatorCustomer = new CustomerAVL(); // used as a temporary CustomerAVL object

    // -----------------------menu
    // constructions-------------------------------------

    /**
     * default Constructor
     */
    public MenuAVL() {
    }

    /**
     * calls mainMenu and performs loop waiting for input. Input is provided by the
     * call to getInputInt()
     */
    public void runMenu() {
        while (!EXIT) {
            mainMenu();
            int choice = getInputInt();
            performAction(choice);
        }
    }

    /**
     * prints all the options for the user.
     */
    private void mainMenu() {
        System.out.println("\n Enter one of the following");
        System.out.println(" 1: To add a video");
        System.out.println(" 2; To delete a video");
        System.out.println(" 3: To add a customer");
        System.out.println(" 4: To delete a customer");
        System.out.println(" 5: To Check if a particular video is in store");
        System.out.println(" 6: To check out a video");
        System.out.println(" 7: to check in a video");
        System.out.println(" 8: To print all customers");
        System.out.println(" 9: To print all videos");
        System.out.println(" 10: to print in store videos");
        System.out.println(" 11: To print all rented videos");
        System.out.println(" 12: To print the videos rented by customer");
        System.out.println(" 13: To exit");
    }

    /**
     * takes in input from scanner System.in reads data as String. String is then
     * parsed and wrapped as an integer which is then passed to choice
     * 
     * @return integer choice
     */
    private int getInputInt() {
        int choice = -1; // create choice integer set to -1
        Scanner kb = new Scanner(System.in); // create new scanner object with system.in as parameter
        while (choice < 0 || choice > 13) {
            try // may throw exception if a non-Integer is passed in
            {
                System.out.println("\n enter number: ");
                choice = Integer.parseInt(kb.nextLine()); // takes in keyboard input and sets choice to the return input
            } catch (NumberFormatException e) // invalid format passed in scanner object
            {
                System.out.println("invalid selection try agian");
            }
        }
        return choice; // return choice int
    }

    /**
     * takes in input from scanner System.in reads data as String.
     * 
     * @return integer choice
     */
    private String getInputString() {
        String choice = ""; // initalize blank string
        Scanner kb = new Scanner(System.in); // create new scanner object with system.in as parameter
        while (choice.equals("")) // if choice is empty continue to call nextLine method
        {
            choice = kb.nextLine(); // takes in keyboard input from scanner
        }
        if (choice.equals("")) // checks to ensure the choice is a non-empty String
        {
            getInputString(); // recursively calls method if choice is empty
        }
        return choice; // return choice
    }

    // ------------------------helper search
    // methods---------------------------------

    /**
     * takes in the user input and does switch comparison to see which methods to
     * make a call to.
     * 
     * @param choice as an integer
     */
    private void performAction(int choice) {
        switch (choice) {
            case 0:
                EXIT = true;
                System.out.print("case 0");
                break;
            case 1:
                addVideo();
                break;
            case 2:
                deleteVideo();
                break;
            case 3:
                addCustomer();
                break;
            case 4:
                deleteCustomer();
                break;
            case 5:
                checkInStore();
                break;
            case 6:
                checkOutVideo();
                break;
            case 7:
                checkInVideo();
                break;
            case 8:
                printAllCustomers();
                break;
            case 9:
                printAllVideos();
                break;
            case 10:
                printALLInStoreVideos();
                break;
            case 11:
                printAllRentedVideo();
                break;
            case 12:
                printCustomerRental();
                break;
            case 13:
                exit();
                break;
            default:
                System.out.println("An unknown error has occurred");
        }
    }

    // ------------------------user option
    // methods-----------------------------------

    /**
     * allows user to add video object to AVLTreeMap by calling the getInputString
     * method to create name and id String variables and constructing video object.
     * This object is then passed to the put method where it will add video to tree
     */
    public void addVideo() {
        System.out.println("please enter the video name you wish to add: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to add: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        video video = new video<>(name, id); // creates new videoCheck object from name and id variables
        AVLvideo.put(id, video); // puts video object in in-store tree at key of video id
        AVLTotalVideos.put(id, video); // puts video object in total video tree at key of video id
        System.out.println(video.getId() + " " + video.getTitle() + " has been added!");
    }

    /**
     * allows user to add video object to AVLTreeMap by calling the getInputString
     * method to create name and id String variables and constructing video object.
     * This object is then passed to the get method which will use the String id of
     * the video object as a key to look for the match. comparator is the returned
     * video object null if not found. This object is then compared to a null value
     * and if null the user is informed the video does not exist if not equal to
     * null the id key is used to remove from both AVLvideo and AVLTotalVideos
     */
    public void deleteVideo() {
        System.out.println("please enter the video name you wish to remove: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to remove: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        comparatorVideo = AVLTotalVideos.get(id); // uses get method to look for matching video in tree
        if (comparatorVideo != null) // if the video was found comparator will equal video
        {
            AVLvideo.remove(id); // remove the node at video id in AVLvideo
            AVLTotalVideos.remove(id); // remove the node at video ide in AVLTotalVideos
            System.out.println("the video: " + name + " " + id + " was removed");
        } else // else the video was not found therefore comparator will not equal video
        {
            System.out.println("no such video exists");
        }
    }

    /**
     * allows user to add in to data structure AVLcustomer, new customer object
     * calls getInputString method to create String to be passed in constructor of
     * customer object
     * 
     * @return the newly added CustomerAVL object
     */
    public CustomerAVL addCustomer() {
        System.out.println("please enter the customer name you wish to add: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter customer id you wish to add: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        CustomerAVL customer = new CustomerAVL(name, id); // creates a new customer object from name and id variables
        AVLcustomer.put(id, customer); // adds the newly created customer object to the AVLcustomer tree
        System.out.println(customer.getName() + " with ID: " + customer.getId() + " is now a memeber!");
        return customer;
    }

    /**
     * allows the user to delete customer from AVLcustomer tree. If the customer
     * exists the comparator CustomerAVL object will not be null since it was found
     * by calling the get method with customerId as parameters. If it is found the
     * customer is removed from tree if it is not the user is notified
     */
    public void deleteCustomer() {
        System.out.println("please enter the customer name you wish to remove: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter customer id you wish to remove: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        comparatorCustomer = AVLcustomer.get(id); // calls the get methods with customer id as parameters to create new
                                                  // CustomerAVL object
        if (comparatorCustomer == null) // if the customer was not found in get method call it will be a null customer
                                        // object
        {
            System.out.println("no such customer exists!"); // was not found
        } else // else the customer was found
        {
            AVLcustomer.remove(id); // the customer is then removed from the tree
            System.out.println("the Customer: " + name + " " + id + " was removed");
        }
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
    public boolean checkInStore() {
        System.out.println("please enter the video name you wish to check if in store: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to check if in store: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        comparatorVideo = AVLvideo.get(id); // uses get method to look for matching video in tree
        if (comparatorVideo != null) // if the video was found comparator will equal video
        {
            System.out.println("the video: " + name + " " + id + " is in store");
            return true;
        } else // else the video was not found therefore comparator will not equal video
        {
            System.out.println("is not in the store");
            return false;
        }
    }

    /**
     * allows the user to enter a customer name and id. these values are turned into
     * Strings variables. The id variable is passed to the get method which checks
     * the AVLcustomer tree for a matching key value. A new customer object is
     * created from the get methods return value. If the comparator value is not
     * null the user is informed the customer exists else the user is told they do
     * not.
     * 
     * @return true if customer is a member
     */
    public boolean checkCustomer() {
        System.out.println("please enter the customer name you wish to check if member: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter customer id you wish to check if member: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        comparatorCustomer = AVLcustomer.get(id); // calls the get methods with customer id as parameters to create new
                                                  // CustomerAVL object
        if (comparatorCustomer != null) // if the comparatorCustomer has value
        {
            System.out.println("The customer: " + name + " is a member!");
            return true; // customer was found and is a member return true
        } else // else the comparatorCustomer has no value
        {
            System.out.println("The customer: " + name + " is not yet a member!");
            return false; // customer was not found return false
        }
    }

    /**
     * allows user to rent movies to customers. inStore is boolean of checkInStore
     * method. If it returns false the video cannot be rented and the user is
     * informed return false. If inStore is true then a boolean customerInStore is
     * created from the return value of checkCustomer method. If false the
     * addCustomer method is invoked and the user is told to try the rental again
     * return false. if customerInStore is true a new CustomerAVL object is created
     * by removing the matching customer entry from BSTcustomer tree. A video object
     * outVid is then created by removing the corresponding entry from the BSTvideo
     * tree. the outVid is then added to the customerBST and then the customer is
     * added back into the AVLcustomer tree return true.
     * 
     * @return true if both video and customer are in store.
     */
    public boolean checkOutVideo() {
        boolean inStore = checkInStore(); // Boolean created by invoking checkInStore method
        if (inStore != true) // if video is not in store
        {
            return false;
        } else // else video is in store
        {
            boolean customerInStore = checkCustomer(); // Boolean created by invoking the checkCustomer method
            if (customerInStore == false) // customer is not a member
            {
                addCustomer(); // invoke addCustomer method
                System.out.println("try to rent video again now that customer is member");
                return false;
            } else // customer is a member
            {
                CustomerAVL hold = AVLcustomer.remove(comparatorCustomer.getId());// creates new CustomerBST object by
                                                                                  // removing matching object from tree
                video outVid = AVLvideo.remove(comparatorVideo.getId()); // creates new video object by removing
                                                                         // matching oject from BSTvideo
                hold.numberOfRented++; // increment numberOfRented
                hold.rentedVideo.put(comparatorVideo.getId(), outVid); // adds video to the customer
                AVLcustomer.put(hold.getId(), hold); // re-adds the customer back to the tree
                System.out.println(hold.getName() + " is now renting " + outVid.getTitle());
                return true;
            }
        }
    }

    /**
     * Allows the user to enter the title and an id and return the video if it is
     * being rented. It first uses the name and id String variables to update the
     * comparatorVideo variable. The AVLcustomer tree is checked if empty return
     * false if not the int j will bet set to the size of the AVLcustomer tree.
     * While j does not equal 0. and Entry object is made and a String key is set
     * from entry key. The comparatorCustomer variable is set to the customer at
     * key. if the customerComparator rentedVideo tree .get returns a non-null
     * value, comparatorVideo is set to the the removed video from the customer. The
     * video is then put into the AVLvideo list. The user is informed. If the loop
     * is exited the video is not being rented. return false
     * 
     * @return true if customer in AVLcustomer has video false if no customers or
     *         the video is not being rented
     */
    public boolean checkInVideo() {
        System.out.println("please enter the name of the video you wish to check in: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter the id of the video you want to check in: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        comparatorVideo.setId(id); // sets the id of the comparatorVideo variable
        comparatorVideo.setTitle(name); // sets the name of the comparatorVideo variable
        if (AVLcustomer.isEmpty()) // if there are no customers
        {
            System.out.println("there are no customers in database");
            return false;
        } else // there are customers
        {
            int j = AVLcustomer.size(); // gets the size of the AVLcustomer tree
            while (j != 0) // while there are customers to be checked
            {
                Entry entry = AVLcustomer.firstEntry(); // gets the entry of key with least value
                String key = entry.getKey().toString(); // String of the current key
                comparatorCustomer = AVLcustomer.get(key); // sets the comparatorCustomer to the customer at key in tree
                if (null != comparatorCustomer.rentedVideo.get(id)) // if the customer has the mataching id of the video
                {
                    comparatorVideo = comparatorCustomer.rentedVideo.remove(id);// sets the comparatorVideo variable to
                                                                                // the returned removed video from
                                                                                // customer
                    AVLvideo.put(comparatorVideo.getId(), comparatorVideo); // re-adds the video to the AVLvideo list ie
                                                                            // the video is checked in
                    System.out.println("the video " + comparatorVideo.getTitle() + " has been returned");
                    return true;
                } else // the video was not checkout by this customer
                {
                    entry = AVLcustomer.higherEntry(key); // the entry variable is set to next highest key value
                    j--; // decrement j
                }
            }
            System.out.println("the video is not being rented");
            return false; // the video was not found to be with any customer
        }
    }

    /**
     * Will print a list of all the customers and their Id's. If the BSTcustomer
     * tree is empty the user is informed. If AVLcustomer is not empty, The tree is
     * traversed from lowest key value to greatest and each customer is printed.
     */
    public void printAllCustomers() {
        if (AVLcustomer.isEmpty()) // the AVLcustomer tree is empty
        {
            System.out.println("Oh NO! There are no customers!!!");
        } else // AVLcustomer is non-empty
        {
            int i = AVLcustomer.size(); // i is set to the size of the tree
            System.out.println("there are " + i + " customers"); // prints the number of customers
            Entry entry = AVLcustomer.firstEntry(); // gets entry of lowest key value
            while (i != 0) // while there are nodes to be traversed
            {
                String key = entry.getKey().toString(); // String of the entry key value
                comparatorCustomer = AVLcustomer.get(key); // set the comparatorCustomer variable to customer in tree at
                                                           // value key
                String name = comparatorCustomer.getName(); // sets name variable to customer name
                String id = comparatorCustomer.getId(); // sets id variable to customer id
                System.out.println("Customer name: " + name + " ID: " + id);
                entry = AVLcustomer.higherEntry(key); // sets the entry to the next highest key value
                i--; // decrement i
            }
        }
    }

    /**
     * Will print a list of all the videos and their Id's in store. If the BSTvideo
     * tree is empty the user is informed. If AVLvideo is not empty, The tree is
     * traversed from lowest key value to greatest and each video is printed.
     */
    public void printALLInStoreVideos() {
        if (AVLvideo.isEmpty()) // there are no videos in store
        {
            System.out.println("There are current;y no videos in the store");
        } else // AVLvideo is a non-empty tree
        {
            int i = AVLvideo.size(); // i is set the size of the tree
            Entry entry = AVLvideo.firstEntry(); // an entry object is set to the lowest key value
            System.out.println("There are " + i + " currently in the store");
            while (i != 0) // while there are nodes to traverse in tree
            {
                String key = entry.getKey().toString(); // String key is set to the current entry key
                comparatorVideo = AVLvideo.get(key); // the comparatorVideo variable is set to referenece the video at
                                                     // key
                String title = comparatorVideo.getTitle(); // String title is the title of the referenced video
                String id = comparatorVideo.getId(); // String id is the id of the refereced video
                System.out.println("Title: " + title + " ID: " + id);
                entry = AVLvideo.higherEntry(key); // the entry is set the entry with the next higest key value
                i--; // decrement i
            }
        }
    }

    /**
     * Will print a list of all the videos and their Id's in store database. If the
     * AVLTotalVideos tree is empty the user is informed. If AVLTotalVideo is not
     * empty, The tree is traversed from lowest key value to greatest and each video
     * is printed.
     */
    public void printAllVideos() {
        if (AVLTotalVideos.isEmpty()) // there are no videos in database
        {
            System.out.println("There are no videos in the store database!!");
        } else // AVLTotalVideos is non-empty
        {
            int i = AVLTotalVideos.size(); // i is set to the size of tree
            Entry entry = AVLTotalVideos.firstEntry(); // entry is set to the lowest key value
            System.out.println("There are " + i + " videos in store database");
            while (i != 0) // while there are nodes to be traversed
            {
                String key = entry.getKey().toString(); // String key is set to current entry key
                comparatorVideo = AVLTotalVideos.get(key); // sets the comparatorVideo to reference entry at key
                String title = comparatorVideo.getTitle(); // String title is set to video title
                String id = comparatorVideo.getId(); // String id is set to video id
                System.out.println("Title: " + title + " ID: " + id);
                entry = AVLTotalVideos.higherEntry(key); // entry is updated to entry with next greates key value
                i--; // decrement i
            }
        }
    }

    /**
     * Will print a list of all the videos that are rented by customers. If the
     * AVLTotalVideos tree is empty the user is informed. If there are no customers
     * in the BSTcustomer list the user is informed. Else the AVLcustomer tree is
     * traversed from key with lowest value to greatest. The each iteration of the
     * customer is checked for if they are renting videos. If there numberOfRented
     * is not equal to 0, This tree is also traversed from by lowest key to greatest
     * key. The videos are printed.
     */
    public void printAllRentedVideo() {
        if (AVLcustomer.isEmpty()) // there are no customers
        {
            System.out.println("There are no customers to be able to rent any videos!");
        } else if (AVLTotalVideos.isEmpty()) // there are no videos in database
        {
            System.out.println("There are no videos in the database to rent!!");
        } else if (AVLTotalVideos.size() == AVLvideo.size()) {
            System.out.println("None of our videos are being rented!"); // The number of videos in store matches
                                                                        // database therefore none are out
        } else // there are customers and videos and videos out of the store
        {
            int i = AVLcustomer.size(); // i is set to the size of the tree
            System.out.println("there are " + i + " customers \n"); // prints the number of customers
            Entry entry = AVLcustomer.firstEntry(); // gets entry of lowest key value
            while (i != 0) // while there are nodes to be traversed
            {
                String key = entry.getKey().toString(); // String of the entry key value
                comparatorCustomer = AVLcustomer.get(key); // set the comparatorCustomer variable to customer in tree at
                                                           // value key
                String name = comparatorCustomer.getName(); // sets name variable to customer name
                String id = comparatorCustomer.getId(); // sets id variable to customer id
                int j = comparatorCustomer.getNumberOfRented(); // j is set to the numberOfRented in customer field
                if (j != 0) // customer has videos
                {
                    System.out.println("Customer Name: " + name + " Customer ID: " + id);
                    Entry vidEntry = comparatorCustomer.rentedVideo.firstEntry();// vidEntry is set to the lowest key
                                                                                 // value
                    while (j != 0) // while there are nodes to be traversed
                    {
                        String vidKey = vidEntry.getKey().toString(); // String vidKey is set to the key in vidEntry
                        comparatorVideo = comparatorCustomer.rentedVideo.get(vidKey);// comparatorVideo variable is set
                                                                                     // to the video matching vidKey
                        String title = comparatorVideo.getTitle(); // String of video title
                        String vidId = comparatorVideo.getId(); // String of video id
                        System.out.println("Video Title: " + title + " Video ID " + id);
                        vidEntry = comparatorCustomer.rentedVideo.higherEntry(vidKey);// vidEntry is set to next entry
                                                                                      // with next highest key
                        j--; // decrement j
                    }
                    entry = AVLcustomer.higherEntry(key); // sets the entry to the next highest key value
                    i--;
                } else // no videos in customer found
                {
                    entry = AVLcustomer.higherEntry(key); // sets the entry to the next highest key value
                    i--; // decrement i
                }
            }
        }
    }

    /**
     * Print the videos associated with the specified customer. The system will only
     * print videos if there are customers in database, there are videos in
     * database, there are less videos in the AVLvideo tree than the AVLTotalVideo
     * tree, and the call to checkCustomer method returns true. The method call
     * updates the comparatorCustomer variable to the target customer. from there if
     * the customer has numberOfRented greater than 0, the tree is traversed from
     * key with least value to key with greatest value the videos are printed as
     * they are found
     */
    public void printCustomerRental() {
        if (AVLcustomer.isEmpty()) // Customer tree is empty
        {
            System.out.println("There are no customers!!!");
        } else if (AVLTotalVideos.isEmpty()) // total video tree is empty therefore no videos to rent
        {
            System.out.println("There are no videos in database to rent!!"); // videos in equal videos total therefore
                                                                             // none are out
        } else if (AVLvideo.size() == AVLTotalVideos.size()) {
            System.out.println("There are no videos currently being rented!!");
        } else {
            if (true == checkCustomer()) // method call allows comparatorCustomer to be changed to targer customer
            {
                String id = comparatorCustomer.getId(); // String of target customer id
                comparatorCustomer = AVLcustomer.get(id); // resets the comparator customer to reference the target in
                                                          // tree
                String name = comparatorCustomer.getName(); // String of target customer name
                String custId = comparatorCustomer.getId(); // String of target customer id
                int i = comparatorCustomer.getNumberOfRented(); // number of videos customer has rented
                System.out.println("Customer: " + name + " ID: " + custId + " is renting " + i + " videos");
                if (i != 0) // if customer videos rented
                {
                    Entry entry = comparatorCustomer.rentedVideo.firstEntry(); // create entry object that finds first
                                                                               // entry of rentedVideo
                    while (i != 0) {
                        String key = entry.getKey().toString(); // String key is key of entry
                        comparatorVideo = comparatorCustomer.rentedVideo.get(key);// comparatorVideo set to reference
                                                                                  // entry key
                        String title = comparatorVideo.getTitle(); // String of title of video
                        String vidId = comparatorVideo.getId(); // String of Id of video
                        System.out.println("Video Title: " + title + " Video ID: " + vidId);
                        entry = comparatorCustomer.rentedVideo.higherEntry(key);// entry object is set to next highest
                                                                                // entry
                        i--; // decrement i
                    }
                } else // else customer has no videos
                {
                    System.out.println(comparatorCustomer.getName() + " does not have any vidoes rented");
                }
            } else // customer does not exist
            {
                System.out.println("Please try again with valid customer");
            }
        }
    }

    /**
     * sets the EXIT boolean to true to exit runMenu loop
     */
    public void exit() {
        EXIT = true;
    }
}
