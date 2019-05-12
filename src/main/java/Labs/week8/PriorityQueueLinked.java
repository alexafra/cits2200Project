package Labs.week8;

import Labs.CITS2200.*;


/**
 * A PriorityQueue implemented as a Singly Linked Priority Queue.
 */
public class PriorityQueueLinked implements Labs.CITS2200.PriorityQueue {
    private PQLink first;

    /**
     * Constructor for PriorityQueueLinked.
     * Constructs an empty priority queue.
     */
    public PriorityQueueLinked () { first = null; }

    /**
     * Tests if the priority queue is empty.
     * @return true if the priority queue is empty, false otherwise.
     */
    public boolean isEmpty() { return first == null; }

    /**
     * Return the first element in the priority queue - an item with the maximum priority.
     * If there are multiple items with the maximum priority then the order is not specified.
     * @return an item with the maximum priority in the queue.
     * @throws Underflow when the priority queue is empty
     */
    public Object examine() throws Underflow {
        if (isEmpty()) { throw new Underflow("examining an empty priority queue"); }
        return first.element;
    }

    /**
     * Return and remove the first element in the priority queue - an item with the maximum priority.
     * If there are multiple items with the maximum priority then which is returned and removed is not
     * specified.
     * @return an element with the maximum priority in the queue.
     * @throws Underflow when the priority queue is empty
     */
    public Object dequeue() throws Underflow {
        if (isEmpty()) { throw new Underflow("dequeuing from an empty priority queue"); }
        PQLink temp = first;
        first = first.next;
        return temp.element;
    }

    /**
     * Add an element to the priority queue. In this Singly Linked implementation of the Priority Queue
     * the element will be placed in order in the Linked List according to its priority.
     * @param element The element to be added to the queue.
     * @param priority THe priority of the element to be added to the queue.
     * @throws IllegalValue when the priority is negative.
     */
    public void enqueue(Object element, int priority) throws IllegalValue {
        if (priority < 0) { throw new IllegalValue("Priority must not be negative");}
        if (isEmpty() || priority > first.priority) {
            first = new PQLink(element, priority, first);
        } else {
            PQLink current = first;
            while (current.next != null && current.next.priority >= priority) {
                current = current.next;
            }
            current.next = new PQLink(element, priority, current.next);
        }
    }


    /**
     * Creates an iterator that can iterate over the Priority Queue Collection.
     * @return an Iterator object that is wrapped in a public Iterator interface.
     */
    public Iterator iterator() { return new PQLinkedIterator(first); }

    public class PQLinkedIterator implements Iterator {
        //instance variable is public only to avoid potential error with marking machine
        public Link currentBackingLink;

        public PQLinkedIterator (PQLink firstPQLink) {
            currentBackingLink = new Link(firstPQLink, null);
        }

        /**
         * Returns the next item in the iterator to be iterated over, and moves the iterator to the next item in
         * the priority queue to be iterated over. If there are no items left in the priority queue to be iterated over,
         * an OutOfBounds exception will be thrown.
         * @return the next item in the iterator to be iterated over.
         * @throws OutOfBounds if there are no items left in the collection to be iterated over.
         */
        public Object next() throws OutOfBounds {
            if (!hasNext()) { throw new OutOfBounds("calling next over an empty iterator."); }
            PQLink temp = (PQLink)currentBackingLink.item;
            currentBackingLink = new Link(temp.next, null);
            return temp.element;
        }

        /**
         * Tests if the collection has any items left to be iterated over.
         * @return true if there are more items in the iterator to be iterated over, false otherwise.
         */
        public boolean hasNext() { return currentBackingLink.item != null; }
    }

    /**
     * The node or links used in the Linked Priority Queue
     */
    public class PQLink {
        //instance variables are public only to avoid potential error with marking machine
        public Object element;
        public int priority;
        public PQLink next;

        /**
         * A constructor for a priority queue node/link.
         * @param element the value stored in the node.
         * @param priority the priority stored in the node.
         * @param next the next node/link in the singly linked priority queue.
         */
        public PQLink(Object element, int priority, PQLink next) {
            this.element = element;
            this.priority = priority;
            this.next = next;
        }
    }
}
