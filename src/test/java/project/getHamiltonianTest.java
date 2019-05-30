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


        CITS2200Project graph = new MyCITS2200Project();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("d", "e");
        graph.addEdge("e", "f");
        graph.addEdge("f", "g");
        graph.addEdge("g", "h");
        graph.addEdge("h", "i");
        graph.addEdge("i", "j");
        graph.addEdge("j", "k");
        graph.addEdge("k", "l");
        graph.addEdge("l", "m");
        graph.addEdge("m", "n");
        graph.addEdge("n", "o");
        graph.addEdge("o", "p");
        graph.addEdge("p", "q");
        graph.addEdge("q", "r");
        graph.addEdge("r", "s");
        graph.addEdge("s", "t");
        graph.addEdge("t", "u");

        String[] expectedPath = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u"};
        assertThat(graph.getHamiltonianPath()).isEqualTo(expectedPath);
    }



}