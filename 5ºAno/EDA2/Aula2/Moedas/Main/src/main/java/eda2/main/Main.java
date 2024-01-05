//package eda2.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);

        String primeiraLinha = br.readLine();

        int nrMoedas = Integer.parseInt(primeiraLinha);

        String[] valueMoedas = br.readLine().split(" ");

        int[] arrayMoedas = new int[nrMoedas];
        
        for(int i = 0; i< nrMoedas; i++){
            arrayMoedas[i] = Integer.parseInt(valueMoedas[i]);
        }

        String terceiraLinha = br.readLine();

        int nrPerguntas = Integer.parseInt(terceiraLinha);

        for (int j = 0; j < nrPerguntas; j++) {

            String perguntas = br.readLine();

            int pergunta = Integer.parseInt(perguntas);
            
            System.out.println(pergunta + ":" + " " +"[" + coin(pergunta, arrayMoedas) + "]");

        }
    }
    
    
    public static int coin(int pergunta, int[] arrayMoedas){
        
        int[] resultado = new int[pergunta + 1];
        
        resultado[0] = 0; 
        
        Arrays.fill(resultado, 1, resultado.length, pergunta+1); // preencher o array resultado desde a posicao 1 ate resultado.length com o valor de pergunta +1 
        
         for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < arrayMoedas.length; j++) {
                if (arrayMoedas[j] <= i)
                    resultado[i] = Math.min(1 + resultado[i- arrayMoedas[j]], resultado[i]);
            }
        }
        
        return resultado[pergunta];
    }
}
