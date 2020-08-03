/**
 *
 * @author John
 * @param <E>
 */
public interface PositionalList<E> {
    /**
     * @return the number of elements in the list
     */
    int size();

    /**
     * @return tests whether the list is empty
     */
    boolean isEmpty();

    /**
     * @return the first Position in the list null if empty
     */
    Position<E> first();

    /**
     * @return the last Position in the list null if empty
     */
    Position<E> last();

    /**
     * @return the Position immediately before Position p(or null, if p is first)
     */
    Position<E> before() throws IllegalArgumentException;

    /**
     * @return the Position immediately after Position p(or null, if p is last)
     */
    Position<E> after() throws IllegalArgumentException;

    /**
     * inserts element e at the front of the list
     * 
     * @param e the real data
     * @return returns its new Position
     */
    Position<E> addFirst(E e);

    /**
     * inserts element e at the end of the list
     * 
     * @param e the real data
     * @return returns its new Position
     */
    Position<E> addLast(E e);

    /**
     * insets element e immediately before Position p
     * 
     * @param p the Position
     * @param e the real data
     * @return the new Position
     * @throws IllegalArgumentException
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * insets element e immediately after Position p
     * 
     * @param p the Position
     * @param e the real data
     * @return the new Position
     * @throws IllegalArgumentException
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element stored at Position p
     * 
     * @param p the Position
     * @param e the real data
     * @return new the replaced element
     * @throws IllegalArgumentException
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * removes the element stored at Position p
     * 
     * @param p the Position
     * @param e the real data
     * @return the removed element
     * @throws IllegalArgumentException
     */
    E remove(Position<E> p, E e) throws IllegalArgumentException;
}
