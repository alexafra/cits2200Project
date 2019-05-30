package project;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class getStronglyConnectedTest {

    private MyCITS2200Project graph30;
    private MyCITS2200Project graph31;
    private MyCITS2200Project graph32;
    private MyCITS2200Project graph33;
//    private MyCITS2200Project graph24;
//    private MyCITS2200Project graph25;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init() {
        graph30 = new MyCITS2200Project();
        graph31 = new MyCITS2200Project();
        graph32 = new MyCITS2200Project();
        graph33 = new MyCITS2200Project();
//        graph34 = new MyCITS2200Project();
//        graph35 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        CITS2200ProjectTester.loadEdges(graph30, pathBase + 30 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph31, pathBase + 31 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph32, pathBase + 32 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph33, pathBase + 33 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph34, pathBase + 34 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph35, pathBase + 35 + pathSuffix);
    }

    @Test
    @DisplayName("Test Simple AB Graph")
    public void testSimpleCenter() {

        String[][] string1 = new String[][] {{"a"}, {"b"}};

        assertThat(graph30.getStronglyConnectedComponents()).isEqualTo(string1);

    }

    @Test
    @DisplayName("Test Simple AB BA Graph")
    public void testLargerCenter() {

        String[][] string1 = new String[][] {{"b","a"}};

        assertThat(graph31.getStronglyConnectedComponents()).isEqualTo(string1);
    }

    @Test
    @DisplayName("Disconnected Graph AB BA CD DC")
    public void testDisconnectedGraph() {

        String[][] string2 = new String[][] {{"d", "c"},{"b", "a"}};

        assertThat(graph32.getStronglyConnectedComponents()).isEqualTo(string2);
    }

    @Test
    @DisplayName("No SSC Graph")
    public void testNoConnected() {
        String[][] string =  new String[][] {{"d"},{"c"},{"b"},{"a"}};

        assertThat(graph33.getStronglyConnectedComponents()).isEqualTo(string);
    }
}


