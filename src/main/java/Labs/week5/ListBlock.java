package Labs.week5;


import Labs.CITS2200.OutOfBounds;

public class ListBlock implements List {

    private final static int before = -1;
    private Object[] list;
    private int after;

    public boolean isEmpty() { return (before + 1) == after; }

    public boolean isFull() { return (after == list.length); }

    public boolean isBeforeFirst(WindowBlock var1) { return  var1.index == -1; }

    public boolean isAfterLast(WindowBlock var1) { return var1.index == after; }

    public void beforeFirst(WindowBlock var1) { var1.index = before; }

    public void afterLast(WindowBlock var1) { var1.index = after; }

    public void next(WindowBlock var1) throws OutOfBounds {
        if ( var1.index >= after  || var1.index < before )
            throw new OutOfBounds("Getting next from invalid list index");
        var1.index ++;
    }

    public void previous(WindowBlock var1) throws OutOfBounds {
        if ( var1.index <= before || var1.index > after )
            throw new OutOfBounds("Getting previous from invalid list index");
        var1.index--;
    }

    public void insertAfter(Object var1, WindowBlock var2) throws OutOfBounds {
        if ( var2.index >= after  || var2.index < before )
            throw new OutOfBounds("Inserting after from invalid list index");
        for (int i = after - 1; i >= var2.index + 1; i --) { list[i + 1] = list[i]; }
        list[var2.index + 1] = var1;
    }

    public void insertBefore(Object var1, WindowBlock var2) throws OutOfBounds {
        if ( var2.index <= before || var2.index > after )
            throw new OutOfBounds("Inserting before from invalid list index");
        for (int i = after - 1; i >= var2.index; i--) { list[i + 1] = list[i]; }
        list[var2.index++] = var1;

    }

    public Object examine(WindowBlock var1) throws OutOfBounds {
        if (var1.index <= before || var1.index >= after)
            throw new OutOfBounds("Examining an invalid list index");
        return list[var1.index];
    }


    public Object replace(Object var1, WindowBlock var2) throws OutOfBounds {
        if (var2.index <= before || var2.index >= after)
            throw new OutOfBounds("Replacing from invalid list index");
        Object old = list[var2.index];
        list[var2.index] = var1;
        return old;
    }

    public Object delete(WindowBlock var1) throws OutOfBounds {
        if (var1.index <= before || var1.index >= after)
            throw new OutOfBounds("Deleting from invalid list index");
        Object old = list[var1.index];
        for (int i = var1.index; i < after - 1; i++) { list[i] = list[i + 1]; }
        after--;
        return old;
    }
}
