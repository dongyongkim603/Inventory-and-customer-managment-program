/**
 *
 * @author john
 */
public class LinkedQueue<E> implements Queue<E> {
    private SinglyLinkedList<E> list;

    public LinkedQueue() {
        list = new SinglyLinkedList<>();
    }

    public int size() {
        return list.getSize();
    }

    public E first() {
        return list.first();
    }

    public E dequeue() {
        E val = list.first();
        list.removeFirst();
        return val;
    }

    public void enqueue(E e) {
        list.addLast(e);
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public String toString() {
        return list.toString();
    }

}