/**
 *
 * @author John
 * @param <E>
 */
public interface Position<E> {
    E getElement() throws IllegalStateException;
}
