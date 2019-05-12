package Labs.week4.Assessed;

import Labs.CITS2200.Deque;
import Labs.CITS2200.Overflow;
import Labs.CITS2200.Underflow;

public class DequeCyclic<E> implements Deque<E> {
    //Can i implement with 1
    //Write unit tests????
    private E[] deque;
    private int beforeFirst;
    private int afterLast;

    /**
     * initialise an empty deque
     * @param size the maximum possible size of the deque
     */
    public DequeCyclic (int size) {
        deque = (E[]) new Object[size + 2]; //safe only because deque is private and not exposed to a client
        beforeFirst = 0;
        afterLast = 1;
    }

    /**
     * initialise an empty deque
     * the maximum size possible will be 10
     */
    public DequeCyclic() { this(10); }

    /**
     * tests if the deque is empty
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty() { return (beforeFirst + 1) % deque.length == afterLast; }

    /**
     * tests if the deque is full
     * @return true if the deque is full, false otherwise
     */
    public boolean isFull() { return (afterLast + 1) % deque.length == beforeFirst; }

    /**
     * adds an item to the left of the deque
     * @param var1 the item to add to the deque
     * @throws Overflow if the deque is full
     */
    public void pushLeft(E var1) throws Overflow {
        if (isFull()) throw new Overflow("pushing left on full deque.");
        else {
            deque[beforeFirst] = var1;
            beforeFirst = (beforeFirst + deque.length - 1) % deque.length;
        }
    }

    /**
     * adds an item to the right of the deque
     * @param var1 the item to add to the deque
     * @throws Overflow if the deque is full
     */
    public void pushRight(E var1) throws Overflow {
        if (isFull()) throw new Overflow("pushing right on full deque.");
        else {
            deque[afterLast] = var1;
            afterLast = (afterLast + 1) % deque.length;
        }
    }

    /**
     * remove and return the leftmost item of the deque
     * @return the leftmost item of the deque
     * @throws Underflow if the deque is empty
     */
    public E popLeft() throws Underflow {
        if (isEmpty()) throw new Underflow("popping left from empty deque.");
        else {
            beforeFirst = (beforeFirst + 1) % deque.length;
            return deque[beforeFirst];
        }
    }

    /**
     * remove and return the rightmost item of the deque
     * @return the rightmost item of the deque
     * @throws Underflow if the deque is empty
     */
    public E popRight() throws Underflow {
        if (isEmpty()) throw new Underflow("popping right from empty deque.");
        else {
            afterLast = (afterLast + deque.length - 1) % deque.length;
            return deque[afterLast];
        }
    }

    /**
     * return the leftmost item of the deque
     * does not alter the data or the state of the deque
     * @return the leftmost item of the deque
     * @throws Underflow if the deque is empty
     */
    public E peekLeft() throws Underflow {
        if (isEmpty()) throw new Underflow("peeking left from empty deque.");
        return deque[(beforeFirst + 1) % deque.length];
    }

    /**
     * return the rightmost item of the deque
     * does not alter the data or the state of the deque
     * @return the rightmost item of the deque
     * @throws Underflow if the deque is empty
     */
    public E peekRight() throws Underflow {
        if (isEmpty()) throw new Underflow("peeking right from empty deque.");
        return deque[(afterLast + deque.length - 1) % deque.length];
    }

}