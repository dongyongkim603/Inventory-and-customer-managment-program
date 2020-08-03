import java.util.Scanner;

/**
 *
 * @author John
 */
public class MenuSLL {
    // instance vairables
    boolean EXIT; // the boolean that is checked in runMenu loop to create exit condition

    // class instance object variable of the SinglyLinkedList data structure with
    // type videoCheck. Lists all videos currently in store
    private final SinglyLinkedList<video> SLLvideo = new SinglyLinkedList<>();

    // class instance object variable of the SinglyLinkedList data structure with
    // type customer
    private final SinglyLinkedList<CustomerSLL> SLLcustomer = new SinglyLinkedList<>();

    //// class instance object variable of the SinglyLinkedList data structure with
    //// type videoCheck. List all total videos in store records
    private final SinglyLinkedList<video> SLLTotalVideos = new SinglyLinkedList<>();

    public CustomerSLL tempCustomer = new CustomerSLL(); // used as a temporary CustomerSLL object
    public video tempVideo = new video(); // used as a temporary video object

    // -----------------------menu construction
    // methods------------------------------

    /**
     * default constructor
     */
    public MenuSLL() {
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
     * calls mainMenu and performs loop waiting for input. Input is provided by the
     * call to getInputInt()
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
        return null;
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
                if (key.getId().equals(tempCustomer.getId()) && // if the tempCustomer equals the key
                        key.getName().equals(tempCustomer.getName())) {
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

    // ------------------------------------------------------------------------------

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
     * method allows user to add/create new videoCheck object and add it to the
     * SLLvideo.
     */
    public void addVideo() {
        System.out.println("please enter the video name you wish to add: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to add: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        video video = new video<>(name, id); // creates new videoCheck object from name and id variables
        SLLvideo.addFirst(video); // adds the newly created videoCheck object to the SLLvideo list
        SLLTotalVideos.addFirst(video); // adds the newly created videoCheck object to the SLLTotalVideos list
        System.out.println(video.getId() + " " + video.getTitle() + " has been added!");
    }

    /**
     * allows user to delete videoCheck from list. if it exists the videoCheck name
     * and id is shown if not the user is informed
     */
    public void deleteVideo() {
        System.out.println("please enter the video name you wish to remove: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to remove: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        video video = new video<>(name, id); // creates new videoCheck object from name and id variables
        containsVideo(video, SLLvideo); // calls the containsVideo method to return index of videoCheck if exists if not
                                        // return -1
        tempVideo = containsVideo(video, SLLTotalVideos); // calls the containsVideo method to return index of
                                                          // videoCheck if exists if not return -1
        if (tempVideo != null) {
            System.out.println("the video: " + tempVideo.getTitle() + " " + tempVideo.getId() + " was removed");
        } else {
            System.out.println("the video does not exist"); // the index is negitive therefor the videoCheck does not
                                                            // exist
        }
    }

    /**
     * allows user to add in to data structure SLLcustomer, new customer object
     * calls getInputString method to create String to be passed in constructor of
     * customer object
     * 
     * @return the newly created customer
     */
    public CustomerSLL addCustomer() {
        System.out.println("please enter the customer name you wish to add: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter customer id you wish to add: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        CustomerSLL customer = new CustomerSLL(name, id); // creates a new customer object from name and id variables
        SLLcustomer.addFirst(customer); // adds newly created customer object to SLL customer list
        System.out.println(customer.getName() + " " + customer.getId() + " has been added");
        return customer;
    }

    /**
     * allows the user to delete customer from SLLcustomer list. If the customer
     * exists it makes a call to removeAtN to remove the node and then prints the
     * removed customer else the user is informed no such customer exists
     */
    public void deleteCustomer() {
        System.out.println("please enter the customer name you wish to remove: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter customer id you wish to remove: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        CustomerSLL customer = new CustomerSLL(name, id); // creates new customer object from name and id variables
        tempCustomer = containsCustomer(customer); // calls containsCustomer method to return index of customer if
                                                   // exists if not return -1
        if (tempCustomer != null) {
            System.out.println("Customer: " + name + " " + id + " was removed");
        } else {
            System.out.println("no such customer exists"); // the index is negitive therefore the customer does not
                                                           // exist
        }
    }

    /**
     * checks it the videoCheck exists in the SLLvideo list ie if the videoCheck is
     * in the store prints the name of the videoCheck if found else prints false if
     * it was not
     * 
     * @return the status of videoCheck being in store
     */
    public boolean checkInStore() {
        System.out.println("please enter the video name you wish to check if in store: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to check if in store: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        video video = new video<>(name, id); // creates new customer object from name and id variables
        tempVideo = containsVideo(video, SLLvideo); // calls containsVideo method to return index of videoCheck if
                                                    // exists if not return-1
        if (tempVideo != null) {
            System.out.println("the video " + name + " " + id + " is in the store");
            SLLvideo.addFirst(tempVideo);
            return true; // index is non-negitive therefor the videoCheck exists return true
        } else {
            System.out.println("the video " + name + " " + id + " is not in the store");
            return false; // index is negitive therefore the videoCheck does not exist return false
        }
    }

    /**
     * checks if the videoCheck is in store by passing a videoCheck object as
     * parameters
     * 
     * @param video the videoCheck to be looked for
     * @return true if found false if does not exist
     */
    public boolean checkInStore(video video) {
        tempVideo = containsVideo(video, SLLTotalVideos); // calls the containsVideo method which returns index of
                                                          // videoCheck if exists if not return -1
        if (tempVideo != null) // if tempVideo does not equal null it was found
        {
            SLLTotalVideos.addFirst(tempVideo); // re-add the tempVideo to the total video list
            return true; // the index is non-negitive therefore exists return true
        } else // the index is negitive therefore does not exist return false
        {
            return false; // the index is negitive therefore does not exist return false
        }
    }

    /**
     * prompts the user to enter name and id of customer. This is turned into a
     * CustomerSLL object and passed to the containsCustomer method to see if such
     * an object exists.
     * 
     * @return true if customer is found else false
     */
    public boolean checkCustomer() {
        System.out.println("please enter the customer name you wish to check if member: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to check if member: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        CustomerSLL customer = new CustomerSLL(name, id); // creates new customer object from name and id variables
        tempCustomer = containsCustomer(customer); // calls containsCustomer method which sets int i to the index of
                                                   // customer in list -1 if not
        if (tempCustomer != null) {
            System.out.println("the cusomter " + name + " " + id + " already is a memeber ");
            SLLcustomer.addFirst(tempCustomer); // re-add the customer to the list
            return true; // index is non-negitive therefore the customer is in list return true
        } else {
            System.out.println(name + " " + id + " is not a member yet");
            return false; // index is negitive therefore the customer does not exist return false
        }
    }

    /**
     * allows the user to rent videos to customers. will first ask user to enter
     * videoCheck name and id. It will look to see if the videoCheck exists in the
     * store or SLLvideo list if it does it will then ask user to enter customer
     * information else it will inform the user the videoCheck is not in the store.
     * If the customer is a member it will use the created customer object with the
     * an update that sets the videoCheck object to the customer. It will then
     * access the list and replace the old customer element with the new one that
     * has the rented videoCheck update.
     * 
     * @return true if videoCheck is in store and customer is member or has been
     *         added else will return false if the videoCheck is not in the store
     */
    public boolean checkOutVideo() {
        System.out.println("please enter the video name you wish to checkout: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to checkout: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        video video = new video<>(name, id); // creates new videoCheck object from the name an id varibles
        tempVideo = containsVideo(video, SLLvideo); // creates an integer of index of the videoCheck object if found -1
                                                    // if not
        if (tempVideo != null) {
            System.out.println("please enter the customer name you wish to checkout: ");
            String customerName = getInputString().toUpperCase(); // calls getInoutString method and converts to all
                                                                  // uppercase
            System.out.println("please enter customer id you wish to checkout: ");
            String customerId = getInputString().toUpperCase(); // calls getInoutString method and converts to all
                                                                // uppercase
            CustomerSLL customer = new CustomerSLL(customerName, customerId); // creates new customer object from
                                                                              // customerName and customerId
            tempCustomer = containsCustomer(customer); // creates int k by calling containsCustomer which returns index
                                                       // of customer or -1 if not
            while (tempCustomer == null) // if not enters loop to create new customer to add to SLLcustomer
            {
                System.out.println("Customer is not yet a member");
                customer = addCustomer(); // call to addCustomer method
                tempCustomer = containsCustomer(customer); // will reset k to a != -1
            }
            tempCustomer.setRentVideo(video); // sets the videoCheck object in customer feild to the videoCheck object
            SLLcustomer.addFirst(tempCustomer); // updates the customer object in SLLcustomer list
            System.out.println(customer.getName() + " is now renting " + name);
            return true; // returns true
        } else {
            System.out.println(name + " is not in the store");
            return false; // returns false if the videoCheck does not exist
        }
    }

    /**
     * allows the user to enter the name and id of a videoCheck to create a
     * videoCheck object this videoCheck object is to check if any customer in the
     * list has rented this videoCheck. If the videoCheck is found the system will
     * inform the user that it has been returned and by the coresponding customer.
     * The customer object is then updated to have no rented videoCheck associated
     * with it.
     * 
     * @return will return true if the customer with the associated videoCheck is
     *         found will return false otherwise
     */
    public boolean checkInVideo() {
        System.out.println("please enter the video name you wish to check in: ");
        String name = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        System.out.println("please enter video id you wish to check in: ");
        String id = getInputString().toUpperCase(); // calls getInoutString method and converts to all uppercase
        video video = new video(name, id); // creates new videoCheck object from name and id variables
        tempVideo = containsVideo(video, SLLTotalVideos); // calls the hasVideo method returns the index of customer
                                                          // with assocciated videoCheck
        if (tempVideo != null) // is not a video of our store
        {
            SLLTotalVideos.addFirst(tempVideo); // add tempVideo to the first of total videos
            tempVideo = null; // clear the tempVideo
            int i = SLLcustomer.getSize(); // get the size of the customer list
            if (i != 0) // if there are customers
            {
                while (i != 0) // from number of customers to 0
                {
                    tempCustomer = SLLcustomer.removeFirst(); // set tempCustomer to the removed first customer in list
                    int j = tempCustomer.getNumberOfRented(); // get the number of videos the customer has rented
                    while (j != 0) // while customer has videos
                    {
                        tempVideo = containsVideo(video, tempCustomer.rentedVideo);// set tempVideo to the return of the
                                                                                   // call to containsVideo
                        if (tempVideo != null) // if tempVideo is not null video was found
                        {
                            SLLvideo.addFirst(tempVideo); // return the video to the store by adding it to the store
                                                          // video list
                            System.out.println("the video " + video.getTitle() + " has been returned");
                            return true; // video was returned return true
                        }
                    }
                    SLLcustomer.addLast(tempCustomer); // customer did not have video re-add to end of list
                    i--; // decrement i
                }
            } else // video is not being rented
            {
                System.out.println("no one is renting this video");
                return false; // video is not being rented
            }
        } // video is not in part of store
        System.out.println("this video is not one of ours");
        return false; // the videoCheck was not out or doesnt exist return false
    }

    /**
     * iterates through the list of customers and prints the number of customers
     * their names and id numbers will print a notice if the list is empty
     */
    public void printAllCustomers() {
        int i = SLLcustomer.getSize(); // the size of the list
        if (i > 0) // if there are customers
        {
            System.out.println("there are " + i + " customer members");
            for (int j = 0; i > j; j++) // for j at 0 to the number of customers
            {
                tempCustomer = SLLcustomer.removeFirst(); // removes first customer tempCustomer is set to return
                System.out.println("Customer Name: " + tempCustomer.getName() + " Customer Id:" + tempCustomer.getId());
                SLLcustomer.addLast(tempCustomer); // re-add customer to list
            }
        } else {
            System.out.println("there are no customers!"); // list is empty
        }

    }

    /**
     * iterates through the list of videos and prints the number of videos that are
     * currently in the store their names and id numbers will print a notice if the
     * list is empty
     */
    public void printALLInStoreVideos() {
        int i = SLLvideo.getSize(); // the size of the list
        if (i > 0) // if there are videos in store list
        {
            System.out.println("there are " + i + " videos in the store");
            for (int j = 0; i > j; j++) {
                tempVideo = SLLvideo.removeFirst(); // removes first item in SLLvideo and sets return to tempVideo
                System.out.println("Video title: " + tempVideo.getTitle() + " video id: " + tempVideo.getId());
                SLLvideo.addLast(tempVideo); // re-adds the video to the end of list
            }
        } else {
            System.out.println("There are no videos currently in the store!!"); // list is empty
        }
    }

    /**
     * iterates through the list of videos and prints the number of videos their
     * names and id numbers will print a notice if the list is empty
     */
    public void printAllVideos() {
        int i = SLLTotalVideos.getSize(); // the size of the list
        if (i > 0) // SLLTotalVideos is non-empty
        {
            System.out.println("there are " + i + " videos in the store database");
            for (int j = 0; i > j; j++) {
                tempVideo = SLLTotalVideos.removeFirst(); // removes first video in total video list and sets videoTemp
                                                          // as return value
                System.out.println("Video title: " + tempVideo.getTitle() + " video id: " + tempVideo.getId());
                SLLTotalVideos.addLast(tempVideo); // re-adds the video to list
            }
        } else // no videos in list
        {
            System.out.println("There are no videos in the store database!!"); // list is empty
        }
    }

    /**
     * prints out all of the videos currently being rented by all of the customers.
     * if there are no customers return. else go through the SLLcustomer list. then
     * for each customer go through rentedVideo list. if there are videos print name
     * + id then move to next node else if there are no videos for customer move to
     * next node in customer list. counter is incremented for each found video and
     * is printed at end.
     */
    public void printAllRentedVideo() {
        int counter = 0; // number of rented videos
        if (SLLcustomer.isEmpty()) // if there are no customers
        {
            System.out.println("there are no customers in database");
            return;
        }
        int i = SLLcustomer.getSize(); // number of customers in SLLcustomer list
        while (i != 0) // while the customer index is pointing at customer
        {
            tempCustomer = SLLcustomer.removeFirst(); // sets tempCustomer to returned value of removeFirst of customer
                                                      // list
            if (tempCustomer.numberOfRented != 0) // customer has rented videos
            {
                int j = tempCustomer.getNumberOfRented(); // size of videos in rentedVideo list
                while (j != 0) // while the rentedVideo index is pointin at video
                {
                    tempVideo = tempCustomer.rentedVideo.removeFirst(); // removes first video from customer SLL
                    counter++; // increment counter
                    System.out.println("Title: " + tempVideo.getTitle() + " ID:" + tempVideo.getId()); // print
                                                                                                       // information
                    tempCustomer.rentedVideo.addLast(tempVideo); // re-add video to customer
                    j--; // decrement rentedVideo index
                }
                SLLcustomer.addLast(tempCustomer); // re-add customer to list
                i--; // decrement customer index
            } else {
                SLLcustomer.addLast(tempCustomer); // re-add customer to list
                i--; // decrement customer index
            }
        }
        System.out.println(" are currently " + counter + " videos rented by all customers");
    }

    /**
     * prompts user to enter name and id of a customer which will create new
     * CustomerSLL object. This is passed to containsCustomer method which will
     * return the index of the customer in the SLLcustomer list and a -1 if no such
     * customer exists. the customer in the returned index is removed and the return
     * value is set to the local customer variable. If the customer has
     * numberOfRentals greater than 0 a loop is used to print the videos. else the
     * user is told that the customer is not renting any videos currently.
     */
    public void printCustomerRental() {
        int i = SLLcustomer.getSize(); // get size of the customer list
        if (i != 0) // if not empty
        {
            System.out.println("please enter the customer name you wish to check rentals: ");
            String customerName = getInputString().toUpperCase(); // calls getInoutString method and converts to all
                                                                  // uppercase
            System.out.println("please enter customer id you wish to check rentals: ");
            String customerId = getInputString().toUpperCase(); // calls getInoutString method and converts to all
                                                                // uppercase
            CustomerSLL customer = new CustomerSLL(customerName, customerId); // creates new customer object from
                                                                              // customerName and customerId
            tempCustomer = containsCustomer(customer); // set tempCustomer to the return of the containsCustomer call
            if (tempCustomer != null) // if tempCustomer is not null it was found
            {
                int j = tempCustomer.getNumberOfRented(); // get the number of videos rented by the customer
                if (j != 0) // if customer has videos rented
                {
                    while (j != 0) // from number of rented to 0
                    {
                        tempVideo = tempCustomer.rentedVideo.removeFirst(); // set tempVideo to the first video in
                                                                            // customer rented list
                        System.out.println("Video Title: " + tempVideo.getTitle() + " Video ID: " + tempVideo.getId());
                        tempCustomer.rentedVideo.addLast(tempVideo); // re-add the video to the list
                        j--; // decrement j
                    }
                } else // else there are no rented videos by customer
                {
                    System.out.println("this Customer is not renting any videos yet! ");
                }
            } else // else customer is not a memeber
            {
                System.out.println("This customer it not a member of our store yet!");
            }
        } else // else there the customer list is empty
        {
            System.out.println("There are customers yet!!");
        }
    }

    /**
     * sets instance variable EXIT to true allowing exit condition of runMenu while
     * loop
     */
    public void exit() {
        EXIT = true;
    }
}
