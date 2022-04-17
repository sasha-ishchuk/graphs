import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
Use Dijkstra's algorithm to find the shortest path from a vertex
to other vertices in a graph with edges with non-negative weights.
 */
class DijkstraAlgorithm implements AlgorithmInterface{

    private final int INF = 9999;
    private int[][] matrix;

    //create graph using adjacency matrix
    @Override
    public void createGraph(int V) {
        matrix = new int[V][V];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    //add edge to the graph
    public void addEdge(int vertex1, int vertex2, int weight) {
        matrix[vertex1][vertex2] = weight;
    }

    //get tje list of vertex's neighbours
    public List<Integer> getNeighbours(int idx) {
        List<Integer> n = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++){
            if(matrix[idx][i] != 0){
                n.add(i);
            }
        }
        return n;
    }

    public void dijkstraAlgorithm(int source){
        //array contains distances form source to another vertices (index = vertex, value = distance)
        int[] distance = new int[matrix.length];
        Arrays.fill(distance, INF);
        distance[source] = 0;

        //create a priority queue (priority is distance from the source, shorter distance = higher priority)
        PriorityQueue<Priority> queue = new PriorityQueue<>();
        queue.add(new Priority(source, 0));

        /*
        Until the queue is empty:
        1) Delete the element with the lowest priority.
        2) After removing update the distance-array & priorities in the queue
         */
        while(!queue.isEmpty()){
            int vertex = queue.poll().edge;
            List<Integer> vertexNeighbours = getNeighbours(vertex);
            for(int i = 0; i < vertexNeighbours.size(); i ++) {
                if (distance[vertexNeighbours.get(i)] > distance[vertex] + matrix[vertex][vertexNeighbours.get(i)]){
                    distance[vertexNeighbours.get(i)] = distance[vertex] + matrix[vertex][vertexNeighbours.get(i)];
                    queue.add(new Priority(vertexNeighbours.get(i), distance[vertexNeighbours.get(i)]));
                }
            }
        }

        System.out.println("Distance from source (" + source + ") to:");
        for(int i = 0; i < distance.length; i ++){
            System.out.println(i + " is: " + distance[i]);
        }
    }

    //read graph from file
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

    public static void main(String[] args){
        DijkstraAlgorithm d = new DijkstraAlgorithm();
        d.readFromFile("C:\\Users\\Sasha\\OneDrive\\Рабочий стол\\dijkstra\\Graf1.txt");
        d.dijkstraAlgorithm(1);
    }
}

//class to initialize Priority object, witch contains edge and its cost.
class Priority implements Comparable<Priority>{
    public int edge;
    public int stepCost;

    Priority(int edge, int stepCost) {
        this.edge = edge;
        this.stepCost = stepCost;
    }

    public int getStepCost() {
        return stepCost;
    }

    @Override
    public int compareTo(Priority o) {
        return Integer.compare(this.getStepCost(), o.getStepCost());
    }
}
