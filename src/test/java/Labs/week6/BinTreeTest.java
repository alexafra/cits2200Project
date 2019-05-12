package Labs.week6;

import Labs.CITS2200.Iterator;
import Labs.CITS2200.OutOfBounds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@DisplayName("BinTree Test Suit")
public class BinTreeTest {



    @Test
    @DisplayName("Testing equal for one node max trees")
    public void testEqualsSingleNode() {
        BinTree<String> binTree1 = new BinTree<String>();
        BinTree<String> binTree2 = new BinTree<String>();
        assertThat(binTree1.equals(binTree2)).isEqualTo(true);

        BinTree binTree3 = new BinTree<String>("FirstElement", new BinTree<String>(), new BinTree<String>());
        BinTree binTree4 = new BinTree<String>("FirstElement", new BinTree<String>(), new BinTree<String>());
        assertThat(binTree3.equals(binTree4)).isEqualTo(true);

        BinTree binTree5 = new BinTree<String>("FirstElement", new BinTree<String>(), new BinTree<String>());
        BinTree binTree6 = new BinTree<String>("SecondElement", new BinTree<String>(), new BinTree<String>());
        assertThat(binTree5.equals(binTree6)).isEqualTo(false);

        BinTree binTree7 = new BinTree<String>(null, new BinTree<String>(), new BinTree<String>());
        BinTree binTree8 = new BinTree<String>("SecondElement", new BinTree<String>(), new BinTree<String>());
        assertThat(binTree7.equals(binTree8)).isEqualTo(false);

        BinTree binTree9 = new BinTree<Integer>(5, new BinTree<Integer>(), new BinTree<Integer>());
        BinTree binTree10 = new BinTree<String>("SecondElement", new BinTree<String>(), new BinTree<String>());
        assertThat(binTree9.equals(binTree10)).isEqualTo(false);

        BinTree binTree11 = new BinTree<Integer>();
        BinTree binTree12 = new BinTree<String>();
        assertThat(binTree11.equals(binTree12)).isEqualTo(true);

        BinTree binTree13 = new BinTree<Integer>(null, new BinTree<Integer>(), new BinTree<Integer>());
        BinTree binTree14 = new BinTree<String>(null, new BinTree<String>(), new BinTree<String>());
        assertThat(binTree13.equals(binTree14)).isEqualTo(true);

        BinTree binTree15 = new BinTree<Integer>(0, new BinTree<Integer>(), new BinTree<Integer>());
        BinTree binTree16 = new BinTree<String>(null, new BinTree<String>(), new BinTree<String>());
        assertThat(binTree15.equals(binTree16)).isEqualTo(false);

    }

    @Test
    @DisplayName("Testing iterator for one node max trees")
    public void testIteratorSingleNode() {
        BinTree<String> binTree1 = new BinTree<String>();
        Iterator it1 = binTree1.iterator();
        assertThat(it1.hasNext()).isFalse();
        Throwable thrown1 = catchThrowable(() -> it1.next());
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);

        BinTree binTree2 = new BinTree<String>("FirstElement", new BinTree<String>(), new BinTree<String>());
        Iterator it2 = binTree2.iterator();
        assertThat(it2.hasNext()).isTrue();
        assertThat(it2.next()).isEqualTo("FirstElement");
        assertThat(it2.hasNext()).isFalse();
        Throwable thrown2 = catchThrowable(() -> it2.next());
        assertThat(thrown2).isInstanceOf(OutOfBounds.class);

        BinTree binTree3 = new BinTree<String>(null, new BinTree<String>(), new BinTree<String>());
        Iterator it3 = binTree3.iterator();
        assertThat(it3.hasNext()).isTrue();
        assertThat(it3.next()).isNull();
        assertThat(it3.hasNext()).isFalse();
        Throwable thrown3 = catchThrowable(() -> it3.next());
        assertThat(thrown3).isInstanceOf(OutOfBounds.class);

        BinTree binTree4 = new BinTree<Integer>(0, new BinTree<Integer>(), new BinTree<Integer>());
        Iterator it4 = binTree4.iterator();
        assertThat(it4.hasNext()).isTrue();
        assertThat(it4.next()).isEqualTo(0);
        assertThat(it4.hasNext()).isFalse();
        Throwable thrown4 = catchThrowable(() -> it4.next());
        assertThat(thrown4).isInstanceOf(OutOfBounds.class);
    }

    @Test
    @DisplayName("testing equals for larger trees")
    public void testEqualsForLargeTrees() {
        BinTree<Integer> t131 = new BinTree<Integer>(5, new BinTree(), new BinTree());
        BinTree<Integer> t121 = new BinTree<Integer>(20, t131, new BinTree());
        BinTree<Integer> t122 = new BinTree<Integer>(13, new BinTree(), new BinTree());
        BinTree<Integer> t111 = new BinTree<Integer>(10, t121, t122);

        BinTree<Integer> t231 = new BinTree<Integer>(5, new BinTree(), new BinTree());
        BinTree<Integer> t221 = new BinTree<Integer>(20, t231, new BinTree());
        BinTree<Integer> t222 = new BinTree<Integer>(13, new BinTree(), new BinTree());
        BinTree<Integer> t211 = new BinTree<Integer>(10, t221, t222);

        assertThat(t111.equals(t111)).isEqualTo(true);
        assertThat(t111.equals(t211)).isEqualTo(true);

        BinTree<Integer> t331 = new BinTree<Integer>(5, new BinTree(), new BinTree());
        BinTree<Integer> t321 = new BinTree<Integer>(20, t331, new BinTree());
        BinTree<Integer> t322 = new BinTree<Integer>(13, new BinTree(), new BinTree());
        BinTree<Integer> t311 = new BinTree<Integer>(11, t321, t322);

        assertThat(t111.equals(t311)).isEqualTo(false);

        BinTree<String> t431 = new BinTree<String>("a b", new BinTree(), new BinTree());
        BinTree<String> t421 = new BinTree<String>("c d", t431, new BinTree());
        BinTree<String> t422 = new BinTree<String>("e f", new BinTree(), new BinTree());
        BinTree<String> t411 = new BinTree<String>(null, t421, t422);

        assertThat(t111.equals(t411)).isEqualTo(false);

        BinTree<Integer> t532 = new BinTree<Integer>(5, new BinTree(), new BinTree());
        BinTree<Integer> t521 = new BinTree<Integer>(20, new BinTree(), t532);
        BinTree<Integer> t522 = new BinTree<Integer>(13, new BinTree(), new BinTree());
        BinTree<Integer> t511 = new BinTree<Integer>(10, t521, t522);

        assertThat(t111.equals(t511)).isEqualTo(false);

        BinTree<String> t631 = new BinTree<String>(null, new BinTree(), new BinTree());
        BinTree<String> t621 = new BinTree<String>(null, t631, new BinTree());
        BinTree<String> t622 = new BinTree<String>(null, new BinTree(), new BinTree());
        BinTree<String> t611 = new BinTree<String>(null, t621, t622);

        assertThat(t611.equals(t611)).isEqualTo(true);

        BinTree<Integer> t731 = new BinTree<Integer>(null, new BinTree(), new BinTree());
        BinTree<Integer> t721 = new BinTree<Integer>(null, t731, new BinTree());
        BinTree<Integer> t722 = new BinTree<Integer>(null, new BinTree(), new BinTree());
        BinTree<Integer> t711 = new BinTree<Integer>(null, t721, t722);

        assertThat(t611.equals(t711)).isEqualTo(true);
    }


    @Test
    @DisplayName("testing iterator for larger trees")
    public void testIteratorForLargeTrees() {
        BinTree<Integer> t131 = new BinTree<Integer>(5, new BinTree(), new BinTree());
        BinTree<Integer> t121 = new BinTree<Integer>(20, t131, new BinTree());
        BinTree<Integer> t122 = new BinTree<Integer>(13, new BinTree(), new BinTree());
        BinTree<Integer> t1 = new BinTree<Integer>(10, t121, t122);

        List<Integer> expectedValues1 = new ArrayList<Integer>(Arrays.asList(5, 10, 20, 13));

        Iterator it1 = t1.iterator();
        assertThat(it1.hasNext()).isTrue();
        assertThat(expectedValues1.remove(it1.next())).isTrue();
        assertThat(it1.hasNext()).isTrue();
        assertThat(expectedValues1.remove(it1.next())).isTrue();
        assertThat(it1.hasNext()).isTrue();;
        assertThat(expectedValues1.remove(it1.next())).isTrue();
        assertThat(it1.hasNext()).isTrue();
        assertThat(expectedValues1.remove(it1.next())).isTrue();
        assertThat(it1.hasNext()).isFalse();
        assertThat(expectedValues1.isEmpty());
        Throwable thrown1 = catchThrowable(() -> it1.next());
        assertThat(thrown1).isInstanceOf(OutOfBounds.class);


        BinTree<String> t231 = new BinTree<String>("a b", new BinTree(), new BinTree());
        BinTree<String> t221 = new BinTree<String>("c d", t231, new BinTree());
        BinTree<String> t222 = new BinTree<String>("e f", new BinTree(), new BinTree());
        BinTree<String> t2 = new BinTree<String>(null, t221, t222);

        List<String> expectedValues2 = new ArrayList<String>(Arrays.asList("a b", "c d", "e f", null));

        Iterator it2 = t2.iterator();
        assertThat(it2.hasNext()).isTrue();
        assertThat(expectedValues2.remove(it2.next())).isTrue();
        assertThat(it2.hasNext()).isTrue();
        assertThat(expectedValues2.remove(it2.next())).isTrue();
        assertThat(it2.hasNext()).isTrue();;
        assertThat(expectedValues2.remove(it2.next())).isTrue();
        assertThat(it2.hasNext()).isTrue();
        assertThat(expectedValues2.remove(it2.next())).isTrue();
        assertThat(it2.hasNext()).isFalse();
        assertThat(expectedValues2.isEmpty());
        Throwable thrown2 = catchThrowable(() -> it2.next());
        assertThat(thrown2).isInstanceOf(OutOfBounds.class);


        BinTree<Integer> t331 = new BinTree<Integer>(null, new BinTree(), new BinTree());
        BinTree<Integer> t321 = new BinTree<Integer>(null, t331, new BinTree());
        BinTree<Integer> t322 = new BinTree<Integer>(null, new BinTree(), new BinTree());
        BinTree<Integer> t3 = new BinTree<Integer>(null, t321, t322);

        List<Integer> expectedValues3 = new ArrayList<Integer>(Arrays.asList(null, null, null, null));

        Iterator it3 = t3.iterator();
        assertThat(it3.hasNext()).isTrue();
        assertThat(expectedValues3.remove(it3.next())).isTrue();
        assertThat(it3.hasNext()).isTrue();
        assertThat(expectedValues3.remove(it3.next())).isTrue();
        assertThat(it3.hasNext()).isTrue();;
        assertThat(expectedValues3.remove(it3.next())).isTrue();
        assertThat(it3.hasNext()).isTrue();
        assertThat(expectedValues3.remove(it3.next())).isTrue();
        assertThat(it3.hasNext()).isFalse();
        assertThat(expectedValues3.isEmpty());
        Throwable thrown3 = catchThrowable(() -> it3.next());
        assertThat(thrown3).isInstanceOf(OutOfBounds.class);

    }



}
