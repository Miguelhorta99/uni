import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class coins {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int nrCoins = Integer.parseInt(bf.readLine());
        String[] coins = bf.readLine().split(" ");
        int[] arrayMoedas = new int[nrCoins];

        for (int i = 0; i < nrCoins; i++) {
            arrayMoedas[i] = Integer.parseInt(coins[i]);
        }

        int nrQuestion = Integer.parseInt(bf.readLine());

        for (int j = 0; j < nrQuestion; j++) {
            int question = Integer.parseInt(bf.readLine());

            System.out.println(question + ":" + " " + "[" + calcular(question, arrayMoedas) + "]");
        }

    }

    public static int calcular(int pergunta, int[] arrayMoedas) {

        int[] resultado = new int[pergunta + 1];

        resultado[0] = 0;

        Arrays.fill(resultado, 1, resultado.length, pergunta + 1); // preencher o array resultado desde a posicao 1 ate
                                                                   // resultado.length com o valor de pergunta +1

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < arrayMoedas.length; j++) {
                if (arrayMoedas[j] <= i)
                    resultado[i] = Math.min(1 + resultado[i - arrayMoedas[j]], resultado[i]); //queremos o valor final do array resultado, onde se vai calculando o numero menor de moedas possiveis
                                                                                              //para atingir o valor necessario
            }
        }

        return resultado[pergunta];
    }

}
