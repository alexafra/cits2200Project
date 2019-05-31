package project;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class getHamiltonianTest {

    private MyCITS2200Project graph100;
    private MyCITS2200Project graph101;
    private MyCITS2200Project graph102;
    private MyCITS2200Project graph103;
    private MyCITS2200Project graph104;
//    private MyCITS2200Project graph105;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init() {
        graph100 = new MyCITS2200Project();
        graph101 = new MyCITS2200Project();
        graph102 = new MyCITS2200Project();
        graph103 = new MyCITS2200Project();
        graph104 = new MyCITS2200Project();
//        graph105 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        CITS2200ProjectTester.loadEdges(graph100, pathBase + "100" + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph101, pathBase + 101 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph102, pathBase + 102 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph103, pathBase + 103 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph104, pathBase + 104 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph105, pathBase + 105 + pathSuffix);
    }

    @Test
    @DisplayName("Test Graph With Hamiltonian")
    public void testHasHamiltonian() {

//        //Possible Hamiltonian path for this one...IF IT DOESNT PASS, JUST CHECK THAT IT CONTAINS digits 0-6.
//        //Any combination will do
//        String[] string1 = new String[] {"0", "1", "2", "3", "4", "5", "6"};
////
//        assertThat(graph100.getHamiltonianPath()).isEqualTo(string1);
//
//        //Trivial Hamiltonian, where there is only 2 vertices
//        String[] string2 = new String[] {"a", "b"};
//        assertThat(graph101.getHamiltonianPath()).isEqualTo(string2);
    }

    @Test
    @DisplayName("Test Single Node")
    public void testSingleNode() {
        CITS2200Project graph = new MyCITS2200Project();
        graph.addEdge("a", "a");
        String[] expectedPath = {"a"};
        assertThat(graph.getHamiltonianPath()).isEqualTo(expectedPath);
    }

    @Test
    @DisplayName("Test Two Nodes")
    public void testTwoNodes() {
        CITS2200Project graph1 = new MyCITS2200Project();
        graph1.addEdge("a", "b");
        String[] expectedPath1 = {"a", "b"};
        assertThat(graph1.getHamiltonianPath()).isEqualTo(expectedPath1);

        CITS2200Project graph2 = new MyCITS2200Project();
        graph1.addEdge("b", "a");
        String[] expectedPath2 = {"b", "a"};
        assertThat(graph1.getHamiltonianPath()).isEqualTo(expectedPath2);
    }

    @Test
    @DisplayName("Test Three Nodes")
    public void testThreeNodes() {
        CITS2200Project graph1 = new MyCITS2200Project();
        graph1.addEdge("a", "b");
        graph1.addEdge("b", "c");
        String[] expectedPath1 = {"a", "b", "c"};
        assertThat(graph1.getHamiltonianPath()).isEqualTo(expectedPath1);
    }

    @Test
    @DisplayName("Test Disconnected Graph")
    public void testDisconnectedNodes() {
        CITS2200Project graph1 = new MyCITS2200Project();
        graph1.addEdge("a", "b");
        graph1.addEdge("b", "c");
        graph1.addEdge("d", "e");
        String[] expectedPath1 = {};
        assertThat(graph1.getHamiltonianPath()).isEqualTo(expectedPath1);
    }

    @Test
    @DisplayName("Test Graph NO Hamiltonian")
    public void testNoHamiltonian() {

        CITS2200Project graph = new MyCITS2200Project();
        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        String[] expectedPath = {};
        assertThat(graph.getHamiltonianPath()).isEqualTo(expectedPath);
    }

    @Test
    @DisplayName("Test linear")
    public void testLiearNodes() {

        //20 1.821 s
        //19 930ms

        CITS2200Project graph1 = new MyCITS2200Project();
        CITS2200Project graph2 = new MyCITS2200Project();
        CITS2200Project graph3 = new MyCITS2200Project();
        CITS2200Project graph4 = new MyCITS2200Project();
        CITS2200Project graph5 = new MyCITS2200Project();

        CITS2200Project graph = new MyCITS2200Project();
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < i; j++) {
                if (i == j) continue;
                graph1.addEdge(String.valueOf('a' + i), String.valueOf ('a' + j));
                graph1.addEdge(String.valueOf('a' + j), String.valueOf ('a' + i));
            }
            long start1 = System.nanoTime();
            graph1.getHamiltonianPath();
            long finish1 = System.nanoTime();
            long timens1 = finish1 - start1;
            double time1 = ((double)timens1) / 1000000;
            System.out.println(time1);
        }

//            System.out.println("n: " + (i + 1) + "  time: " + time1 + "ms.");




//        graph1.addEdge("a", "b"); //1
//        graph1.addEdge("b", "c");
//        graph1.addEdge("c", "d");
//        graph1.addEdge("d", "e");
//
//        graph2.addEdge("a", "b"); //1
//        graph2.addEdge("b", "c");
//        graph2.addEdge("c", "d");
//        graph2.addEdge("d", "e");
//        graph2.addEdge("e", "f"); //5
//        graph2.addEdge("f", "g");
//        graph2.addEdge("g", "h");
//        graph2.addEdge("h", "i");
//        graph2.addEdge("i", "j");
//
//        graph3.addEdge("a", "b"); //1
//        graph3.addEdge("b", "c");
//        graph3.addEdge("c", "d");
//        graph3.addEdge("d", "e");
//        graph3.addEdge("e", "f"); //5
//        graph3.addEdge("f", "g");
//        graph3.addEdge("g", "h");
//        graph3.addEdge("h", "i");
//        graph3.addEdge("i", "j");
//        graph3.addEdge("j", "k"); //10
//        graph3.addEdge("k", "l");
//        graph3.addEdge("l", "m");
//        graph3.addEdge("m", "n");
//        graph3.addEdge("n", "o");
//
//        graph4.addEdge("a", "b"); //1
//        graph4.addEdge("b", "c");
//        graph4.addEdge("c", "d");
//        graph4.addEdge("d", "e");
//        graph4.addEdge("e", "f"); //5
//        graph4.addEdge("f", "g");
//        graph4.addEdge("g", "h");
//        graph4.addEdge("h", "i");
//        graph4.addEdge("i", "j");
//        graph4.addEdge("j", "k"); //10
//        graph4.addEdge("k", "l");
//        graph4.addEdge("l", "m");
//        graph4.addEdge("m", "n");
//        graph4.addEdge("n", "o");
//        graph4.addEdge("o", "p"); //15
//        graph4.addEdge("p", "q");
//        graph4.addEdge("q", "r");
//        graph4.addEdge("r", "s");
//        graph4.addEdge("s", "t");
//        graph4.addEdge("t", "u"); //20
//
//        graph5.addEdge("a", "b"); //1
//        graph5.addEdge("b", "c");
//        graph5.addEdge("c", "d");
//        graph5.addEdge("d", "e");
//        graph5.addEdge("e", "f"); //5
//        graph5.addEdge("f", "g");
//        graph5.addEdge("g", "h");
//        graph5.addEdge("h", "i");
//        graph5.addEdge("i", "j");
//        graph5.addEdge("j", "k"); //10
//        graph5.addEdge("k", "l");
//        graph5.addEdge("l", "m");
//        graph5.addEdge("m", "n");
//        graph5.addEdge("n", "o");
//        graph5.addEdge("o", "p"); //15
//        graph5.addEdge("p", "q");
//        graph5.addEdge("q", "r");
//        graph5.addEdge("r", "s");
//        graph5.addEdge("s", "t");// 19
//
//        String[] expectedPath1 = {"a", "b", "c", "d", "e"};
//        String[] expectedPath2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
//        String[] expectedPath3 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"};
//        String[] expectedPath4 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u"};
//
//        long start1 = System.nanoTime();
//        graph1.getHamiltonianPath();
//        long finish1 = System.nanoTime();
//
//        long start2 = System.nanoTime();
//        graph2.getHamiltonianPath();
//        long finish2 = System.nanoTime();
//        long start3 = System.nanoTime();
//        graph3.getHamiltonianPath();
//        long finish3 = System.nanoTime();
//        long start4 = System.nanoTime();
//        graph4.getHamiltonianPath();
//        long finish4 = System.nanoTime();
//        long start5 = System.nanoTime();
//        graph5.getHamiltonianPath();
//        long finish5 = System.nanoTime();
//
//        long timens1 = finish1 - start1;
//        double time1 = ((double)timens1) / 1000000;
//
//        long timens2 = finish2 - start2;
//        double time2 = ((double)timens2) / 1000000;
//
//        long timens3 = finish3 - start3;
//        double time3 = ((double)timens3) / 1000000;
//
//        long timens4 = finish4 - start4;
//        double time4 = ((double)timens4) / 1000000;
//
//        long timens5 = finish5 - start5;
//        double time5 = ((double)timens5) / 1000000;
//
//        System.out.println("size 5, time is: " + time1 + "ms.");
//        System.out.println("size 10, time is: " + time2 + "ms.");
//        System.out.println("size 15, time is: " + time3 + "ms.");
//        System.out.println("size 19, time is: " + time4 + "ms.");
//        System.out.println("size 20, time is: " + time5 + "ms.");




//        assertThat(graph.getHamiltonianPath()).isEqualTo(expectedPath1);
    }



}