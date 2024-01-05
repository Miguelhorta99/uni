package eda2.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author miguel
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int firstLine = Integer.parseInt(br.readLine());

        for (int i = 0; i < firstLine; i++) {

            int size = Integer.parseInt(br.readLine());

            String[] start = br.readLine().split(" ");

            String[] end = br.readLine().split(" ");

            int[] pIni = new int[2];
            int[] pFim = new int[2];

            for (int j = 0; j < 2; j++) {
                pIni[j] = Integer.parseInt(start[j]);
                pFim[j] = Integer.parseInt(end[j]);
            }
            
            /*
            
            int nrBlocks = Integer.parseInt(br.readLine());
            
            String[] blocked = new String[3*nrBlocks+1];
            
            for(int k =0; k < nrBlocks; k++){
                
                blocked = br.readLine().split(" ");
            }
            
            System.out.println("BLOCKED:" + Arrays.toString(blocked));
            
            */

            //System.out.println("tamanho:" + size);
            //System.out.println("inicio:" + Arrays.toString(pIni));
            //System.out.println("fim:" + Arrays.toString(pFim));

            City city = new City(size);
            
            city.numberOfWays(i, i, i, i);
            
        }

    }
}

class City {

    /*
    Cria uma cidade com SIZE ruas com orientação Norte/Sul e SIZE ruas com
    orientação Este/Oeste.
     */
    public City(int size) {
        int[][] table = new int[size][size];
    }

    /*
    Acrescenta o bloqueio do segmento de rua que tem início no cruzamento
    (X,Y), entre a rua Norte/Sul número X e a rua Este/Oeste número Y, na
    direcção DIRECTION (que pode ser "N", "E", "S" ou "W").
     */
    public void addBlockage(int x, int y, String direction) {

    }

    /*
    Calcula e devolve o número de caminhos que existem, do cruzamento
    (SX,SY) até ao cruzamento (EX,EY), seguindo sempre na direcção Norte
    ou na direcção Este.
     */
    public int numberOfWays(int sx, int sy, int ex, int ey) {
        return 0;
    }
}
