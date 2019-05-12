package Labs.week3.Assessed;

import Labs.CITS2200.Overflow;
import Labs.CITS2200.Underflow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("StackBlock Test Suit")
public class StackBlockTest {
    private StackBlock stack;
    private StackBlock stack2;

    @BeforeEach
    public void init() {
        stack = new StackBlock();
        stack2 = new StackBlock();
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
    @DisplayName("It should pop and push to the top of the stack only")
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
    @DisplayName("It should generate an Overflow exception when stack limit it reached")
    public void pushFullStackTest() {
        for (int i = 0; i < 10; i++) { stack.push(new Object());}
        assertThatThrownBy(() -> stack.push( new Object() )).isInstanceOf(Exception.class);

        assertThatExceptionOfType(Overflow.class).isThrownBy(() -> { stack.push( new Object() ); });

        Throwable thrown = catchThrowable(() -> { stack.push( new Object() ); });

        assertThat(thrown).isInstanceOf(Overflow.class);

    }

    @Test
    @DisplayName("It should add items to the end of the list if stack limit not reached")
    public void pushNormalStackTest() {
        Object obj = 10;
        Throwable thrown = catchThrowable(() -> {stack.push(obj); });
        assertThat(thrown).doesNotThrowAnyException();
        assertThat(stack.pop()).isEqualTo(10);
    }

    @Test
    @DisplayName("isFull should return true when full and false when not")
    public void testIsFull() {
        assertThat(stack.isFull()).isEqualTo(false);
        stack.push(5);
        assertThat(stack.isFull()).isEqualTo(false);
        for (int i = 0; i < 9; i++ ) {
            stack.push(10);
        }
        assertThat(stack.isFull()).isEqualTo(true);
    }


}
