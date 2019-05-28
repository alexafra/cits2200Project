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
//    private MyCITS2200Project graph13;
//    private MyCITS2200Project graph14;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init() {
        graph10 = new MyCITS2200Project();
        graph11 = new MyCITS2200Project();
        graph12 = new MyCITS2200Project();
//        graph3 = new MyCITS2200Project();
//        graph4 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        CITS2200ProjectTester.loadEdges(graph10, pathBase + 10 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph11, pathBase + 11 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph12, pathBase + 12 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph3, pathBase + 13 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph4, pathBase + 14 + pathSuffix);
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

//    @Test
//    @DisplayName("Test Large Graph")
//    public void testGetShortestLarge() {
//
//    }



}
