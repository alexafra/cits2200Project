//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public abstract class BinaryTree<E> {
    private E item;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree() {
    }

    public BinaryTree(E var1, BinaryTree<E> var2, BinaryTree<E> var3) {
        this.item = var1;
        if (var2 != null && var3 != null) {
            this.left = var2;
            this.right = var3;
        } else {
            throw new IllegalValue("Subtrees must be non-null");
        }
    }

    public boolean isEmpty() {
        return this.left == null;
    }

    public E getItem() {
        if (this.isEmpty()) {
            throw new Underflow("Tree is empty");
        } else {
            return this.item;
        }
    }

    public BinaryTree<E> getLeft() {
        if (this.isEmpty()) {
            throw new Underflow("Tree is empty");
        } else {
            return this.left;
        }
    }

    public BinaryTree<E> getRight() {
        if (this.isEmpty()) {
            throw new Underflow("Tree is empty");
        } else {
            return this.right;
        }
    }

    public abstract Iterator<E> iterator();

    public String toString() {
        StringBuffer var1 = new StringBuffer();
        this.toString(0, var1);
        return var1.toString();
    }

    private void toString(int var1, StringBuffer var2) {
        if (!this.isEmpty()) {
            this.left.toString(var1 + 1, var2);

            for(int var3 = 0; var3 < var1; ++var3) {
                var2.append("\t");
            }

            var2.append(this.item.toString() + "\n");
            this.right.toString(var1 + 1, var2);
        }
    }

    public abstract boolean equals(Object var1);
}
