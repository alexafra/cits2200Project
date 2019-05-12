package Labs.week4.Assessed;

import Labs.CITS2200.Deque;
import Labs.CITS2200.Overflow;
import Labs.CITS2200.Underflow;
import Labs.week4.NotAssessed.QueueCyclic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DequeCyclicTest {
    private Deque deque;

    @BeforeEach
    public void init () {
        //Change constructor's class to change which queue implementation
        //is tested. QueueCyclic or QueueLinked
        deque = new DequeCyclic();
    }

    @AfterEach
    public void cleanup() {
        deque = null;
    }

    @Test
    @DisplayName("it should return if the block queue is full")
    public void isEmptyTest() {
        assertThat(deque.isEmpty()).isEqualTo(true);
        for (int i = 0; i < 10; i++) {
            deque.pushRight('a');
            assertThat(deque.isEmpty()).isEqualTo(false);
        }
        for (int i = 0; i < 9; i++) {
            deque.popRight();
            assertThat(deque.isEmpty()).isEqualTo(false);
        }
        deque.popRight();
        assertThat(deque.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("it should return the first element and not alter the queue")
    public void examineTest() {
        Throwable thrown = catchThrowable(() -> { deque.peekRight(); });
        assertThat(thrown).isInstanceOf(Underflow.class);
        for (int i = 0; i < 10; i++) { deque.pushRight(i); }
        for (int i = 0; i < 3; i++) { deque.peekRight(); }
        assertThat(deque.peekRight()).isEqualTo(0);
        assertThat(deque.peekRight()).isEqualTo(0);
    }

    @Test
    @DisplayName("it should throw exception when adding to a full queue")
    public void enqueueDequeueGeneralTest() {
        deque.pushRight(1);
        assertThat(deque.isEmpty()).isEqualTo(false);
        assertThat(deque.popRight()).isEqualTo(1);

        for (int i = 0; i < 10; i++) {deque.pushRight(i); }
        for (int i = 0; i < 10; i++) {assertThat(deque.popLeft()).isEqualTo(i);}
    }

    @Test
    @DisplayName("it should throw Underflow when dequeuing an empty queue")
    public void dequeueExceptionTest() {
        Throwable thrown = catchThrowable(() -> deque.popRight());
        assertThat(thrown).isInstanceOf(Underflow.class);

        for (int i = 0; i < 10; i++) {deque.pushRight(i); }
        for (int i = 0; i < 10; i++) {
            thrown = catchThrowable(() -> deque.popRight());
            assertThat(thrown).doesNotThrowAnyException();
        }
        thrown = catchThrowable(() -> deque.popRight());
        assertThat(thrown).isInstanceOf(Underflow.class);
    }



    //Specific to Block
    @Test
    @DisplayName("it should throw exception when adding to a full queue")
    public void enqueueExceptionTest() {
        deque = new DequeCyclic();
        //this is not a pure function - side affects object is altered
        for (int i = 0; i < 10; i++) {
            Throwable thrown = catchThrowable(() -> deque.pushRight(1));
            assertThat(thrown).doesNotThrowAnyException();
        }

        Throwable thrown = catchThrowable(() -> { deque.pushRight(10); });
        assertThat(thrown).isInstanceOf(Overflow.class);

    }

    //specific to block
    @Test
    @DisplayName("it should return if the block queue is full")
    public void isFullTest() {
        QueueCyclic queue = new QueueCyclic();
        assertThat(queue.isFull()).isEqualTo(false);
        for (int i = 0; i < 9; i++) {
            queue.enqueue('a');
            assertThat(queue.isFull()).isEqualTo(false);
        }
        queue.enqueue('a');
        assertThat(queue.isFull()).isEqualTo(true);
    }
}

