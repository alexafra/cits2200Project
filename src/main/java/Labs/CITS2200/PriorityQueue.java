//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface PriorityQueue<E> {
    boolean isEmpty();

    void enqueue(E var1, int var2) throws IllegalValue;

    E examine() throws Underflow;

    E dequeue() throws Underflow;

    Iterator iterator();
}
