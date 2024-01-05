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

            ultimo.add(calcMax(nProdutos1, nProdutos2, tipoProd1, tipoProd2, prodVal1, prodVal2)); //chamada da função que ira calcular o maximo valor com o menor numero de pares

        }
        //ciclo que imprime o output
        for (int[] elementos : ultimo) {
            System.out.println(elementos[0] + " " + elementos[1]);
        }

    }

    private static int[] calcMax(int n, int m, ArrayList<Character> types1, ArrayList<Character> types2,
            ArrayList<Integer> values1, ArrayList<Integer> values2) {
        int[][] matrizV = new int[n][m]; // matriz com a soma dos valores dos tipos iguais
        int[][] matrizM = new int[n][m]; // matriz com o numero de pares de cada tipo
        int[] valoresFinais = { 0, 0 };

        if (n == 0 || m == 0) //caso base
            return valoresFinais;

        //calcula a primeira posicao da matriz de valor e pares
        if (types1.get(0) == types2.get(0) && values1.get(0) + values2.get(0) > 0) { 
            matrizV[0][0] = values1.get(0) + values2.get(0);
            matrizM[0][0] = 1;
        } else {
            matrizV[0][0] = 0;
            matrizM[0][0] = 0;
        }

        int maxV = -1, maxM = -1;

        //primeira linha das matrizes
        for (int j = 1; j < m; j++) { 

            // se os tipos forem iguais e a soma dos valores dessa posicao for maior que que a posicao uma coluna atras soma os valores da primeira posicao do primeiro tapete
            if (types1.get(0) == types2.get(j) && values1.get(0) + values2.get(j) >= matrizV[0][j - 1]) { 
                matrizV[0][j] = values1.get(0) + values2.get(j);
                matrizM[0][j] = 1;

            } else {
                matrizV[0][j] = matrizV[0][j - 1];
                matrizM[0][j] = matrizM[0][j - 1];
            }
        }

        //primeira coluna das matrizes
        for (int i = 1; i < n; i++) { 

            // se os tipos forem iguais e a soma dos valores dessa posicao for maior que que a posicao uma linha atras soma os valores da primeira posicao do segundo tapete
            if (types1.get(i) == types2.get(0) && values1.get(i) + values2.get(0) >= matrizV[i - 1][0]) {

                matrizV[i][0] = values1.get(i) + values2.get(0);
                matrizM[i][0] = 1;

            } else {
                matrizV[i][0] = matrizV[i - 1][0];
                matrizM[i][0] = matrizM[i - 1][0];
            }

        }

        //percorre o interior das matrizes (tirando a primeira linha e coluna)
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                maxM = -1;
                maxV = -1;

                //obter o maior valor da matriz ou se o maior valor for igual encontrar o menor numero de pares
                if ((matrizV[i - 1][j] < matrizV[i][j - 1])
                        || (matrizV[i - 1][j] == matrizV[i][j - 1] && matrizM[i - 1][j] > matrizM[i][j - 1])) {
                    maxV = matrizV[i][j - 1];
                    maxM = matrizM[i][j - 1];
                } else {
                    maxV = matrizV[i - 1][j];
                    maxM = matrizM[i - 1][j];
                }

                //comparar o valor maxV com o valor da matriz na posicao (i-1, j-1), se tal acontecer o novo maximo é atribuido a maxV
                if (types1.get(i) == types2.get(j)) {

                    //obter o valor e numero de pares na posicao
                    int matchMaxV = matrizV[i - 1][j - 1] + values1.get(i) + values2.get(j), 
                        matchMaxM = matrizM[i - 1][j - 1] + 1;

                    if (maxV < matchMaxV || (maxV == matchMaxV && maxM > matchMaxM)) {
                        maxV = matchMaxV;
                        maxM = matchMaxM;
                    }

                }

                matrizV[i][j] = maxV;
                matrizM[i][j] = maxM;
            }

        }

        valoresFinais[0] = matrizV[n - 1][m - 1];
        valoresFinais[1] = matrizM[n - 1][m - 1];

        return valoresFinais;
    }
}
