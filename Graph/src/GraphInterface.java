import java.util.List;

public interface GraphInterface {
    void createVertices(int ile);

    void addEdge(int i_Vertex_Index_1, int i_Vertex_Index_2);

    boolean removeEdge(int i_Vertex_Index_1, int i_Vertex_Index_2);

    boolean checkEdge(int i_Vertex_Index_1, int i_Vertex_Index_2);

    int vertexDegree(int idx);

    List<Integer> getNeighbourIndices(int idx);

    void printNeighbourIndices(int idx);

    int getNumberOfEdges();

    void readFromFile(String path);
}
