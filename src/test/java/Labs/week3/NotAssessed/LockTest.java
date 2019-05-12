package Labs.week3.NotAssessed;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Lock Test")
public class LockTest {
    Lock lock1;
    Lock lock2;

    @BeforeEach
    public void init() {
        lock1 = new LockInt();
        lock2 = new LockString();
    }

    @AfterEach
    public void cleanUp() {
        lock1 = null;
        lock2 = null;
    }

    @Test
    @DisplayName("It should initialise int lock to open and combination 0 ")
    public void initialiseIntLock() {
        assertEquals(true, lock1.open(1));
        lock1.close();
        assertEquals(false, lock1.open(1));
        assertEquals(true, lock1.open(0));
    }
    @Test
    @DisplayName("It should initialise the String lock to open and combination 0")
    public void initialiseStringLock() {
        assertEquals(true, lock2.open(1));
        lock2.close();
        assertEquals(false, lock2.open(1));
        assertEquals(true, lock2.open(0));
    }
    @Test
    @DisplayName("It should only change and open int lock if given the correct key")
    public void openIntLock() {
        lock1.changeCombo(0, 132);
        lock1.close();
        assertEquals(false, lock1.open(10));
        assertEquals(true, lock1.open(132));
    }

    @Test
    @DisplayName("It should only change and open String lock if given the correct key")
    public void openStringLock() {
        lock2.changeCombo(321, 100);
        lock2.close();
        assertEquals(false, lock2.open(100));
        assertEquals(true, lock2.open(0));
    }

}
