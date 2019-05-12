package Labs.week4.NotAssessed;

import Labs.CITS2200.Overflow;
import Labs.CITS2200.Queue;
import Labs.CITS2200.Underflow;

public class QueueCyclic implements Queue {
    private Object[] queue;
    private int count;
    private int first;

    /**
     * initialise an empty queue specifying the maximum size
     * @param size the maximum size of the queue
     */
    public QueueCyclic (int size) {
        queue = new Object[size];
        count = 0;
        first = 0;
    }

    /**
     * initialise a default empty queue with maximum size of 10
     */
    public QueueCyclic () { this (10); }

    /**
     * tests if the queue is full
     * @return if the queue is full
     */
    public boolean isFull() { return count == queue.length; }

    /**
     * tests if the queue is empty
     * @return if the queue is empty
     */
    public boolean isEmpty() { return count == 0; };

    /**
     * adds an item to the end of the queue
     * @param var1 the item to be added to the queue
     * @throws Overflow if the queue is full
     */
    public void enqueue(Object var1) throws Overflow {
        if (isFull()) throw new Overflow("enqueuing to a full queue.");
        else queue[(first + count++) % queue.length] = var1;
    }

    /**
     * returns the first element in the queue without changing the
     * queues state.
     * @return the first element in the queue
     * @throws Underflow if the queue is empty
     */
    public Object examine() throws Underflow {
        if(isEmpty()) throw new Underflow("examining an empty queue.");
        else return queue[first];
    }

    /**
     * removes and returns the first element in the queue
     * @return the first element in the queue
     * @throws Underflow if the queue is empty
     */
    public Object dequeue() throws Underflow {
        if (isEmpty()) throw new Underflow("dequeuing an empty queue.");
        else {
            Object firstObj = queue[first];
            first = (first + 1) % queue.length;
            count--;
            return firstObj;
        }
    }



}
