//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public class ListLinked implements List {
    private Link before;
    private Link after = new Link(null, null);

    public ListLinked() {
        this.before = new Link(null, this.after);
    }

    public boolean isEmpty() {
        return this.before.successor == this.after;
    }

    public boolean isBeforeFirst(WindowLinked var1) {
        return var1.link == this.before;
    }

    public boolean isAfterLast(WindowLinked var1) {
        return var1.link == this.after;
    }

    public void beforeFirst(WindowLinked var1) {
        var1.link = this.before;
    }

    public void afterLast(WindowLinked var1) {
        var1.link = this.after;
    }

    public void next(WindowLinked var1) throws OutOfBounds {
        if (!this.isAfterLast(var1)) {
            var1.link = var1.link.successor;
        } else {
            throw new OutOfBounds("calling next after list end");
        }
    }

    public void previous(WindowLinked var1) throws OutOfBounds {
        if (this.isBeforeFirst(var1)) {
            throw new OutOfBounds("calling previous before start of list");
        } else {
            Link var2 = this.before.successor;

            Link var3;
            for(var3 = this.before; var2 != var1.link; var2 = var2.successor) {
                var3 = var2;
            }

            var1.link = var3;
        }
    }

    public void insertAfter(Object var1, WindowLinked var2) throws OutOfBounds {
        if (!this.isAfterLast(var2)) {
            var2.link.successor = new Link(var1, var2.link.successor);
        } else {
            throw new OutOfBounds("inserting after end of list");
        }
    }

    public void insertBefore(Object var1, WindowLinked var2) throws OutOfBounds {
        if (!this.isBeforeFirst(var2)) {
            var2.link.successor = new Link(var2.link.item, var2.link.successor);
            if (this.isAfterLast(var2)) {
                this.after = var2.link.successor;
            }

            var2.link.item = var1;
            var2.link = var2.link.successor;
        } else {
            throw new OutOfBounds("inserting before start of list");
        }
    }

    public Object examine(WindowLinked var1) throws OutOfBounds {
        if (!this.isBeforeFirst(var1) && !this.isAfterLast(var1)) {
            return var1.link.item;
        } else {
            throw new OutOfBounds("window not within list");
        }
    }

    public Object replace(Object var1, WindowLinked var2) throws OutOfBounds {
        if (!this.isBeforeFirst(var2) && !this.isAfterLast(var2)) {
            Object var3 = var2.link.item;
            var2.link.item = var1;
            return var3;
        } else {
            throw new OutOfBounds("window not within list");
        }
    }

    public Object delete(WindowLinked var1) throws OutOfBounds {
        if (!this.isBeforeFirst(var1) && !this.isAfterLast(var1)) {
            Object var2 = var1.link.item;
            var1.link.item = var1.link.successor.item;
            if (var1.link.successor == this.after) {
                this.after = var1.link;
            }

            var1.link.successor = var1.link.successor.successor;
            return var2;
        } else {
            throw new OutOfBounds("window not within list");
        }
    }
}
