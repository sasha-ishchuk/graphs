import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopologicalSorting implements AlgorithmInterface{
    /*
     1) We can only sort topologically by directed acyclic graphs.
     2) Topological sorting is the ordering of (all) graph vertices.
     3) If there is an edge connecting the vertices x and y, then after
        topological sorting the vertex x will be before y.
    */
    private int[][] graph;
    private int[] inEdges;

    //create graph with adjacency matrix
    @Override
    public void createGraph(int V) {
        graph = new int[V][V];
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph.length; j++){
                graph[i][j] = 0;
            }
        }
    }

    //add edge to the graph (and its weight)
    public void addEdge(int vertex1, int vertex2, int weight) {
        graph[vertex1][vertex2] = weight;
    }

    //get the list of vertex's neighbours
    public List<Integer> getNeighbourIndices(int idx) {
        List<Integer> neighbours = new ArrayList<>();
        for(int i = 0; i < graph.length; i++){
            if(graph[idx][i] != 0){
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    //check how many edges enter to each vertex
    public void checkInEdges(){
        //indexes = vertices, values = number of income edges
        inEdges = new int[graph.length];
        for (int[] ints : graph) {
            for (int j = 0; j < graph.length; j++) {
                if (ints[j] != 0) {
                    if (inEdges[j] == 0) {
                        inEdges[j] = 1;
                    } else {
                        inEdges[j] += 1;
                    }
                }
            }
        }
    }

    public void topologicalSorting(){
        checkInEdges();

        //initialize the list with result of topological sorting
        List<Integer> sortedGraph = new ArrayList<>();

        //initialize the queue(LIFO) & add to it vertices without enter edges
        Deque<Integer> emptyVerticesQueue = new ArrayDeque<>();
        for(int i = 0; i < inEdges.length; i++){
            if(inEdges[i] == 0){
                emptyVerticesQueue.add(i);
            }
        }

        /*
        Until the queue is empty:
        1) Remove the first element from queue & past it to the sort results list.
        2) For each of the neighbors of the deleted element, low the number of edges entering it by 1.
        3) Check how many edges (after reduction) enter into this vertex
           (if 0, add this vertex to the end of the queue).
         */
        while (!emptyVerticesQueue.isEmpty()) {
            int currentVertex = emptyVerticesQueue.poll();
            List<Integer> neighbours = getNeighbourIndices(currentVertex);

            for(int i = 0; i < neighbours.size(); i++){
                if(inEdges[neighbours.get(i)] != 0) {
                    inEdges[neighbours.get(i)] -= 1;
                    if(inEdges[neighbours.get(i)] == 0){
                        emptyVerticesQueue.add(neighbours.get(i));
                    }
                }
            }
            sortedGraph.add(currentVertex);
        }
        //check if graph is acyclic
        isAcyclic(sortedGraph);
    }

    /*
    If the number of elements after topological sort = number of graph's vertex,
    then graph is acyclic.
    If not, there are cycles in the graph
     */
    public void isAcyclic(List<Integer> sortingResult){
        if(sortingResult.size() < graph.length){
            System.out.println("Graph is NOT acyclic. The topological sorting is impossible:(");
        }
        else if(sortingResult.size() > graph.length){
            System.err.println("Something went wrong...");
        }
        else {
            System.out.println("Graph is acyclic.");
            System.out.println("The result of topological sorting is: " + sortingResult);
        }
    }

    //read graph from file
    @Override
    public void readFromFile(String path) {
        int verNumber;
        int verIndex_1, verIndex_2;
        int weight;
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));

            verNumber = scanner.nextInt();
            createGraph(verNumber);

            while (scanner.hasNext()){
                verIndex_1 = scanner.nextInt();
                verIndex_2 = scanner.nextInt();
                weight = scanner.nextInt();
                addEdge(verIndex_1, verIndex_2, weight);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File isn't found. Check your filepath " + path + ".");
        }
    }

    public static void main(String[] args){
        TopologicalSorting sorting = new TopologicalSorting();
        sorting.readFromFile("C:\\Users\\Sasha\\OneDrive\\Рабочий стол\\Graph2.txt");
        sorting.topologicalSorting();
    }
}
