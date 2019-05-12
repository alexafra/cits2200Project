package Labs.week9;

import Labs.CITS2200.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PathImpTest {

    PathImp path;
    Graph graph0;
    Graph graph1;
    Graph graph2;
    Graph graph3;
    Graph graph4;
    Graph graph5;
    Graph graph6;

    @BeforeEach
    public void init () throws Exception {
        path = new PathImp();
        String fileBase = "src/test/testData/GraphInput";
        String postFix = ".txt";
        graph0 = Graph.readFile(fileBase + 0 + postFix, true, false);

        graph1 = Graph.readFile(fileBase + 1 +  postFix, true, true);
        graph2 = Graph.readFile(fileBase + 2 + postFix, true, true);
        graph3 = Graph.readFile(fileBase + 3 + postFix, true, true);
        graph4 = Graph.readFile(fileBase + 4 +  postFix, true, true);
        graph5 = Graph.readFile(fileBase + 5 +  postFix, true, true);
        graph6 = Graph.readFile(fileBase + 6 +  postFix, true, true);

    }

    @Test
    @DisplayName("Test Get Connected Tree")
    public void testGetConnectedTree () throws Exception {

        //No Nodes
        int weight = path.getMinSpanningTree(graph0);
        int[] distances = path.getShortestPaths(graph0, 0);
        int[] parent = path.getSubTree(graph0, 0);

        int[] expectedDistances = {0, 5, 5, 6, 6, 9, 8, 1, 5, 7};


        assertThat(weight).isEqualTo(36);
        assertThat(distances).isEqualTo(expectedDistances);
        //assertThat(parent).isEqualTo()

        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph0.toString());
        System.out.println("Weight of Minimum Spanning Tree: ");
        System.out.println(weight);
        System.out.println("Distances from node 0: ");
        System.out.println(Arrays.toString(distances));
        System.out.println("Graph Parent Array: ");
        System.out.println(Arrays.toString(parent));
        System.out.println();
    }

//
//        //Normal Small Graph
//        int[] parentArray31 = search.getConnectedTree(graph3, 0);
//        int[] expectedArray31 = {-1, 2, 0};
//        System.out.println("////////////////////");
//        System.out.println("Graph: ");
//        System.out.println(graph3.toString());
//        System.out.println("Graph Parent Array: ");
//        System.out.println(Arrays.toString(parentArray31));
//        System.out.println("Expected Parent Array: ");
//        System.out.println(Arrays.toString(expectedArray31));
//        System.out.println();
//        assertThat(parentArray31).isEqualTo(expectedArray31);

//    @Test
//    @DisplayName("Test Comparable")
//    public void testComparable()  {
//        PathImp.PQElement element1 = new PathImp.PQElement(5, 10);
//        PathImp.PQElement element2 = new PathImp.PQElement(5, 10);
//        PathImp.PQElement element3 = new PathImp.PQElement(5, 11);
//        PathImp.PQElement element1 = new PathImp.PQElement(5, 9);
//
//        assert
//    }
}

