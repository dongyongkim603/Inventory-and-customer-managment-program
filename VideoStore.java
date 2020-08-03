/**
 * A implementation of the video store project
 * 
 * @author John
 */
public class VideoStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length <= 1 && args[0].equals("SLL")) // if one argument passed in is SLL
            {
                System.out.println("now running the SLL version of video store: ");
                MenuSLL menu = new MenuSLL(); // creates new MenuSLL
                menu.runMenu(); // run the store program
            } else if (args.length <= 1 && args[0].equals("DLL")) // else if one argument passed in is DLL
            {
                System.out.println("now running the DLL version of video store: ");
                MenuDLL menu = new MenuDLL(); // create new MenuDLL
                menu.runMenu(); // run the store program
            } else if (args.length <= 1 && args[0].equals("BST")) // else if one argument passed in is BST
            {
                System.out.println("now running the BST version of video store");
                MenuBST menu = new MenuBST(); // create new MenuBST
                menu.runMenu(); // run the store program
            } else if (args.length <= 1 && args[0].equals("AVL")) // else if one argument passed in is AVL
            {
                System.out.println("now running the AVL version of the video store");
                MenuAVL menu = new MenuAVL(); // create new MenuAVL
                menu.runMenu(); // run the store program
            } else if (args.length > 1 && args[0].equals("SLL")) // else if more than one argument passed in and 1st arg
                                                                 // is SLL
            {
                int vids = Integer.parseInt(args[1]); // parse String convert to int and create new vid int
                int custs = Integer.parseInt(args[2]); // parse String convert to in and create new custs int
                int actions = Integer.parseInt(args[3]); // parse String convert to in and create new actions int
                SLLAnalysis SLL = new SLLAnalysis(vids, custs, actions); // create new SLLAnalysis object pass in
                                                                         // variables
                SLL.runMenu(); // initialize runMenu which generate random requests
                SLL.runRequests(); // initalize the runRequests which processes requests from queue
            } else if (args.length > 1 && args[0].equals("DLL")) // else if more than one argument passed in and 1st arg
                                                                 // is DLL
            {
                int vids = Integer.parseInt(args[1]); // parse String convert to int and create new vid int
                int custs = Integer.parseInt(args[2]); // parse String convert to in and create new custs int
                int actions = Integer.parseInt(args[3]); // parse String convert to in and create new actions int
                DLLAnalysis DLL = new DLLAnalysis(vids, custs, actions); // create new DLLAnalysis object pass in
                                                                         // variables
                DLL.runMenu(); // initialize runMenu which generate random requests
                DLL.runRequests(); // initalize the runRequests which processes requests from queue
            } else if (args.length > 1 && args[0].equals("BST")) // else if more than one argument passed in and 1st arg
                                                                 // is BST
            {
                int vids = Integer.parseInt(args[1]); // parse String convert to int and create new vid int
                int custs = Integer.parseInt(args[2]); // parse String convert to in and create new custs int
                int actions = Integer.parseInt(args[3]); // parse String convert to in and create new actions int
                BSTAnalysis BST = new BSTAnalysis(vids, custs, actions); // create new BSTAnalysis object pass in
                                                                         // variables
                BST.runMenu(); // initialize runMenu which generate random requests
                BST.runRequests(); // initalize the runRequests which processes requests from queue
            } else if (args.length > 1 && args[0].equals("AVL")) // else if more than one argument passed in and 1st arg
                                                                 // is AVL
            {
                int vids = Integer.parseInt(args[1]); // parse String convert to int and create new vid int
                int custs = Integer.parseInt(args[2]); // parse String convert to in and create new custs int
                int actions = Integer.parseInt(args[3]); // parse String convert to in and create new actions int
                AVLAnalysis AVL = new AVLAnalysis(vids, custs, actions); // create new AVLAnalysis object pass in
                                                                         // variables
                AVL.runMenu(); // initialize runMenu which generate random requests
                AVL.runRequests(); // initalize the runRequests which processes requests from queue
            } else // else none or wrong parameters were passed in
            {
                System.out.println("please enter arguments");
                System.out.println(args[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) // will catch ArrayIndexOutOfBoundsException if no parameters are
                                                   // passed in
        {
            System.out.println("no arguments");
            DLLAnalysis test = new DLLAnalysis(100, 100, 1000);
            test.runMenu();
            test.runRequests();
        }
    }

}
