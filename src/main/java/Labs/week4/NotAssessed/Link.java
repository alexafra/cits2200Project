package Labs.week4.NotAssessed;

public class Link {
    private Object item;
    private Link successor;

    public Link (Object item, Link successor) {
        this.item = item;
        this.successor = successor;
    }

    public Link (Object item) { this(item, null); }

    public Link () { this(null, null);}

    public void setSuccessor(Link successor) { this.successor = successor; }

    public void setItem(Object item) { this.item = item; }

    public Object getItem() { return this.item; }

    public Link getSuccessor() { return this.successor; }
}
