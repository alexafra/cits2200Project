package project;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class sampleTest {

    private MyCITS2200Project graph;
//    private MyCITS2200Project graph31;
//    private MyCITS2200Project graph32;
//    private MyCITS2200Project graph23;
//    private MyCITS2200Project graph24;
//    private MyCITS2200Project graph25;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init() {
        graph = new MyCITS2200Project();
//        graph31 = new MyCITS2200Project();
//        graph32 = new MyCITS2200Project();
//        graph23 = new MyCITS2200Project();
//        graph24 = new MyCITS2200Project();
//        graph25 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        CITS2200ProjectTester.loadEdges(graph, pathBase + "Sample" + pathSuffix);

//        CITS2200ProjectTester.loadEdges(graph31, pathBase + 31 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph32, pathBase + 32 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph23, pathBase + 23 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph24, pathBase + 24 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph25, pathBase + 25 + pathSuffix);
    }

    @Test
    @DisplayName("Test Shortest Path")
    public void testShortestPath() {

        assertThat(graph.getShortestPath("/wiki/Australia","/wiki/United+Kingdom")).isEqualTo(5);

    }

    @Test
    @DisplayName("Test Graph Centers")
    public void testGraphCenter() {

        String[] string1 = new String[] {"/wiki/Brazil"};

        long startTime = System.nanoTime();
        assertThat(graph.getCenters()).isEqualTo(string1);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Estimated time is:" + estimatedTime);

    }

    @Test
    @DisplayName("Test Strongly Connected")
    public void testStronglyConnected() {

        //THIS IS JUST A DUMMY STRING SO I CAN CHECK THE OUTPUT OF THE METHOD
        //IT WOULD HAVE BEEN TO HARD TO INPUT THE OTHER STRING, WHICH IS LIKE 800 EDGES...
        String[][] string2 = new String[][] {{"d", "c"},{"b", "a"}};

        assertThat(graph.getStronglyConnectedComponents()).isEqualTo(string2);
    }
}

