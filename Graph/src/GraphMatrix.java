import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GraphMatrix implements GraphInterface{

    private final int N = 100;
    private final boolean[][] graphMatrix = new boolean[N][N];

    /*
    Graph implementation using adjacency matrix.
    Create the (boolean)matrix and fill it with FALSE.
     */
    @Override
    public void createVertices(int ile) {
        for (int i = 0; i < ile; i++){
            for (int j = 0; j < ile; j ++){
                graphMatrix[i][j] = false;
            }
        }
    }

    //Add edge to the graph (if edge between vertices is present -> TRUE)
    @Override
    public void addEdge(int i_Vertex_Index_1, int i_Vertex_Index_2) {
        graphMatrix[i_Vertex_Index_1][i_Vertex_Index_2] = true;
    }

    //Remove edge
    @Override
    public boolean removeEdge(int i_Vertex_Index_1, int i_Vertex_Index_2) {
        if(graphMatrix[i_Vertex_Index_1][i_Vertex_Index_2]){
            graphMatrix[i_Vertex_Index_1][i_Vertex_Index_2] = false;
            return true;
        }
        return false;
    }

    //Verify if edge is present in the graph
    @Override
    public boolean checkEdge(int i_Vertex_Index_1, int i_Vertex_Index_2) {
        return graphMatrix[i_Vertex_Index_1][i_Vertex_Index_2];
    }

    //Vertex degree in this implementation depends on number of vertex's neighbours
    @Override
    public int vertexDegree(int idx) {
        int degreeCount = 0;
        for(int i = 0; i < graphMatrix.length; i ++){
            if(graphMatrix[idx][i]){
                degreeCount += 1;
            }
        }
        return degreeCount;
    }

    //Return the list of vertex's neighbours
    @Override
    public List<Integer> getNeighbourIndices(int idx) {
        List<Integer> neighbours = new ArrayList<>();
        for(int i = 0; i < graphMatrix.length; i++){
            if(graphMatrix[idx][i]){
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    //Print the list of vertex's neighbours
    @Override
    public void printNeighbourIndices(int idx) {
        System.out.println("List of " + idx + " edge's neighbours is: " + getNeighbourIndices(idx) + ".");
    }

    //Return the number of edges in the graph
    @Override
    public int getNumberOfEdges() {
        int edgeNum = 0;
        for (boolean[] matrix : graphMatrix) {
            for (int j = 0; j < graphMatrix.length; j++) {
                if (matrix[j]) {
                    edgeNum += 1;
                }
            }
        }
        return edgeNum;
    }

    //Create graph by reading it from file
    @Override
    public void readFromFile(String path) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));

            int verNumber = scanner.nextInt();
            createVertices(verNumber);

            while (scanner.hasNext()){
                int verIndex_1 = scanner.nextInt();
                int verIndex_2 = scanner.nextInt();
                addEdge(verIndex_1, verIndex_2);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File isn't found. Check your filepath " + path + ".");
        }
    }

    //Display the graph
    public void printGraph(){
        for (boolean[] matrix : graphMatrix) {
            System.out.println(Arrays.toString(matrix));
        }
    }

    //Main method
    public static void main(String[] args){
        GraphMatrix graphM = new GraphMatrix();
        graphM.readFromFile("C:\\Users\\Sasha\\OneDrive\\Рабочий стол\\Graf.txt");
        graphM.printNeighbourIndices(4);
        System.out.println(graphM.getNumberOfEdges());
//        graphM.printGraph();
        System.out.println(graphM.vertexDegree(1));
    }
}

