package Labs.week5;

import Labs.CITS2200.Link;
import Labs.CITS2200.List;
import Labs.CITS2200.OutOfBounds;
import Labs.CITS2200.WindowLinked;

public class ListLinked implements List  {
    private Link before;
    private Link after;


    public ListLinked () {
        after = new Link(null, null);
        before = new Link(null, after);
    }

    /**
     * tests if the list is empty
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() { return before.successor == after; }

    /**
     * tests if the given window is over the before-first link
     * @param var1 the window to be tested
     * @return true if the window is over the before-first link, false otherwise
     */
    public boolean isBeforeFirst(WindowLinked var1) { return var1.link == before; }

    /**
     * tests if the given window is over the after-last link
     * @param var1 the window to be tested
     * @return true if the window is over the after-last link, false otherwise
     */
    public boolean isAfterLast(WindowLinked var1) { return var1.link == after; }

    /**
     * moves the window over the before-first link
     * @param var1 the window to be moved
     */
    public void beforeFirst(WindowLinked var1) { var1.link = before; }

    /**
     * moves the window over the after-last link
     * @param var1 the window to be moved
     */
    public void afterLast(WindowLinked var1) { var1.link = after; }

    /**
     * moves the window to the next link in the list
     * @param var1 the window to be moved
     * @throws OutOfBounds if the window is already over the after-last link
     */
    public void next(WindowLinked var1) throws OutOfBounds {
        if (isAfterLast(var1)) //any other corner cases???
            throw new OutOfBounds("Getting next from the after-last link.");
        var1.link = var1.link.successor;
    }

    /**
     * moves the window to the previous link in the list
     * @param var1 the window to be moved
     * @throws OutOfBounds if the window is already over the before-first link
     */
    public void previous(WindowLinked var1) throws OutOfBounds {
        if (isBeforeFirst(var1))
            throw new OutOfBounds("Getting before from the before-first link.");
        if(isEmpty()) {
            var1.link = before;
            return;
        }

        Link previous = before;
        Link current = before.successor;
        while (current != var1.link) {
            previous = current;
            current = current.successor;
        }
        var1.link = previous;

    }

    /**
     * insert an item after the given window
     * @param var1 the item to be inserted
     * @param var2 the window that the item is to be inserted after
     * @throws OutOfBounds if the window is already over the after-last link
     */
    public void insertAfter(Object var1, WindowLinked var2) throws OutOfBounds {
        if (isAfterLast(var2))
            throw new OutOfBounds("Inserting after the after-last link.");
        var2.link.successor = new Link(var1, var2.link.successor);
    }

    /**
     * insert an item before the given window
     * @param var1 the item ot be inserted
     * @param var2 the window that the item is to be inserted before
     * @throws OutOfBounds if the window is already over the before-first link
     */
    public void insertBefore(Object var1, WindowLinked var2) throws OutOfBounds {
        if (isBeforeFirst(var2))
            throw new OutOfBounds("Inserting before the before-first link.");
        Link newLink = new Link(var2.link.item, var2.link.successor);
        var2.link.successor = newLink;
        if (var2.link == after)
            after = var2.link.successor;
        var2.link.item = var1;
        var2.link = newLink;
    }

    /**
     * returns the item the given window is hovering over
     * @param var1 the given window
     * @return the item the given window is over
     * @throws OutOfBounds if the given window is over either the before-first or after-last link
     */
    public Object examine(WindowLinked var1) throws OutOfBounds {
        if (isBeforeFirst(var1) || isAfterLast(var1))
            throw new OutOfBounds("Examining the before-first or after-last link");
        return var1.link.item;
    }

    /**
     * replace the item a given window is hovering over
     * @param var1 the item to be inserted at the link the window is hovering over
     * @param var2 the given window
     * @return the item that was replaced
     * @throws OutOfBounds if the given window is over either the before-first or after-last link
     */
    public Object replace(Object var1, WindowLinked var2) throws OutOfBounds {
        if (isBeforeFirst(var2) || isAfterLast(var2))
            throw new OutOfBounds("Replacing the before-first or after-last link");
        Object oldObj = var2.link.item;
        var2.link.item = var1;
        return oldObj;
    }

    /**
     * delete the link the given window is hovering over, return the item in that link
     * and move the window to the next link
     * @param var1 the given window
     * @return the item in the link that is being deleted
     * @throws OutOfBounds if the given window is over either the before-first or after-last link
     */
    public Object delete(WindowLinked var1) throws OutOfBounds {
        if (isBeforeFirst(var1) || isAfterLast(var1))
            throw new OutOfBounds("deleting the before-first or after-last link");
        Object oldObj = var1.link.item;
        var1.link.item = var1.link.successor.item;

        if (var1.link.successor == after) {
            after = var1.link;
        }
        var1.link.successor = var1.link.successor.successor;
        return oldObj;
    }
}