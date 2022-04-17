import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FloydWarshall implements AlgorithmInterface{

    private final int INFINITY = 999;
    private int[][] matrix;

    /*
    Create graph with adjacency matrix.
    Fill it with infinities except the diagonal, which fill with 0.
    It means that for each vertex its distance from itself is 0,
    and the distance from the others is infinitely large.
     */
    @Override
    public void createGraph(int V) {
        matrix = new int[V][V];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(i == j){
                    matrix[i][j] = 0;
                }
                else{
                    matrix[i][j] = INFINITY;
                }
            }
        }
    }

    //Add edge to the graph
    public void addEdge(int vertex1, int vertex2, int weight) {
        matrix[vertex1][vertex2] = weight;
    }

    /*
    Floyd-Warshall algorithm implementation.
    After executing the loop we receive
    the shortest distances between vertices.
     */
    public void floydWarshallAlgorithm(){
        for(int i = 0; i < matrix.length; i ++){
            for(int j = 0; j < matrix.length; j ++){
                for(int k = 0; k < matrix.length; k++){
                    if(matrix[j][i] + matrix[i][k] < matrix[j][k]){
                        matrix[j][k] = matrix[j][i] + matrix[i][k];
                    }
                }
            }
        }
    }

    //Display the matrix
    public void printMatrix(){
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }

    //Create graph by reading it from file
    @Override
    public void readFromFile(String path) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));

            int verNumber = scanner.nextInt();
            createGraph(verNumber);

            while (scanner.hasNext()){
                int verIndex_1 = scanner.nextInt();
                int verIndex_2 = scanner.nextInt();
                int weight = scanner.nextInt();
                addEdge(verIndex_1, verIndex_2, weight);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File isn't found. Check your filepath " + path + ".");
        }
    }

    //Main method
    public static void main(String[] args){
        FloydWarshall floydWarshall = new FloydWarshall();
        floydWarshall.readFromFile("C:\\Users\\Sasha\\OneDrive\\Рабочий стол\\Graf1.txt");
        floydWarshall.floydWarshallAlgorithm();
        floydWarshall.printMatrix();
    }
}