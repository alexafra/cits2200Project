package Labs.week8;

import Labs.CITS2200.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PriorityQueueLinkedTest {

    PriorityQueue pq;

    @BeforeEach
    public void init () {
        pq = new PriorityQueueLinked();
    }

    @AfterEach
    public void cleanup() {
        pq = null;
    }


    @Test
    @DisplayName("Test empty Priority Queue")
    public void testEmptyPriorityQueue()  {

        assertThat(pq.isEmpty()).isEqualTo(true);

        Throwable thrown1 = catchThrowable(() -> pq.examine());
        assertThat(thrown1).isInstanceOf(Underflow.class);

        Throwable thrown2 = catchThrowable(() -> pq.dequeue());
        assertThat(thrown2).isInstanceOf(Underflow.class);

        Iterator it1 = pq.iterator();
        assertThat(it1.hasNext()).isEqualTo(false);

        Throwable thrown3 = catchThrowable(() -> it1.next());
        assertThat(thrown3).isInstanceOf(OutOfBounds.class);
    }

    @Test
    @DisplayName("Test single item priority queue")
    public void testSingleLengthPriorityQueue() {
        Throwable thrown1 = catchThrowable(() -> pq.enqueue("a", -1));
        assertThat(thrown1).isInstanceOf(IllegalValue.class);

        Throwable thrown2 = catchThrowable(() -> pq.enqueue("a", 0));
        assertThat(thrown2).doesNotThrowAnyException();

        assertThat(pq.examine()).isEqualTo("a");
        assertThat(pq.examine()).isEqualTo("a");

        Iterator it1 = pq.iterator();
        assertThat(it1.hasNext()).isEqualTo(true);
        assertThat(it1.next()).isEqualTo("a");
        assertThat(it1.hasNext()).isEqualTo(false);
        Throwable thrown3 = catchThrowable(() -> it1.next());
        assertThat(thrown3).isInstanceOf(OutOfBounds.class);

        assertThat(pq.dequeue()).isEqualTo("a");
        Throwable thrown4 = catchThrowable(()-> pq.dequeue());
        assertThat(thrown4).isInstanceOf(Underflow.class);

    }

    @Test
    @DisplayName("Test complex priority queue")
    public void testMediumSizedPriorityQUeue() {
        Throwable thrown1 = catchThrowable(() -> pq.enqueue("a", -1));
        assertThat(thrown1).isInstanceOf(IllegalValue.class);

        for (int i = 0; i < 10; i ++) {
            pq.enqueue((char)('a' + i), i / 2);
        }

        Throwable thrown2 = catchThrowable(() -> pq.enqueue("a", -10));
        assertThat(thrown2).isInstanceOf(IllegalValue.class);



        assertThat(pq.examine()).isEqualTo('i');
        assertThat(pq.examine()).isEqualTo('i');



        Iterator it1 = pq.iterator();
        assertThat(it1.hasNext()).isEqualTo(true);

        int count = 0;
        while (it1.hasNext()) {
            it1.next();
            count++;
        }
        Throwable thrown3 = catchThrowable(() -> it1.next());
        assertThat(thrown3).isInstanceOf(OutOfBounds.class);
        assertThat(it1.hasNext()).isEqualTo(false);

        char[] array1 = new char[10];
        char[] array2 = new char[10];
        char[] expectedArray = {'i', 'j', 'g', 'h', 'e', 'f', 'c', 'd', 'a', 'b' };

        for (int i = 0; i < 10; i ++) {
            Throwable thrown11 = catchThrowable(()->pq.examine());
            assertThat(thrown11).doesNotThrowAnyException();
            array1[i] = (char)pq.examine();
            array2[i] = (char)pq.dequeue();
        }
        assertThat(array1).isEqualTo(expectedArray);
        assertThat(array2).isEqualTo(expectedArray);

        for (int i = 0; i < 10; i ++) {
            pq.enqueue((char)('a' + i), i / 2);
        }
        for (int i = 0; i < 10; i ++) {
            Throwable thrown11 = catchThrowable(()->pq.dequeue());
            assertThat(thrown11).doesNotThrowAnyException();
        }




        Throwable thrown4 = catchThrowable(() -> pq.examine());
        assertThat(thrown4).isInstanceOf(Underflow.class);
        Throwable thrown5 = catchThrowable(() -> pq.dequeue());
        assertThat(thrown5).isInstanceOf(Underflow.class);

    }

    @Test
    @DisplayName("Moch method calls in marking machine")
    public void testMochMethodCalls() {
        Throwable thrown1 = catchThrowable(()->pq.examine());
        assertThat(thrown1).isInstanceOf(Underflow.class);

        pq.enqueue('A', 5);
        assertThat(pq.isEmpty()).isEqualTo(false);
        assertThat(pq.examine()).isEqualTo('A');
        pq.enqueue('B', 9);
        Throwable thrown2 = catchThrowable(()->pq.enqueue('G', -3));
        assertThat(thrown2).isInstanceOf(IllegalValue.class);
        pq.enqueue('E', 50);
        pq.enqueue('C', 2);
        pq.enqueue('F', 1);
        pq.enqueue('D', 11);

        assertThat(pq.examine()).isEqualTo('E');
        assertThat(pq.dequeue()).isEqualTo('E');
        assertThat(pq.examine()).isEqualTo('D');
        assertThat(pq.dequeue()).isEqualTo('D');
        assertThat(pq.examine()).isEqualTo('B');
        assertThat(pq.dequeue()).isEqualTo('B');
        assertThat(pq.examine()).isEqualTo('A');
        assertThat(pq.dequeue()).isEqualTo('A');
        assertThat(pq.examine()).isEqualTo('C');
        assertThat(pq.dequeue()).isEqualTo('C');
        assertThat(pq.examine()).isEqualTo('F');
        assertThat(pq.dequeue()).isEqualTo('F');

        thrown1 = catchThrowable(()->pq.examine());
        assertThat(thrown1).isInstanceOf(Underflow.class);
        thrown1 = catchThrowable(()->pq.examine());
        assertThat(thrown1).isInstanceOf(Underflow.class);

        pq.enqueue('A', 5);
        assertThat(pq.isEmpty()).isEqualTo(false);
        assertThat(pq.examine()).isEqualTo('A');
        pq.enqueue('B', 9);
        thrown2 = catchThrowable(()->pq.enqueue('G', -3));
        assertThat(thrown2).isInstanceOf(IllegalValue.class);
        pq.enqueue('E', 50);
        pq.enqueue('C', 2);
        pq.enqueue('F', 1);
        pq.enqueue('D', 11);

        assertThat(pq.examine()).isEqualTo('E');
        assertThat(pq.dequeue()).isEqualTo('E');
        assertThat(pq.examine()).isEqualTo('D');
        assertThat(pq.dequeue()).isEqualTo('D');
        assertThat(pq.examine()).isEqualTo('B');
        assertThat(pq.dequeue()).isEqualTo('B');
        assertThat(pq.examine()).isEqualTo('A');
        assertThat(pq.dequeue()).isEqualTo('A');
        assertThat(pq.examine()).isEqualTo('C');
        assertThat(pq.dequeue()).isEqualTo('C');
        assertThat(pq.examine()).isEqualTo('F');
        assertThat(pq.dequeue()).isEqualTo('F');

        thrown1 = catchThrowable(()->pq.examine());
        assertThat(thrown1).isInstanceOf(Underflow.class);
        thrown1 = catchThrowable(()->pq.examine());
        assertThat(thrown1).isInstanceOf(Underflow.class);


    }
}
