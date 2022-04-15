import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphList implements GraphInterface {

    //Graph is a list of lists (implementation using adjacency list)
    private final List<List<Integer>> listVertex = new ArrayList<>();

    //Number of vertex decide how many lists are included to main list
    @Override
    public void createVertices(int ile) {
        for(int i = 0; i < ile; i++){
            listVertex.add(new ArrayList<>());
        }
    }

    //Add edges between graph's vertices
    @Override
    public void addEdge(int i_Vertex_Index_1, int i_Vertex_Index_2) {
        listVertex.get(i_Vertex_Index_1).add(i_Vertex_Index_2);
    }

    //Remove edge
    @Override
    public boolean removeEdge(int i_Vertex_Index_1, int i_Vertex_Index_2) {
        for(int i = 0; i < listVertex.get(i_Vertex_Index_1).size(); i++) {
            if (listVertex.get(i_Vertex_Index_1).get(i) == i_Vertex_Index_2) {
                listVertex.get(i_Vertex_Index_1).remove(i);
                return true;
            }
        }
        return false;
    }

    //Verify if edge is present in the graph
    @Override
    public boolean checkEdge(int i_Vertex_Index_1, int i_Vertex_Index_2) {
        for(int i = 0; i < listVertex.get(i_Vertex_Index_1).size(); i++){
            if(listVertex.get(i_Vertex_Index_1).get(i) == i_Vertex_Index_2) {
                return true;
            }
        }
        return false;
    }

    //Vertex degree in this implementation depends on number of vertex's neighbours
    @Override
    public int vertexDegree(int idx) {
        return listVertex.get(idx).size();
    }

    //Return the list of vertex's neighbours
    @Override
    public List<Integer> getNeighbourIndices(int idx) {
        return new ArrayList<>(listVertex.get(idx));
    }

    //Print the list of vertex's neighbours
    @Override
    public void printNeighbourIndices(int idx) {
        List<Integer> neighboursList = getNeighbourIndices(idx);
        System.out.println("List of " + idx + " edge's neighbours is: " + neighboursList + ".");
    }

    //Return number of edges in the graph
    @Override
    public int getNumberOfEdges() {
       int edgeNum = 0;
        for (List<Integer> vertex : listVertex) {
            edgeNum += vertex.size();
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

    //Main method
    public static void main(String[] args){
        GraphList graph = new GraphList();
        graph.readFromFile("C:\\Users\\Sasha\\OneDrive\\Рабочий стол\\Graf.txt");
        graph.printNeighbourIndices(4);
        System.out.println(graph.getNumberOfEdges());
        System.out.println(graph.vertexDegree(1));
    }
}
