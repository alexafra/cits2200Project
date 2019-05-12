//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface List {
    boolean isEmpty();

    boolean isBeforeFirst(WindowLinked var1);

    boolean isAfterLast(WindowLinked var1);

    void beforeFirst(WindowLinked var1);

    void afterLast(WindowLinked var1);

    void next(WindowLinked var1) throws OutOfBounds;

    void previous(WindowLinked var1) throws OutOfBounds;

    void insertAfter(Object var1, WindowLinked var2) throws OutOfBounds;

    void insertBefore(Object var1, WindowLinked var2) throws OutOfBounds;

    Object examine(WindowLinked var1) throws OutOfBounds;

    Object replace(Object var1, WindowLinked var2) throws OutOfBounds;

    Object delete(WindowLinked var1) throws OutOfBounds;
}
