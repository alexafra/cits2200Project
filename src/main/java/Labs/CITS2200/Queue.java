//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface Queue {
    boolean isEmpty();

    void enqueue(Object var1);

    Object examine() throws Underflow;

    Object dequeue() throws Underflow;
}
