/**
 *
 * @author John
 * @param <E>
 */
public interface List<E> {
    /**
     * @return number of elements in the list
     */
    int size();

    /**
     * @return whether the list is empty
     */
    boolean isEmpty();

    /**
     * @param i index pointer
     * @return (but does not remove) the element at index i
     * @throws IndexOutOfBoundsException
     */
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * replaces the element at index i with e
     * 
     * @param i index pointer
     * @param e element of data
     * @return the replaced data
     * @throws IndexOutOfBoundsException
     */
    E set(int i, E e) throws IndexOutOfBoundsException;

    /**
     * inserts element e at index i, shifting all subsequent elements later
     * 
     * @param i index pointer
     * @param e element of data
     * @throws IndexOutOfBoundsException
     */
    void add(int i, E e) throws IndexOutOfBoundsException;

    /**
     * removes the element at index i, shifting all subsequent elements earlier
     * 
     * @param i index pointer
     * @return the removed element
     * @throws IndexOutOfBoundsException
     */
    E remove(int i) throws IndexOutOfBoundsException;
}
