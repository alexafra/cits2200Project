package Labs.week3.NotAssessed;

public interface Lock {

    /**
     * Attempt to open the lock given a combination key.
     * @param combination to unlock the lock
     * @return If the lock is open or closed after the method is called.
     */
    boolean open(int combination);

    /**
     * Closes the lock
     */
    void close();

    /**
     * Attempts to change the lock combination.
     * @param initialComb the current lock combination
     * @param finalComb the new lock combination
     * @return if the lock combination was successfully changed
     */
    boolean changeCombo(int initialComb, int finalComb);

}
