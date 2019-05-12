package Labs.week3.NotAssessed;

/**
 * A basic integer implementation of the Lock interface
 * @author Alexander George Frazis
 */
public class LockInt implements Lock {
    /**
     * The combination to open the lock
     */
    private int combination;
    /**
     *If the lock is currently open
     */
    private boolean isOpen;

    /**
     * A customizable constructor of the Lock Object
     * @param combination the initial combination
     * @param isOpen is the lock begins open
     */
    public LockInt (int combination, boolean isOpen) {
        this.combination = combination;
        this.isOpen = isOpen;
    }

    /**
     * A default constructor of a LockInt object
     * The Lock initially has a combination 0, and is open.
     */
    public LockInt () {
        this(0, true);
    }

    /**
     * Attempt to open the lock given a combination key.
     * @param combination to unlock the lock
     * @return If the lock is open or closed after the method is called.
     */
    public boolean open(int combination) {
        if (this.isOpen || combination == this.combination) {
            this.isOpen = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Closes the lock
     */
    public void close() {
        this.isOpen = false;
    }

    /**
     * Attempts to change the lock combination.
     * @param initialComb the current lock combination
     * @param finalComb the new lock combination
     * @return if the lock combination was successfully changed
     */
    public boolean changeCombo(int initialComb, int finalComb) {
        if (initialComb == this.combination) {
            this.combination = finalComb;
            return true;
        } else {
            return false;
        }
    }
}
