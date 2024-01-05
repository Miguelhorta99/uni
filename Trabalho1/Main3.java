import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main3{

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> res = new ArrayList<int[]>();
        
        int nQuestions = Integer.parseInt(input.readLine()); // numero de questões

        for (int i = 1; i <= nQuestions; i++){

            int nProducts1 = Integer.parseInt(input.readLine()); // número de produtos na 1ª linha
            ArrayList<Character> products1Types = new ArrayList<Character>(); // tipo dos produtos da 1ªlinha
            ArrayList<Integer> products1Values = new ArrayList<Integer>(); // valor dos produtos da 1ªlinha

            for (int j = 1; j <= nProducts1; j++){ 
                String[] lineString = input.readLine().split(" "); // lê a linha e separa em diferentes posições do array quando encontrar um espaço vazio
                products1Types.add(lineString[1].charAt(0)); // Mete os caracteres dos produtos na lista "products1Types"
                products1Values.add(Integer.parseInt(lineString[2])); // Mete os valores dos produtos na lista "products1Values"

            }

            int nProducts2 = Integer.parseInt(input.readLine()); // número de produtos da 2ª linha
            ArrayList<Character> products2Types = new ArrayList<Character>(); // tipo dos produtos da 2ªlinha
            ArrayList<Integer> products2Values = new ArrayList<Integer>(); //valor dos produtos da 2ªlinha

            for (int j = 1; j <= nProducts2; j++){
                String[] lineString = input.readLine().split(" "); // lê a linha e separa em diferentes posições do array quando encontrar um espaço vazio
                products2Types.add(lineString[1].charAt(0)); // Mete os caracteres dos produtos na lista "products1Types"
                products2Values.add(Integer.parseInt(lineString[2])); // Mete os valores dos produtos na lista "products1Values"
            }

            res.add(calcMax(nProducts1, nProducts2, products1Types, products2Types, products1Values, products2Values));
        }

        for (int[] el : res) {
            System.out.println(el[0] + " " + el[1]);
        }
    }

    private static int[] calcMax(int n, int m, ArrayList<Character> types1, ArrayList<Character> types2, ArrayList<Integer> values1, ArrayList<Integer> values2) {
        int[][] matrizV = new int[n][m], matrizM = new int[n][m];
        int[] valoresFinais = {0, 0};

        if (n == 0 || m == 0) return valoresFinais;

        if (types1.get(0) == types2.get(0)){
            matrizV[0][0] = values1.get(0)+values2.get(0);
            matrizM[0][0] = 1;
        } else {
            matrizV[0][0] = 0;
            matrizM[0][0] = 0;
        }

        for (int j = 1; j < m; j++){
            if (types1.get(0) == types2.get(j)) {
                matrizV[0][j] = matrizV[0][j-1]+values1.get(0)+values2.get(j);
                matrizM[0][j] = matrizM[0][j-1]+1;
            } else {
                matrizV[0][j] = matrizV[0][j-1];
                matrizM[0][j] = matrizM[0][j-1];
            }
        }
        for (int i = 1; i < m; i++){
            if (types1.get(i) == types2.get(0)) {
                matrizV[i][0] = matrizV[i-1][0]+values1.get(i)+values2.get(0);
                matrizM[i][0] = matrizM[i-1][0]+1;
            } else {
                matrizV[i][0] = matrizV[i-1][0];
                matrizM[i][0] = matrizM[i-1][0];
            }
        }

        for (int i = 1; i < n; i++){ // n = 4
            for (int j = 1; j < m; j++){ // m = 3
                int maxVal = -1, maxMatch = -1;

                if ((matrizV[i-1][j] < matrizV[i][j-1]) || (matrizV[i-1][j] == matrizV[i][j-1] && matrizM[i-1][j] < matrizM[i][j-1])) {
                    maxVal = matrizV[i][j-1];
                    maxMatch = matrizM[i][j-1];
                } else {
                    maxVal = matrizV[i-1][j];
                    maxMatch = matrizM[i-1][j];
                }

                if (types1.get(i) == types2.get(j)){
                    int matchMaxV = matrizV[i-1][j-1]+values1.get(i)+values2.get(j),
                        matchMaxM = matrizM[i-1][j-1]+1;

                    if (maxVal < matchMaxV || (maxVal == matchMaxV && maxMatch < matchMaxM)) {
                        maxVal = matchMaxV;
                        maxMatch = matchMaxM;
                    }
                }

                matrizV[i][j] = maxVal;
                matrizM[i][j] = maxMatch;
            }
        }

        valoresFinais[0] = matrizV[n-1][m-1];
        valoresFinais[1] = matrizM[n-1][m-1];

        return valoresFinais;
    }

}
