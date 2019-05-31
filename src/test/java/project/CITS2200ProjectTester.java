package project;

import java.io.*;
import java.util.*;

public class CITS2200ProjectTester {

    public static CITS2200Project createFullGraph(int size, double density) {
        CITS2200Project graph = new MyCITS2200Project();
        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j ++) {
                if (i == j) continue;
                if (Math.random() > density) continue;
                graph.addEdge(String.valueOf('0' + i), String.valueOf ('0' + i + 1));
            }
        }
        return graph;
    }

    public static CITS2200Project createLinearGraph(int size, double density) {
        CITS2200Project graph = new MyCITS2200Project();
        for (int i = 0; i < size; i ++) {
            graph.addEdge(String.valueOf('0' + i), String.valueOf ('0' + i + 1));
        }
        return graph;
    }




    public static void loadEdges(CITS2200Project project, String path) {
        // The graph is in the following format:
        // Every pair of consecutive lines represent a directed edge.
        // The edge goes from the URL in the first line to the URL in the second line.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (reader.ready()) {
                String from = reader.readLine();
                String to = reader.readLine();
                //System.out.println("Adding edge from " + from + " to " + to);
                project.addEdge(from, to);
            }
        } catch (Exception e) {
            System.out.println("There was a problem:");
            System.out.println(e.toString());
        }
    }

    /*
        The aim of testing is to have two methods that can load graphs, one in matrix form and one in edge form
        Matrix form can be received from https://graphonline.ru/en/ but our files will have an additional number of vertices.
        Dome methods can be tested directly from graphonline, others we can use graph online for quick visualisation of the
        matrix.

        This should be able to load the graph in matrix form
     */
    public static void loadMatrix(CITS2200Project project, String path) {
        String delimiter = " ";

        try {
            BufferedReader file = new BufferedReader(new FileReader(path));
            String firstLine = file.readLine();
            int length = Integer.parseInt(firstLine);

            for (int i = 0; i < length; ++i) {
                StringTokenizer lineI = new StringTokenizer(file.readLine().trim(), delimiter);
                for (int j = 0; j < length; ++j) {
                    String currentToken = lineI.nextToken();
                    if (Integer.parseInt(currentToken) == 1) {
                        project.addEdge(String.valueOf((char) ('a' + i)), String.valueOf((char) ('a' + j)));
                    }
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("There was a problem:");
            System.out.println(e.toString());
        }

    }


    //Convert a\nb => edgeMatrix
    public static void main(String[] args) {

        //Ignore the next 5 lines, theyre necessary but meaningless
        long start = System.nanoTime();
        CITS2200Project graph = createFullGraph(10, 1);
        long finish = System.nanoTime();
        long timens = finish - start;
        double time = ((double)timens) / 1000000;

        //Hamiltonian Data
        //Includes the creation of the graph
        //repeats 3 times
        //Hamiltonian data
//        for (int j = 0; j < 3; j ++) {
//            for (int i = 1; i < 21; ++i) {
//
//
//                start = System.nanoTime();
//                graph = createFullGraph(i, 1);
//                graph.getHamiltonianPath();
//                finish = System.nanoTime();
//                timens = finish - start;
//                time = ((double)timens) / 1000000;
//                System.out.println(time);
//            }
//            System.out.println();
//            System.out.println();
//
//        }


//        graph.getShortestPath("0", String.valueOf(10)); //line is to put method in working memory. Otherwise meaningless
//



//        //get shortest Path Graph density is 0.2 Ran 3 time manually
//        for (int i = 0; i < 1000; ++i) {
//            start = System.nanoTime();
//            graph = createFullGraph(i, 0.2);
//            graph.getShortestPath("0", String.valueOf(i));
//            finish = System.nanoTime();
//            timens = finish - start;
//            time = ((double)timens) / 1000000;
//            System.out.println(time);
//        }
//
//
        //getCenters
        graph.getCenters(); //meaningless just placing inn working memory.
        for (int i = 0; i < 500; ++i) {
            start = System.nanoTime();
            graph = createFullGraph(i,0.2);
            graph.getCenters();
            finish = System.nanoTime();
            timens = finish - start;
            time = ((double)timens) / 1000000;
            System.out.println(time);
        }
////
        //get Strongly connected components
//        graph.getStronglyConnectedComponents();   //meaningless just placing inn working memory.
//        for (int i = 0; i < 1000; ++i) {
//            start = System.nanoTime();
//            graph = createFullGraph(i, 0.2);
//            graph.getStronglyConnectedComponents();
//            finish = System.nanoTime();
//            timens = finish - start;
//            time = ((double)timens) / 1000000;
//            System.out.println(time);
//        }



    }
}