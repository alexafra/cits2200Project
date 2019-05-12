package Labs.week5;


import Labs.CITS2200.OutOfBounds;
import Labs.CITS2200.WindowLinked;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ListLinkedTest {
    @DisplayName("Test empty list")
    @Test
    public void testEmptyList() {
        ListLinked list1 = new ListLinked();
        assertThat(list1.isEmpty()).isTrue();
        WindowLinked window1 = new WindowLinked();
        assertThat(list1.isAfterLast(window1)).isFalse();
        assertThat(list1.isBeforeFirst(window1)).isFalse();

        list1.beforeFirst(window1);
        list1.insertAfter(5, window1);
        Throwable thrown1 = catchThrowable(() -> list1.previous(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);
        thrown1 = catchThrowable(() -> list1.examine(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);

        thrown1 = catchThrowable(() -> list1.next(window1));
        assertThat(thrown1).doesNotThrowAnyException();
        thrown1 = catchThrowable(() -> list1.examine(window1));
        assertThat(thrown1).doesNotThrowAnyException();
        assertThat(list1.examine(window1)).isEqualTo(5);

        list1.next(window1);
        thrown1 = catchThrowable(()-> list1.examine(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);

        thrown1 = catchThrowable(()-> list1.replace(10, window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);

        thrown1 = catchThrowable(()-> list1.delete(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);

        thrown1 = catchThrowable(()-> list1.next(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);

        list1.previous(window1);
        assertThat(list1.examine(window1)).isEqualTo(5);

        assertThat(list1.replace(10, window1)).isEqualTo(5);
        assertThat(list1.examine(window1)).isEqualTo(10);
        assertThat(list1.delete(window1)).isEqualTo(10);
        assertThat(list1.isAfterLast(window1)).isTrue();
        assertThat(list1.isBeforeFirst(window1)).isFalse();
        assertThat(list1.isEmpty()).isTrue();

        list1.insertBefore(5, window1);
        assertThat(list1.isEmpty()).isFalse();
        thrown1 = catchThrowable(()-> list1.next(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);
        thrown1 = catchThrowable(()-> list1.examine(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);

        list1.previous(window1);
        assertThat(list1.examine(window1)).isEqualTo(5);
        list1.insertBefore(4, window1);
        list1.previous(window1);
        assertThat(list1.examine(window1)).isEqualTo(4);

        assertThat(list1.delete(window1)).isEqualTo(4);
        assertThat(list1.delete(window1)).isEqualTo(5);
        thrown1 = catchThrowable(() -> list1.delete(window1));
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);
    }
}
