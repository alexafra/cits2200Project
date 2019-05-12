//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface Stack {
    boolean isEmpty();

    void push(Object var1) throws Overflow;

    Object examine() throws Underflow;

    Object pop() throws Underflow;
}
