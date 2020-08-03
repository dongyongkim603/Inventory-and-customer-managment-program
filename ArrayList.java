
/**
 *
 * @author John
 * @param <E>
 */
public class ArrayList<E> implements List<E> {
    // instance variables
    public static final int CAPACITY = 16; // defualt array capacity
    private E[] data; // deneric array used for storage
    private int size = 0; // current number of elements

    // constructors

    /**
     * constructs a list with default capacity
     */
    public ArrayList() {
        this(CAPACITY);
    }

    /**
     * constructs list with given capacity
     * 
     * @param capacity size of new list
     */
    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity]; // safe cast compiler may give warning
    }

    // public methods

    /**
     * @return the number of elements in the array list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return whether the array list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param i index pointer
     * @return but does not remove the element at index i
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    /**
     * replaces the element at index i with element e
     * 
     * @param i index pointer
     * @param e data element
     * @return the replaced data
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    /**
     * inserts element e to be at index i, shifting subsequent elements later
     * 
     * @param i index pointer
     * @param e data element
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);

        if (size == data.length) // not enough capacity
        {
            throw new IllegalStateException("array is full!");
        }
        for (int k = size - 1; k >= i; k--) // shifts rightmost element
        {
            data[k + 1] = data[k];
        }
        data[i] = e; // ready to place new data element
        size++;
    }

    /**
     * removes the element at index i, shifting elements earlier
     * 
     * @param i index pointer
     * @return the removed element
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];

        for (int k = i; k < size - 1; k++) // shifts element to fill gap
        {
            data[k] = data[k + 1];
        }
        data[size - 1] = null; // help garbage collection
        size--;
        return temp;
    }

    // utility method

    /**
     * checks whether given index is in the range[0, n-1]
     * 
     * @param i index pointer
     * @param n number of possible inputs
     * @throws IndexOutOfBoundsException
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("illegal index: " + i);
        }
    }
}
