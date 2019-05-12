//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public class Pair {
    public Object item1;
    public Object item2;

    public Pair(Object var1, Object var2) {
        this.item1 = var1;
        this.item2 = var2;
    }

    public boolean equals(Object var1) {
        if (var1 == null) {
            return false;
        } else if (!(var1 instanceof Pair)) {
            return false;
        } else {
            return this.item1.equals(((Pair)var1).item1) && this.item2.equals(((Pair)var1).item2);
        }
    }

    public String toString() {
        return "< " + this.item1.toString() + " , " + this.item2.toString() + " >";
    }
}
