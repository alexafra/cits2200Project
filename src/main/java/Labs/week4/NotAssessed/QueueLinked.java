package Labs.week4.NotAssessed;

import Labs.CITS2200.Overflow;
import Labs.CITS2200.Queue;
import Labs.CITS2200.Underflow;

public class QueueLinked implements Queue{
    private Link link;

    /**
     * initialise an empty queue
     */
    public QueueLinked() {
        link = new Link(null, null);
        link.setSuccessor(link);
    }

    /**
     * tests if the queue is empty
     * @return if the queue is empty
     */
    public boolean isEmpty() { return link.getSuccessor() == link; }

    /**
     * adds an item to the end of the queue
     * @param var1 the item to be added to the queue
     * @throws Overflow if the queue is full
     */
    public void enqueue(Object var1) {
        link.setSuccessor(new Link(null, link.getSuccessor()));
        link.setItem(var1);
        link = link.getSuccessor();
    }

    /**
     * returns the first element in the queue without changing the
     * queues state.
     * @return the first element in the queue
     * @throws Underflow if the queue is empty
     */
    public Object examine() throws Underflow {
        if(isEmpty()) throw new Underflow("examining an empty queue.");
        else return link.getSuccessor().getItem();
    }

    /**
     * removes and returns the first element in the queue
     * @return the first element in the queue
     * @throws Underflow if the queue is empty
     */
    public Object dequeue() throws Underflow {
        if (isEmpty()) throw new Underflow("dequeuing an empty queue.");
        else {
            Object firstItem = link.getSuccessor().getItem();
            link.setSuccessor(link.getSuccessor().getSuccessor());
            return firstItem;
        }
    }

}
