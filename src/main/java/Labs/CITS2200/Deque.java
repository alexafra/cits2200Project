//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface Deque<E> {
    void pushLeft(E var1) throws Overflow;

    void pushRight(E var1) throws Overflow;

    E popLeft() throws Underflow;

    E popRight() throws Underflow;

    E peekRight() throws Underflow;

    E peekLeft() throws Underflow;

    boolean isEmpty();

    boolean isFull();
}
