import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String primeiraLinha[] = input.readLine().split(" "); // rows | columns | test cases
        Map map = new Map(Integer.parseInt(primeiraLinha[0]), Integer.parseInt(primeiraLinha[1]));

        int ncases = Integer.parseInt(primeiraLinha[2]);

        for (int j = 0; j < map.rows; j++) { // => Leitura do mapa
            char line[] = input.readLine().toCharArray();
            for (int k = 0; k < map.cols; k++) {
                map.map[j][k] = line[k];
            }

        }

        // => Criação dos Grafos
        map.generateGraphs();

        for (int i = 0; i < ncases; i++) {

            // -----------------------------------|Cálculos|-----------------------------------//
            // => Leitura das coordenadas do John e da Kate
            String lastLine[] = input.readLine().split(" ");
            int sk = Integer.parseInt(lastLine[0]) * map.cols + Integer.parseInt(lastLine[1]);

            map.bf(sk, map.goal);

        }

        input.close();
    }
}

class Map {
    int rows, cols;
    char map[][]; // => Mapa do tabuleiro
    Graph g;
    int goal;

    public static final int INFINITY = Integer.MAX_VALUE;
    public static final int NONE = -1;

    Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        map = new char[rows][cols];
        this.g = new Graph(rows * cols);

        this.goal = -1;
        
    }

    void generateGraphs() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                int posK = (this.cols * row) + col;
                int lastRow = this.rows - 1;
                int lastCol = this.cols - 1;

                if ((this.map[row][col] != 'O') && (this.map[row][col] != 'X')) {

                    // -----------------------------------------|Grafo da
                    // Kate|-----------------------------------------//

                    // => Ligar à esquerda (Se não for a primeira coluna e se não tiver um bloqueio
                    // à esquerda)
                    if ((col > 0) && (this.map[row][col - 1] != 'O')) {
                        this.g.addEdge(posK, new Edge((posK - 1), /* Índice da posição à esquerda da Kate */
                                cellCost(this.map[row][col]), /* Custo da posição da Kate */
                                this.map[row][col - 1]) /* Símbolo da posição à esquerda da Kate */
                        );
                    }

                    // => Ligar acima (Se não for a primeira linha e se não tiver um bloqueio acima)
                    if ((row > 0) && (this.map[row - 1][col] != 'O')) {
                        this.g.addEdge(posK, new Edge((posK - this.cols), /* Índice da posição acima da Kate */
                                cellCost(this.map[row][col]), /* Custo da posição da Kate */
                                this.map[row - 1][col]) /* Símbolo da posição acima da Kate */
                        );
                    }

                    // => Ligar à direita (Se não for a última coluna e se não tiver um bloqueio à
                    // direita)
                    if ((col < (lastCol)) && (this.map[row][col + 1] != 'O')) {
                        this.g.addEdge(posK, new Edge((posK + 1), /* Índice da posição à direita da Kate */
                                cellCost(this.map[row][col]), /* Custo da posição da Kate */
                                this.map[row][col + 1]) /* Símbolo da posição à direita da Kate */
                        );
                    }

                    // => Ligar abaixo (Se não for a última linha e se não tiver um bloqueio abaixo)
                    if ((row < lastRow) && (this.map[row + 1][col] != 'O')) {
                        this.g.addEdge(posK, new Edge((posK + this.cols), /* Índice da posição abaixo da Kate */
                                cellCost(this.map[row][col]), /* Custo da posição da Kate */
                                this.map[row + 1][col]) /* Símbolo da posição abaixo da Kate */
                        );
                    }

                    
                } else if (this.map[row][col] == 'X') {
                    this.goal = posK;
                }
            }
        }

    }

    // => Devolve o valor respetivo de cada coordenada
    int cellCost(char c) {
        switch (c) {
            case 'W':
                return 2;
            case 'G':
                return 1;
            case 'X':
                return 1;
            default:
                return 1;
        }
    }

    // Algoritmo Bellman-Ford Kate
    boolean bf(int s, int x) {

        int v = this.g.nodes; // => Número de vértices
        int[] d = new int[this.g.nodes]; // => Tempo de cada ponto à origem

        for (int i = 0; i < v; i++) {
            d[i] = INFINITY;
        }
        d[s] = 0;

        for (int j = 0; j < v - 1; j++) {
            boolean cont = false;
            for (int i = 0; i < v; i++) {
                for (Edge up : this.g.adjacents[i]) {
                    // Relax
                    if ((d[i] != INFINITY) && ((d[i] + up.weight()) < d[up.dest()])) { // i => node source | up.dest()
                                                                                       // => node destino
                        d[up.dest()] = d[i] + up.weight(); // => up.weight() => Peso do ramo
                        cont = true;
                    }
                }
            }
            if (!cont)
                break;
        }

        if (d[x] != INFINITY) {
            System.out.println(d[x]);
        } else {
            System.out.println("Unreachable");
        }
        return true;
    }
}

class Edge implements Comparable<Edge> {
    private int dest; // edge destination node
    private int weight; // edge weight
    char type; // edge type => Grass | Water | Magic Wheel

    public Edge(int dest, int weight, char type) {
        this.dest = dest;
        this.weight = weight;
        this.type = type;
    }

    public int dest() {
        return dest;
    }

    public int weight() {
        return weight;
    }

    public char type() {
        return type;
    }

    public int compareTo(Edge anEdge) {
        if (weight < anEdge.weight) {
            return -1;
        }

        if (weight == anEdge.weight) {
            return 0;
        }
        return 1;
    }
}

class Graph {
    int nodes;
    List<Edge>[] adjacents;

    @SuppressWarnings("unchecked")
    public Graph(int nodes) {
        this.nodes = nodes;
        adjacents = new List[nodes];
        for (int i = 0; i < nodes; i++) {
            adjacents[i] = new LinkedList<>();
        }
    }

    public void addEdge(int o, Edge v) {
        adjacents[o].add(v);
    }

    public void show() {
        for (int i = 0; i < this.nodes; i++) {
            System.out.print(i + " ->");
            for (Edge e : this.adjacents[i]) {
                System.out.print(e.dest() + " ");
            }
            System.out.println();
        }
    }
}
