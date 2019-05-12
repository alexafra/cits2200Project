package Labs.week3.NotAssessed;

import Labs.CITS2200.Overflow;
import Labs.CITS2200.Stack;
import Labs.CITS2200.Underflow;

public class StackLink implements Stack {

    private Link last;

    public StackLink(Link last) { this.last = last; }

    public StackLink() { this(null); }

    /**
     * test if the stack is empty
     * @return if the the stack is empty
     */
    public boolean isEmpty() { return last == null; }

    /**
     * test if the stack is full
     * it will always be false for Link implementation of stack interface
     * @return if the stack is full
     */
    public boolean isFull() { return false; }

    /**
     * adds an item to the top of the stack
     * @param var1 the object to be added to the stack
     * @throws Overflow this will never be thrown for Link implementation of stack interface
     */
    public void push(Object var1) throws Overflow {
        Link newLink = new Link(var1, last);
        last = newLink;
    }

    /**
     * returns the top item in the stack without changing the stack state
     * @return the top item in the stack
     * @throws Underflow if the stack is empty
     */
    public Object examine() throws Underflow {
        if (isEmpty()) throw new Underflow("Examining empty stack");
        else return last;
    }

    /**
     * removes and returns the top item in the stack
     * @return the top item in the stack
     * @throws Underflow if the stack is empty
     */
    public Object pop() throws Underflow {
        if (isEmpty()) throw new Underflow("Popping an empty array");
        Object lastItem = last.getItem();
        last = last.getPrevious();
        return lastItem;
    }
}
