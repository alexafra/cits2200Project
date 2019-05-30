package project;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class getShortestPathTest {

    private MyCITS2200Project graph10;
    private MyCITS2200Project graph11;
    private MyCITS2200Project graph12;
    private MyCITS2200Project graph13;
    private MyCITS2200Project graph14;
    private MyCITS2200Project graph15;
    private MyCITS2200Project graph16;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init() {
        graph10 = new MyCITS2200Project();
        graph11 = new MyCITS2200Project();
        graph12 = new MyCITS2200Project();
        graph13 = new MyCITS2200Project();
        graph14 = new MyCITS2200Project();
        graph15 = new MyCITS2200Project();
        graph16 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        CITS2200ProjectTester.loadEdges(graph10, pathBase + 10 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph11, pathBase + 11 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph12, pathBase + 12 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph13, pathBase + 13 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph14, pathBase + 14 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph15, pathBase + 15 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph16, pathBase + 16 + pathSuffix);
    }

    @Test
    @DisplayName("Test Simple AB-BC Graph")
    public void testGetShortestEmpty() {
        assertThat(graph10.getShortestPath("a", "c")).isEqualTo(2);
        assertThat(graph10.getShortestPath("a", "b")).isEqualTo(1);
    }

    @Test
    @DisplayName("Test AB-BC-AC Graph")
    public void testGetShortestSingle() {
        assertThat(graph11.getShortestPath("a", "c")).isEqualTo(1);
        assertThat(graph11.getShortestPath("a", "d")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Test Simple No Path Graph")
    public void testGetShortestSmall() {

        assertThat(graph12.getShortestPath("a", "c")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Vertex connected to itself")
    public void testConnectedToItself() {
        //THE SHORTEST PATH FROM A VERTEX TO ITSELF GIVES 0.
        assertThat(graph13.getShortestPath("a","a")).isEqualTo(0);
        //TESTING WHAT HAPPENS WHEN NON-EXISTENT VERTICES ARE CALLED
        assertThat(graph13.getShortestPath("d", "f")).isEqualTo(-1);
        //TESTING WHAT HAPPENS WHEN EMPTY STRINGS ARE ENTERED
        assertThat(graph13.getShortestPath("","")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Two Possible Paths Test")
    public void testTwoPossiblePaths() {
        assertThat(graph13.getShortestPath("a", "d")).isEqualTo(1);
    }

    @Test
    @DisplayName("Unfinished Edge")
    public void testUnfinishedEdge() {
        assertThat(graph14.getShortestPath("a", "c")).isEqualTo(2);
        assertThat(graph14.getShortestPath("a", "d")).isEqualTo(-1);
        //TESTING VERTICES CONNECTED TO EMPTY STRINGS
        assertThat(graph14.getShortestPath("d", "")).isEqualTo(-1);
        assertThat(graph14.getShortestPath("d","a")).isEqualTo(1);
    }

    @Test
    @DisplayName("Larger Graph")
    public void testLargerGraph() {
        assertThat(graph15.getShortestPath("a","h")).isEqualTo(2);
        assertThat(graph15.getShortestPath("h", "a")).isEqualTo(-1);
        assertThat(graph15.getShortestPath("a", "g")).isEqualTo(4);
    }

    @Test
    @DisplayName("Test Empty Graph")
    public void testEmpty() {
        assertThat(graph16.getShortestPath("a", "b")).isEqualTo(-1);
    }



}
