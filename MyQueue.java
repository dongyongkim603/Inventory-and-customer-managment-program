/**
 *
 * @author John
 * @param <E>
 */
public class MyQueue<E> extends SinglyLinkedList<E> implements Queue<E> {

    /**
     * removes first node from front of Queue.
     * 
     * @return the element contained in node
     */
    @Override
    public E dequeue() {
        return removeFirst();
    }

    /**
     * adds new element to the end of the queue
     * 
     * @param e is the element to be added
     */
    @Override
    public void enqueue(E e) {
        addLast(e);
    }

}
