package Labs.week4.NotAssessed;

import Labs.CITS2200.Overflow;
import Labs.CITS2200.Queue;
import Labs.CITS2200.Underflow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@DisplayName("Queue test suit")
public class QueueCyclicTest {
    private Queue queue;

    @BeforeEach
    public void init () {
        //Change constructor's class to change which queue implementation
        //is tested. QueueCyclic or QueueLinked
        queue = new QueueLinked();
    }

    @AfterEach
    public void cleanup() {
        queue = null;
    }

    @Test
    @DisplayName("it should return if the block queue is full")
    public void isEmptyTest() {
        assertThat(queue.isEmpty()).isEqualTo(true);
        for (int i = 0; i < 10; i++) {
            queue.enqueue('a');
            assertThat(queue.isEmpty()).isEqualTo(false);
        }
        for (int i = 0; i < 9; i++) {
            queue.dequeue();
            assertThat(queue.isEmpty()).isEqualTo(false);
        }
        queue.dequeue();
        assertThat(queue.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("it should return the first element and not alter the queue")
    public void examineTest() {
        Throwable thrown = catchThrowable(() -> { queue.examine(); });
        assertThat(thrown).isInstanceOf(Underflow.class);
        for (int i = 0; i < 10; i++) { queue.enqueue(i); }
        for (int i = 0; i < 3; i++) { queue.examine(); }
        assertThat(queue.examine()).isEqualTo(0);
        assertThat(queue.examine()).isEqualTo(0);
    }

    @Test
    @DisplayName("it should throw exception when adding to a full queue")
    public void enqueueDequeueGeneralTest() {
        queue.enqueue(1);
        assertThat(queue.isEmpty()).isEqualTo(false);
        assertThat(queue.dequeue()).isEqualTo(1);

        for (int i = 0; i < 10; i++) {queue.enqueue(i); }
        for (int i = 0; i < 10; i++) {assertThat(queue.dequeue()).isEqualTo(i);}
    }

    @Test
    @DisplayName("it should throw Underflow when dequeuing an empty queue")
    public void dequeueExceptionTest() {
        Throwable thrown = catchThrowable(() -> queue.dequeue());
        assertThat(thrown).isInstanceOf(Underflow.class);

        for (int i = 0; i < 10; i++) {queue.enqueue(i); }
        for (int i = 0; i < 10; i++) {
            thrown = catchThrowable(() -> queue.dequeue());
            assertThat(thrown).doesNotThrowAnyException();
        }
        thrown = catchThrowable(() -> queue.dequeue());
        assertThat(thrown).isInstanceOf(Underflow.class);
    }



    //Specific to Block
    @Test
    @DisplayName("it should throw exception when adding to a full queue")
    public void enqueueExceptionTest() {
        queue = new QueueCyclic();
        //this is not a pure function - side affects object is altered
        for (int i = 0; i < 10; i++) {
            Throwable thrown = catchThrowable(() -> queue.enqueue(1));
            assertThat(thrown).doesNotThrowAnyException();
        }

        Throwable thrown = catchThrowable(() -> { queue.enqueue(10); });
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
