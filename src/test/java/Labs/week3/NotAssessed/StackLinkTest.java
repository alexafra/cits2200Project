package Labs.week3.NotAssessed;

import Labs.CITS2200.Underflow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("StackLink Test Suit")
public class StackLinkTest {
    private StackLink stack;
    private StackLink stack2;

    @BeforeEach
    public void init() {
        stack = new StackLink();
        stack2 = new StackLink();
    }

    @AfterEach
    public void cleanup() {
        stack = null;
        stack2 = null;
    }

    @Test
    @DisplayName("It should generate an Underflow exception when popping from an empty stack")
    public void popExceptionsTest() {
        Throwable thrown = catchThrowable(() -> { stack.pop(); });
        assertThat(thrown).isInstanceOf(Underflow.class);

        stack.push( new Object() );
        assertThatCode(() -> { stack.pop(); }).doesNotThrowAnyException();

        stack2.push(new Object());
        Throwable thrown2 = catchThrowable(() -> { stack2.pop(); });
        assertThat(thrown2).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("It should pop and push to the top of the stack")
    public void manipulateTopTest() {
        Throwable thrown = catchThrowable(() -> { stack.pop(); });
        assertThat(thrown).isInstanceOf(Underflow.class);

        for (int i = 0; i < 5; i++) {stack.push(i);}
        for (int i = 0; i < 5; i++) {
            Object number = stack.pop();
            assertThat(number).isEqualTo(4-i);
        }
    }

    @Test
    @DisplayName("It should not generate an Overflow exception")
    public void pushFullStackTest() {
        for (int i = 0; i < 10; i++) { stack.push(new Object());}
        Throwable thrown = catchThrowable( () -> { stack.push( new Object() ); } );
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("It should add items to the end of the list if stack limit not reached")
    public void pushNormalStackTest() {
        stack.push(1);
        stack.push(2);

        Throwable thrown = catchThrowable(() -> {stack.push(10); });
        assertThat(thrown).doesNotThrowAnyException();
        assertThat(stack.pop()).isEqualTo(10);
    }

    @Test
    @DisplayName("isFull should return true when full and false when not")
    public void testIsFull() {
        assertThat(stack.isFull()).isEqualTo(false);
        stack.push(5);
        assertThat(stack.isFull()).isEqualTo(false);
        for (int i = 0; i < 9; i++ ) { stack.push(10); }
        assertThat(stack.isFull()).isEqualTo(false);
    }


}
