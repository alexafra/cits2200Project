package Labs.week5;

import Labs.CITS2200.OutOfBounds;

public interface List {
    boolean isEmpty();

    boolean isBeforeFirst(WindowBlock var1);

    boolean isAfterLast(WindowBlock var1);

    void beforeFirst(WindowBlock var1);

    void afterLast(WindowBlock var1);

    void next(WindowBlock var1) throws OutOfBounds;

    void previous(WindowBlock var1) throws OutOfBounds;

    void insertAfter(Object var1, WindowBlock var2) throws OutOfBounds;

    void insertBefore(Object var1, WindowBlock var2) throws OutOfBounds;

    Object examine(WindowBlock var1) throws OutOfBounds;

    Object replace(Object var1, WindowBlock var2) throws OutOfBounds;

    Object delete(WindowBlock var1) throws OutOfBounds;
}
