package project;

import java.io.*;
import java.util.*;

public class CITS2200ProjectTester {
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

        MyCITS2200Project proj0 = new MyCITS2200Project();
        String pathToGraphFile = "src/test/projectTestData/edge2.txt";
        loadEdges(proj0, pathToGraphFile);
        String matrix0 = proj0.graphToMatrixString(); //NOTE graph matrices are NOT unique NP hard no. graphs for a solution.
        System.out.println("Your matrix equivalent is: ");
        System.out.println(matrix0);
        System.out.println();
        System.out.println();




//        // Change this to be the path to the graph file.
//        pathToGraphFile = "src/test/projectTestData/edge9.txt";
//        // Create an instance of your implementation.
//        MyCITS2200Project proj1 = new MyCITS2200Project();
//        loadEdges(proj1, pathToGraphFile);
//        System.out.println("Matrix From loading Edges: ");
//        String matrix1 = proj1.graphToMatrixString();
//        String edge1 = proj1.graphToEdgeString();
//        System.out.println(matrix1);
//        System.out.println();
//        System.out.println(edge1);
//        System.out.println();
//
//
//        System.out.println("Matrix from loading matrix: ");
//        MyCITS2200Project proj2 = new MyCITS2200Project();
//        loadMatrix(proj2, "src/test/projectTestData/matrix9.txt");
//        String matrix2 = proj2.graphToMatrixString();
//        String edge2 = proj2.graphToEdgeString();
//        System.out.println(matrix2);
//        System.out.println();
//        System.out.println(edge2);
//        System.out.println();
//        // Load the graph into the project.
//        System.out.println(matrix1.trim().equals(matrix2.trim()));
//        System.out.println(edge1.trim().equals(edge2.trim()));
    }
}