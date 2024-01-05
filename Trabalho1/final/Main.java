import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int nQuestoes = Integer.parseInt(input.readLine()); // numero de questões

        ArrayList<int[]> ultimo = new ArrayList<int[]>();

        for (int i = 1; i <= nQuestoes; i++) {

            int nProdutos1 = Integer.parseInt(input.readLine()); // número de produtos na 1ª linha
            ArrayList<Character> tipoProd1 = new ArrayList<Character>(); // tipo dos produtos da 1ªlinha
            ArrayList<Integer> prodVal1 = new ArrayList<Integer>(); // valor dos produtos da 1ªlinha

            for (int j = 1; j <= nProdutos1; j++) {
                String[] lineString = input.readLine().split(" "); // lê a linha e separa em diferentes posições do
                                                                   // array quando encontrar um espaço vazio
                tipoProd1.add(lineString[1].charAt(0)); // Mete os caracteres dos produtos na lista
                                                        // "products1Types"
                prodVal1.add(Integer.parseInt(lineString[2])); // Mete os valores dos produtos na lista
                // "products1Values"

            }

            int nProdutos2 = Integer.parseInt(input.readLine()); // número de produtos da 2ª linha
            ArrayList<Character> tipoProd2 = new ArrayList<Character>(); // tipo dos produtos da 2ªlinha
            ArrayList<Integer> prodVal2 = new ArrayList<Integer>(); // valor dos produtos da 2ªlinha

            for (int j = 1; j <= nProdutos2; j++) {
                String[] lineString = input.readLine().split(" "); // lê a linha e separa em diferentes posições do
                                                                   // array quando encontrar um espaço vazio
                tipoProd2.add(lineString[1].charAt(0)); // Mete os caracteres dos produtos na lista
                // "products1Types"
                prodVal2.add(Integer.parseInt(lineString[2])); // Mete os valores dos produtos na lista
                // "products1Values"
            }

            ultimo.add(calcMax(nProdutos1, nProdutos2, tipoProd1, tipoProd2, prodVal1, prodVal2));

        }

        for (int[] elementos : ultimo) {
            System.out.println(elementos[0] + " " + elementos[1]);
        }

    }

    private static int[] calcMax(int n, int m, ArrayList<Character> types1, ArrayList<Character> types2,
            ArrayList<Integer> values1, ArrayList<Integer> values2) {
        int[][] matrizV = new int[n][m]; // matriz com a soma dos valores dos tipos iguais
        int[][] matrizM = new int[n][m]; // matriz com o numero de pares de cada tipo
        int[] valoresFinais = { 0, 0 };

        if (n == 0 || m == 0)
            return valoresFinais;

        if (types1.get(0) == types2.get(0)) {
            if (values1.get(0) + values2.get(0) == 0) {
                matrizV[0][0] = 0;
                matrizM[0][0] = 0;
            } else {
                matrizV[0][0] = values1.get(0) + values2.get(0);
                matrizM[0][0] = 1;
            }

        }



        int max = -1, min = -1;

        for (int j = 1; j < m; j++) {

            if (types1.get(0) == types2.get(j)) {

                if (values1.get(0) + values2.get(j) == 0) {
                    matrizV[0][j] = 0;
                    matrizM[0][j] = 0;
                } else {
                    matrizV[0][j] = values1.get(0) + values2.get(j);
                    matrizM[0][j] = 1;
                }

                if (n == 1) {
                    if (max < matrizV[0][j - 1]) {

                        max = matrizV[0][j - 1];
                        min = matrizM[0][j - 1];
                    }
                }

                if (n == 1 && j == (values2.size() - 1)) {
                    if (max < matrizV[0][j]) {
                        max = matrizV[0][j];
                    }

                    valoresFinais[0] = max;
                    valoresFinais[1] = min;

                    return valoresFinais;
                }

            } else {
                matrizV[0][j] = matrizV[0][j - 1];
                matrizM[0][j] = matrizM[0][j - 1];
            }
        }

        for (int i = 1; i < n; i++) {

            if (types1.get(i) == types2.get(0)) {

                if (values1.get(i) + values2.get(0) == 0) {
                    matrizV[i][0] = 0;
                    matrizM[i][0] = 0;
                } else {
                    matrizV[i][0] = values1.get(i) + values2.get(0);
                    matrizM[i][0] = 1;
                }

                if (m == 1) {
                    if (max < matrizV[i - 1][0]) {

                        max = matrizV[i - 1][0];
                        min = matrizM[i - 1][0];
                    }
                }

                if (m == 1 && i == (values1.size() - 1)) {
                    if (max < matrizV[i][0]) {
                        max = matrizV[i][0];
                    }

                    valoresFinais[0] = max;
                    valoresFinais[1] = min;

                    return valoresFinais;
                }

            } else {
                matrizV[i][0] = matrizV[i - 1][0];
                matrizM[i][0] = matrizM[i - 1][0];
            }
        }

        for (int i = 1; i < n; i++) { // n = 4
            for (int j = 1; j < m; j++) { // m = 3
                int maxVal = -1, maxMatch = -1;

                if(matrizV[i-1][j] >= matrizV[i][j-1]){
                    maxVal = matrizV[i-1][j];
                }
                else{
                    maxVal = matrizV[i][j-1];
                }

                if(maxVal != 0){
                    maxVal = matrizV[i][j-1] + matrizV[i-1][j];
                }

                if(matrizM[i-1][j] >= matrizM[i][j-1]){
                    maxMatch = matrizM[i-1][j];
                }
                else{
                    maxMatch = matrizM[i][j-1];
                }
                matrizV[i][j] = maxVal;
                matrizM[i][j] = maxMatch;

                System.out.println(matrizM[i][j]);
            }

        }

        valoresFinais[0] = matrizV[n - 1][m - 1];
        valoresFinais[1] = matrizM[n - 1][m - 1];

        return valoresFinais;
    }
}

