package Labs.week6;

import Labs.CITS2200.BinaryTree;
import Labs.CITS2200.Iterator;
import Labs.CITS2200.OutOfBounds;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BinTree is a concrete implementation of the BianryTree class. It overrides the equals()
 * and the iterator() methods
 * @param <E> The class type of the objects stored in the nodes of the BinaryTree.
 */
public class BinTree<E> extends BinaryTree<E> {

    /**
     * The default BinTree constructor, creates an "external" or "sentinal node"
     */
    public BinTree () { super(); }

    /**
     * The custom BinTree constructor, creates a new tree using a root node, a left subtree and a right subtree.
     * @param item to go into the new root node
     * @param b1 the left subtree
     * @param b2 the right subtree
     */
    public BinTree (E item, BinaryTree b1, BinaryTree b2) { super (item, b1, b2); }

    /**
     * Test if the current BinTree object is equal to another binTree object. It tests both the
     * tree structures are equal and the values inside the nodes are equal.
     * @param var1 the other tree that is being tested for equality
     * @return true if the two BinTrees are equal and false otherwise.
     */
    public boolean equals(Object var1) {
        if (!(var1 instanceof BinaryTree)) {
            //1. If var1 isnt a binary tree return false
            return false;
        }

        BinaryTree<E> otherTree = (BinaryTree<E>) var1;

        if (this.isEmpty() && otherTree.isEmpty()) {
            //2. If they are both empty then they are equal
            return true;


        } else if (this.isEmpty() || otherTree.isEmpty()) {
            //3. If only one tree is empty return false
            return false;
        } else {
            //4. Neither tree is empty
            boolean equalRoot;
            if (this.getItem() == null && otherTree.getItem() == null) {
                equalRoot = true;
            } else if (this.getItem() == null || otherTree.getItem() == null) {
                equalRoot = false;
            } else {
                equalRoot = this.getItem().equals(otherTree.getItem());
            }
            boolean equalLeft = this.getLeft().equals(otherTree.getLeft());
            boolean equalRight = this.getRight().equals(otherTree.getRight());
            return equalRoot && equalLeft && equalRight;
        }
    }

    /**
     * Returns an Iterator over the collection of BinTree nodes. Not including external/sentinel nodes.
     * @return an Iterator over the collection of BinTree nodes.
     */
    public Iterator<E> iterator() {
        return new BinTreeIterator<E>(this);

    }


    /**
     * A concrete implementation of the Iterator interface that is only visible within this class.
     * @param <E> is the class type stored in each binTree node.
     */
    private class BinTreeIterator<E> implements Iterator<E> {

        //The backing queue that is used by the iterator to track the nodes in the tree
        private Queue<BinaryTree<E>> backingQueue;

        /**
         * The only BinTreeIterator constructor
         * @param thisTree the tree to be iterated over
         */
        private BinTreeIterator(BinTree thisTree) {
            backingQueue = new LinkedList<BinaryTree<E>>();
            if (!thisTree.isEmpty()) { backingQueue.offer(thisTree); }

        }

        /**
         * tests if there is another item in the tree to be iterated over
         * @return true if there is another item left to be iterated over, false otherwise
         */
        public boolean hasNext() { return backingQueue.peek() != null; }

        /**
         * @return the next object to be iterated over
         * @throws OutOfBounds if next is called and there are no items left to be iterated over
         */
        public E next() throws OutOfBounds {
            if (hasNext() == false) { throw new OutOfBounds("getting next from empty iterator"); }
            BinaryTree<E> currentSubTree = backingQueue.remove();
            BinaryTree<E> left = currentSubTree.getLeft();
            BinaryTree<E> right = currentSubTree.getRight();
            if (!left.isEmpty()) { backingQueue.add(left); }
            if (!right.isEmpty()) { backingQueue.add(right); }

            return currentSubTree.getItem();
        }
    }


}
