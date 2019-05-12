package Labs.week3.Assessed;

import Labs.CITS2200.Overflow;
import Labs.CITS2200.Stack;
import Labs.CITS2200.Underflow;

public class StackBlock implements Stack {
    private Object[] stackArray;
    private int cursor;

    /**
     * initialise a new customizable stack
     * @param s the size of the Stack
     */
    public StackBlock(int s) {
        this.stackArray = new Object[s];
        cursor = -1;
    }
    /**
     * initialise a new default stack
     * The size defaults to 10
     */
    public StackBlock() {
        this(10);
    }

    /**
     * test if the stack is empty
     * @return if the stack is empty
     */
    @Override
    public boolean isEmpty() { return cursor == -1; }

    /**
     * test if the stack is full.
     * @return if the stack is full
     */
    public boolean isFull() { return cursor == (stackArray.length - 1); }

    /**
     * add a new object to the top of the stack
     * @param var1 the object to be added
     * @throws Overflow if the stack is full
     */
    @Override
    public void push(Object var1) throws Overflow {
        if (isFull()) throw new Overflow("attempting to push to a full stack");
        else stackArray[++cursor] = var1;
    }

    /**
     * examines the top element of the stack
     * @return the top element of the stack
     * @throws Underflow if the stack is empty
     */
    @Override
    public Object examine() throws Underflow {
        if(isEmpty()) throw new Underflow("attempting to examine an empty stack");
        else return stackArray[cursor];
    }

    /**
     * removes and returns the top element of the stack
     * @return the top element of the stack
     * @throws Underflow if the stack is empty
     */
    @Override
    public Object pop() throws Underflow {
        if(isEmpty()) throw new Underflow("attempting to pop from an empty stack");
        else return stackArray[cursor--];
    }
}
