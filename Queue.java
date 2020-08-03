/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bmccune
 * @param <E>
 */
public interface Queue<E> {

    public int size();

    public E first();

    public E dequeue();

    public void enqueue(E e);

    public boolean isEmpty();
}
