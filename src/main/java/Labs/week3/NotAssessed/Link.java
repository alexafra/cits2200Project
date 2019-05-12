package Labs.week3.NotAssessed;

public class Link {
    private Object item;
    private Link previous;

    public Link (Object item, Link previous) {
        this.item = item;
        this.previous = previous;
    }

    public Link (Object item) { this(item, null); }

    public Link () { this(null, null);}

    public void setLink(Link previous) { this.previous = previous; }

    public void setItem(Object item) { this.item = item; }

    public Object getItem() { return this.item; }

    public Link getPrevious() { return this.previous; }
}
