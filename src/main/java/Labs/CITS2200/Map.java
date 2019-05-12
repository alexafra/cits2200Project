//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface Map {
    boolean isEmpty();

    boolean isDefined(Object var1);

    void assign(Object var1, Object var2);

    Object image(Object var1) throws ItemNotFound;

    Object deassign(Object var1) throws ItemNotFound;
}
