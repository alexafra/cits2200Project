package project;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import java.util.ArrayList;

//src/test/projectTestData/edge
//src/test/projectTestData/matrix

//Perhaps test from the start => end point

//Test empty ans null strings
public class AddEdgesTest {
    private MyCITS2200Project graph0;
    private MyCITS2200Project graph1;
    private MyCITS2200Project graph2;
    private MyCITS2200Project graph3;
    private MyCITS2200Project graph4;
    private MyCITS2200Project graph5;
    private MyCITS2200Project graph6;
    private MyCITS2200Project graph44;
    private MyCITS2200Project graph55;
    private MyCITS2200Project graph66;
    private MyCITS2200Project graph77;
    private MyCITS2200Project graph88;
    private MyCITS2200Project graph99;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init () {
        //graph0 = new MyCITS2200Project();
        graph1 = new MyCITS2200Project();
        graph2 = new MyCITS2200Project();
        graph3 = new MyCITS2200Project();
        graph4 = new MyCITS2200Project();
        graph5 = new MyCITS2200Project();
        graph6 = new MyCITS2200Project();
        graph44 = new MyCITS2200Project();
        graph55 = new MyCITS2200Project();
        graph66 = new MyCITS2200Project();
        graph77 = new MyCITS2200Project();
        graph88 = new MyCITS2200Project();
        graph99 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        //long startTime = System.nanoTime();

        //CITS2200ProjectTester.loadEdges(graph0, pathBase + 0 + pathSuffix);

//        long estimatedTime = System.nanoTime() - startTime;
//        System.out.println(estimatedTime);

        CITS2200ProjectTester.loadEdges(graph1, pathBase + 1 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph2, pathBase + 2 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph3, pathBase + 3 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph4, pathBase + 4 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph5, pathBase + 5 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph6, pathBase + 6 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph44, pathBase + 44 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph55, pathBase + 55 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph66, pathBase + 66 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph77, pathBase + 77 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph88, pathBase + 88 + pathSuffix);
    }

    @Test
    @DisplayName("Test Empty graph")
    public void testAddEdgesEmpty () {

        graph0 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        long startTime = System.nanoTime();

        CITS2200ProjectTester.loadEdges(graph0, pathBase + 0 + pathSuffix);

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Estimated time is:" + estimatedTime);

        assertThat(graph0.adjacencyList.size()).isEqualTo(0);
        assertThat(graph0.edgeMatrix).isEqualTo(new int[0][0]);
        assertThat(graph0.intToStrMap.size()).isEqualTo(0);
        assertThat(graph0.strToIntMap.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test Single Node graph")
    public void testAddEdgesSingle() {
        assertThat(graph1.adjacencyList.size()).isEqualTo(0);
        assertThat(graph1.edgeMatrix).isEqualTo(new int[0][0]);
        assertThat(graph1.intToStrMap.size()).isEqualTo(0);
        assertThat(graph1.strToIntMap.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test Two Node graph")
    public void testAddEdgesDouble() {
        Integer[][] expectedArray = {{1}, {}};
        List<List<Integer>> expectedList = new ArrayList<>();
        for (Integer[] array : expectedArray) {
            expectedList.add(Arrays.asList(array));
        }
        assertThat(graph2.adjacencyList.size()).isEqualTo(2);
        assertThat(graph2.adjacencyList.equals(expectedList));

        MyCITS2200Project graph = new MyCITS2200Project();
        graph.addEdge("a","b");
        graph.addEdge("b", "a");
        Integer[][] expectedArray2 = {{1}, {0}};
        List<List<Integer>> expectedList2 = new ArrayList<>();
        for (Integer[] array : expectedArray2) {
            expectedList2.add(Arrays.asList(array));
        }
        assertThat(graph.adjacencyList.size()).isEqualTo(2);
        assertThat(graph.adjacencyList.equals(expectedList2)).isTrue();
    }

    @Test
    @DisplayName("Test Three Node - odd No. graph")
    public void testAddEdgesThreeNodes() {
        Integer[][] expectedArray = {{1}, {0}};
        List<List<Integer>> expectedList = new ArrayList<>();
        for (Integer[] array : expectedArray) {
            expectedList.add(Arrays.asList(array));
        }
        assertThat(graph5.adjacencyList.size()).isEqualTo(2);
        assertThat(graph5.adjacencyList.equals(expectedList)).isTrue();
        assertThat(graph6.adjacencyList.size()).isEqualTo(2);
        assertThat(graph6.adjacencyList.equals(expectedList)).isTrue();
    }

    @Test
    @DisplayName("Test empty strings")
    public void testEmptyNullStrings() {
        Integer[][] expectedArray = {{0}};
        List<List<Integer>> expectedList = new ArrayList<>();
        MyCITS2200Project graph = new MyCITS2200Project();
        graph.addEdge("","");
        graph.addEdge(null, null);

        assertThat(graph.adjacencyList.size()).isEqualTo(0);
        assertThat(graph.adjacencyList.equals(expectedList)).isTrue();
    }

    @Test
    @DisplayName("Test node connected to itself")
    public void testNodeConnectedToItself() {
        Integer[][] expectedArray = {{0}};
        List<List<Integer>> expectedList = new ArrayList<>();
        for (Integer[] array : expectedArray) {
            expectedList.add(Arrays.asList(array));
        }
        MyCITS2200Project graph = new MyCITS2200Project();
        graph.addEdge("a","a");

        assertThat(graph.adjacencyList.size()).isEqualTo(1);
        assertThat(graph.adjacencyList.equals(expectedList)).isTrue();
    }
}

//Shouldnt have duplicate edge input but not sure
//    @Test
//    @DisplayName("Test Repeat Edges Easy")
//    public void testRepeatEdgesEasy() {
//        Integer[][] expectedArray = {{1}, {0}};
//        List<List<Integer>> expectedList = new ArrayList<>();
//        for (Integer[] array : expectedArray) {
//            expectedList.add(Arrays.asList(array));
//        }
//        assertThat(graph35.adjacencyList.size()).isEqualTo(2);
//        assertThat(graph35.adjacencyList.equals(expectedList)).isTrue();
//        assertThat(graph36.adjacencyList.size()).isEqualTo(2);
//        assertThat(graph36.adjacencyList.equals(expectedList)).isTrue();
//    }
//
//    @Test
//    @DisplayName("Test Repeat Edges Hard")
//    public void testRepeatEdgesHard() {
//        Integer[][] expectedArray = {{1}, {0}};
//        List<List<Integer>> expectedList = new ArrayList<>();
//        for (Integer[] array : expectedArray) {
//            expectedList.add(Arrays.asList(array));
//        }
//        assertThat(graph35.adjacencyList.size()).isEqualTo(2);
//        assertThat(graph35.adjacencyList.equals(expectedList)).isTrue();
//        assertThat(graph36.adjacencyList.size()).isEqualTo(2);
//        assertThat(graph36.adjacencyList.equals(expectedList)).isTrue();
//    }

//        //graph3.5
//        assertThat(graph1.adjacencyList.size()).isEqualTo(2);
//        assertThat(graph1.intToStrMap.size()).isEqualTo(0);
//        assertThat(graph1.strToIntMap.size()).isEqualTo(0);
