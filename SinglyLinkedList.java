/**
 * An implementation of the SinglyLinkedList data structure. The structure uses
 * generic type data to allow for more adaptable usage.
 * 
 * @author John
 * @param <E> a generic data type
 */
public class SinglyLinkedList<E> {

    // -------------------nested node class-----------------------------------------

    /**
     * nested node class for the SinglyLinkedList class
     * 
     * @param <E> where E is the real data
     */
    public static class Node<E> {
        public E element;
        public Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

    }
    // ----------------end of nested class-------------------------------------//

    // instance variiables of the SinglyLinkedList
    protected Node<E> head = null;
    protected Node<E> tail = null;
    protected int size;

    // defualt constructor
    public SinglyLinkedList() {

    }

    // access methods
    public int size() {
        return size;
    }

    /**
     * @return returns true is size of list is 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * reads the data if the first element in the list
     * 
     * @return real data of head node
     */
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    /**
     * reads the data if the last element in the list
     * 
     * @return real data of tail node
     */
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    /**
     * Adds the given element to the front of the List
     * 
     * @param e The element to be added
     */
    public void addFirst(E e) {

        if (isEmpty()) {
            head = tail = new Node<>(e, null);
            size++;
            return;
        }

        head = new Node<>(e, head);
        size++;
    }

    /**
     * reads the data if the last element in the list
     * 
     * @param e the element
     */
    public void addLast(E e) {
        if (isEmpty()) {
            head = tail = new Node<>(e, null);
            size++;
        } else {
            tail = tail.next = new Node<>(e, null);
            size++;
        }
    }

    /**
     * removes the first element
     * 
     * @return first element
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E answer = head.getElement();
        head.getClass();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return answer;
    }

    /**
     * reads the data if the middle element in the list returns null if the Singly
     * LinkedList is empty; returns element at size / 2 + 1 if size is odd returns
     * element at size / 2 if size is even
     * 
     * @return real data of middle node
     */
    public E middle() {
        // used to hold calculated middle index
        int m = 0;

        // the node used as placeholder to return element at index
        Node<E> f = head;

        // special case if list is empty
        if (isEmpty()) {
            return null;
        }

        // uses modulos division to check if even or odd
        if (size % 2 == 0) {
            m = size / 2;

            // loop to itterate through list to proper index
            for (int i = 1; i < m; i++) {
                f = f.getNext();
            }

        } else {
            m = (size / 2) + 1;

            // loop to itterate through list to proper index
            for (int i = 1; i < m; i++) {
                f = f.getNext();
            }
        }
        return f.getElement();
    }

    /**
     * removes the last node in the list
     * 
     * @return the element of the removed node
     */
    public E removeLast() {
        if (size == 1) {
            E answer = head.getElement();
            head = null;
            tail = null;
            size = 0;
            return answer;
        } else if (size == 0) {
            E answer = head.element;
            return answer;
        } else {
            Node<E> cur = head;
            Node<E> prev = null;
            while (cur.next != null) {
                cur = cur.getNext();
                prev = cur;
            }
            E answer = tail.getElement();
            tail = prev;
            tail.next = null;
            size--;
            return answer;
        }

    }

    /**
     * looks for node at index number n and removes it by setting the element data
     * and next data to that of the succeding node
     * 
     * @param n the index number to look for
     * @return the removed element data
     */
    public E removeAtN(int n) {
        if (n > size - 1 && size > 0) // n is outside of list size
        {
            return null;
        } else if (n < 0) {
            return null; // n is a negitive or improssible request
        } else if (size == 0) // list is empty
        {
            return null;
        } else if (n == size - 1 && size > 0) // list is not empty request to remove last item
        {
            return removeLast();
        } else if (n == 0 && size > 0) // list is not empty request to remove first item
        {
            return removeFirst();
        } else // all other requests for non-empty 2 < list with n != to first or last item
        {
            Node<E> cur = head; // element to be removed
            Node<E> tailUpdate = head;
            for (int i = 0; i < n; i++) {
                cur = cur.next;
            }
            for (int i = 0; i < size - 1; i++) {
                tailUpdate = tailUpdate.next;
            }
            Node<E> cur1 = cur.next;
            E answer = cur.getElement();
            cur.element = cur1.element;
            cur.next = cur1.next;
            tail.element = tailUpdate.element;
            tail.next = tailUpdate.next;
            size--;
            return answer;
        }
    }

    /**
     * takes in an element and removes the first found element in the list if it
     * exists
     * 
     * @param key the element to be looked for and removed
     * @return the removed element if found
     */
    public E removeElement(E key) {
        if (contains(key) && size > 1) {
            int index = indexOf(key) - 1;
            Node<E> cur = head; // element to be removed

            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            Node<E> cur1 = cur.next;
            E answer = cur.getElement();
            cur.element = cur1.element;
            cur.next = cur1.next;
            return answer;
        } else if (size == 1) {
            return removeFirst();
        } else {
            return null;
        }
    }

    /**
     * remove the node at index i.
     * 
     * @param i the index of the node to be removed
     * @return returns the element of the removed node
     */
    public E reomveElementAtI(int i) {
        if (size > 1) {
            Node<E> cur = head;
            for (int k = 0; k < i; i++) {
                cur = cur.next;
            }
            Node<E> cur1 = cur.next;
            E answer = cur.getElement();
            cur.element = cur1.element;
            cur.next = cur1.next;
            return answer;
        } else if (size == 1) {
            return removeFirst();
        } else {
            return null;
        }
    }

    /**
     * checks the SLL and verifies is a specified element is within the list
     * 
     * @param key the data to be looked for
     * @return true if data exists in the list
     */
    public boolean contains(E key) {
        Node cur = head;

        while (cur != null && cur.getElement() != key) {
            cur = cur.next;
        }
        if (cur == null) {
            return false;
        }
        return true;
    }

    /**
     * checks to see if a specific element is found within the list. If the data is
     * found it returns the index of the element
     * 
     * @param key the data to be looked for
     * @return the index of the found item return -1 if not found
     */
    public int indexOf(E key) {
        Node cur = head;
        int index = 0;
        while (cur != null) {
            if (cur.getElement() == key) {
                return index;
            }
            cur = cur.next;
            index++;
        }
        return -1;
    }

    /**
     * goes to the specified index location within list and returns the element
     * data.
     * 
     * @param k the index number to be looked up
     * @return the data of the found index
     */
    public E findKthElement(int k) {
        Node cur = head;
        while (cur != null && k > 0) {
            cur = cur.next;
            k--;
        }
        if (cur == null) {
            return null;
        } else {
            return (E) cur.getElement();
        }
    }

    /**
     * allows element in node of index index to be modified and replaced with the
     * new element element
     * 
     * @param index   the node index
     * @param element the data to replace node data
     */
    public void editAtIndex(int index, E element) {
        Node<E> cur = head;
        if (index > size || index < 0) {
        } else if (index == size) {
            cur.setElement(element);
        } else {
            while (index != 0) {
                cur = cur.next;
                index--;
            }
            cur.setElement(element);
        }
    }

    public void printList() {
        System.out.println("A list of size " + size);
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getElement() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        Node temp = head;
        String s = "";

        while (temp != null) {
            s += (temp.getElement() + " ");
            temp = temp.next;
        }
        return s;
    }

    public void printList(Node node) {
        if (node == this.head) {
            System.out.println("A list of size " + size);
        }
        if (node == null) {
            return;
        } else {
            System.out.print(node.getElement() + " ");
            printList(node.getNext());
        }
        if (node == this.head) {
            System.out.println("\n");
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;

    }
}
