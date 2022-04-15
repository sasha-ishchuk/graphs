import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AlgorithmDFS {

    private List<List<Integer>> graph;
    private boolean[] isVisited;
    private final List<Integer> visitedVertex = new ArrayList<>();

    //Implement graph using adjacency list
    public void createGraph(int V){
        graph = new ArrayList<>();
        for(int i = 0; i < V; i++){
            graph.add(new ArrayList<>());
        }
    }

    //Add edge to the graph
    public void addEdge(int vertex1, int vertex2){
        graph.get(vertex1).add(vertex2);
    }

    //Method to visit vertices
    public void visitVertices(int vertex){
        isVisited[vertex] = true;
        visitedVertex.add(vertex);
        for (int v : graph.get(vertex)) {
            if (!isVisited[v]) {
                visitVertices(v);
            }
        }
    }

    //Implementation of Depth First Search algorithm (recursive)
    public void algorithmDFS(){
        isVisited = new boolean[graph.size()];
        for(int i = 0; i < graph.size(); i ++){
            if (!isVisited[i]){
                visitVertices(i);
            }
        }
        System.out.println("The order of visiting the graph vertices is: " +
                visitedVertex.toString().substring(1, visitedVertex.toString().length() - 1) + ".");
    }

    //Create graph by reading it from file
    public void readGraph(String path) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));

            int verNumber = scanner.nextInt();
            createGraph(verNumber);

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

    //Main method
    public static void main(String[] args){
        AlgorithmDFS algorithm = new AlgorithmDFS();
        algorithm.readGraph("C:\\Users\\Sasha\\OneDrive\\Рабочий стол\\Graf.txt");
        algorithm.algorithmDFS();
    }
}
