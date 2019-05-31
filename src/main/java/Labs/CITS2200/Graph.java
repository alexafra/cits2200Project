//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Graph {
    private int[][] edgeMatrix;
    private int numberOfVertices;
    private boolean directed;
    private boolean weighted;

    private Graph(int var1, boolean var2, boolean var3) {
        if (var1 < 1) {
            this.numberOfVertices = 0;
        } else {
            this.numberOfVertices = var1;
        }

        this.edgeMatrix = new int[this.numberOfVertices][this.numberOfVertices];
        this.weighted = var2;
        this.directed = var3;
    }

    private Graph(int var1) {
        this(var1, false, false);
    }

    private void addEdge(int var1, int var2, int var3) {
        if (0 <= var1 && 0 <= var2 && var1 <= this.numberOfVertices && var2 <= this.numberOfVertices) {
            if (var3 < 1) {
                var3 = 0;
            } else if (!this.weighted) {
                var3 = 1;
            }

            this.edgeMatrix[var1][var2] = var3;
            if (!this.directed) {
                this.edgeMatrix[var2][var1] = var3;
            }

        } else {
            throw new RuntimeException("Vertex out of bounds");
        }
    }

    private void addEdge(int var1, int var2) {
        this.addEdge(var1, var2, 1);
    }

    private void removeEdge(int var1, int var2) {
        this.addEdge(var1, var2, 0);
    }

    public int getWeight(int var1, int var2) {
        if (0 <= var1 && 0 <= var2 && var1 <= this.numberOfVertices && var2 <= this.numberOfVertices) {
            return this.edgeMatrix[var1][var2];
        } else {
            throw new RuntimeException("Vertex out of bounds");
        }
    }

    public int getNumberOfVertices() {
        return this.numberOfVertices;
    }

    public boolean isDirected() {
        return this.directed;
    }

    public boolean isWeighted() {
        return this.weighted;
    }

    public int[][] getEdgeMatrix() {
        int[][] var1 = new int[this.edgeMatrix.length][this.edgeMatrix.length];

        for(int var2 = 0; var2 < this.edgeMatrix.length; ++var2) {
            var1[var2] = this.edgeMatrix[var2].clone();
        }

        return var1;
    }

    public static Graph randomGraph(int var0, boolean var1, double var2) {
        Graph var4 = new Graph(var0, false, var1);

        for(int var5 = 0; var5 < var4.getNumberOfVertices(); ++var5) {
            for(int var6 = 0; var6 < (var1 ? var4.getNumberOfVertices() : var5 + 1); ++var6) {
                if (Math.random() < var2) {
                    var4.edgeMatrix[var5][var6] = 1;
                    if (!var1) {
                        var4.edgeMatrix[var6][var5] = 1;
                    }
                }
            }
        }

        return var4;
    }

    public static Graph randomGraph(int var0, double var1) {
        return randomGraph(var0, false, var1);
    }

    //No. Vertices, directed, density, range
    public static Graph randomWeightedGraph(int var0, boolean var1, double var2, int var4) {
        Graph var5 = new Graph(var0, true, var1);

        for(int var6 = 0; var6 < var5.getNumberOfVertices(); ++var6) {
            for(int var7 = 0; var7 < (var1 ? var5.getNumberOfVertices() : var6 + 1); ++var7) {
                if (Math.random() < var2) {
                    var5.edgeMatrix[var6][var7] = (int)(Math.random() * (double)(var4 - 1)) + 1;
                    if (!var1) {
                        var5.edgeMatrix[var7][var6] = var5.edgeMatrix[var6][var7];
                    }
                }
            }
        }

        return var5;
    }

    public static Graph randomWeightedBipartiteGraph(int var0, boolean var1, double var2, int var4) {
        Graph var5 = new Graph(var0, true, var1);

        for(int var6 = 0; var6 < var5.getNumberOfVertices(); var6 += 2) {
            for(int var7 = var1 ? 1 : var6 + 1; var7 < var5.getNumberOfVertices(); var7 += 2) {
                if (Math.random() < var2) {
                    var5.edgeMatrix[var6][var7] = (int)(Math.random() * (double)(var4 - 1)) + 1;
                    if (!var1) {
                        var5.edgeMatrix[var7][var6] = var5.edgeMatrix[var6][var7];
                    }
                }
            }
        }

        return var5;
    }

    public static Graph randomBipartiteGraph(int var0, boolean var1, double var2) {
        Graph var4 = new Graph(var0, false, var1);

        for(int var5 = 0; var5 < var4.getNumberOfVertices(); var5 += 2) {
            for(int var6 = var1 ? 1 : var5 + 1; var6 < var4.getNumberOfVertices(); var6 += 2) {
                if (Math.random() < var2) {
                    var4.edgeMatrix[var5][var6] = 1;
                    if (!var1) {
                        var4.edgeMatrix[var6][var5] = 1;
                    }
                }
            }
        }

        return var4;
    }

    public static Graph readFile(String var0, boolean var1, boolean var2) throws Exception {
        String var3 = " \t";
        BufferedReader var4 = new BufferedReader(new FileReader(var0));
        String var5 = var4.readLine();
        int var6 = Integer.parseInt(var5);
        Graph var7 = new Graph(var6, var1, var2);

        for(int var8 = 0; var8 < var6; ++var8) {
            StringTokenizer var9 = new StringTokenizer(var4.readLine().trim(), var3);

            for(int var10 = 0; var10 < var6; ++var10) {
                String var11 = var9.nextToken();
                var7.addEdge(var8, var10, Integer.parseInt(var11));
            }
        }

        var4.close();
        return var7;
    }

    public String toString() {
        StringBuffer var1 = new StringBuffer(this.getNumberOfVertices() + "\n");

        for(int var2 = 0; var2 < this.numberOfVertices; ++var2) {
            for(int var3 = 0; var3 < this.numberOfVertices; ++var3) {
                var1.append(this.edgeMatrix[var2][var3]);
                var1.append("\t");
            }

            var1.append("\n");
        }

        return var1.toString();
    }
}
