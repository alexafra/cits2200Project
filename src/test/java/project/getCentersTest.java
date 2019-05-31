package project;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class getCentersTest {

    private MyCITS2200Project graph20;
    private MyCITS2200Project graph21;
    private MyCITS2200Project graph22;
    private MyCITS2200Project graph23;
    private MyCITS2200Project graph30;
//    private MyCITS2200Project graph25;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init() {
        graph20 = new MyCITS2200Project();
        graph21 = new MyCITS2200Project();
        graph22 = new MyCITS2200Project();
        graph23 = new MyCITS2200Project();
        graph30 = new MyCITS2200Project();
//        graph25 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        CITS2200ProjectTester.loadEdges(graph20, pathBase + 20 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph21, pathBase + 21 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph22, pathBase + 22 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph23, pathBase + 23 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph30, pathBase + 30 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph25, pathBase + 25 + pathSuffix);
    }

    @Test
    @DisplayName("Test Simple AB-BC Graph")
    public void testSimpleCenter() {

        String[] string1 = {"a"};

        long startTime = System.nanoTime();
        assertThat(graph20.getCenters()).isEqualTo(string1);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Estimated time is:" + estimatedTime);

    }

    @Test
    @DisplayName("Larger Graphs")
    public void testLargerCenter() {
        String[] string1 = {"b"};
        String[] string2 = {"b", "c"};


        assertThat(graph22.getCenters()).isEqualTo(string2);
        assertThat(graph21.getCenters()).isEqualTo(string1);

    }

    @Test
    @DisplayName("No Center")
    public void testNoCenter() {
        String[] string1 = {};

        long startTime = System.nanoTime();
        assertThat(graph23.getCenters()).isEqualTo(string1);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Estimated time is:" + estimatedTime);

    }
}

