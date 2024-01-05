
//package eda2.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        
        String firstLine = br.readLine();
        
        int children = Integer.parseInt(firstLine);
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i< children; i++){
            
            String[] read = br.readLine().split(" ");
            
            int nrSticks = Integer.parseInt(read[0]);
            
            for( int j = 1; j<= nrSticks; j++) {
                if(max < Integer.parseInt(read[j])){
                    max = Integer.parseInt(read[j]);
                }
            }
            
        }
        System.out.println(max);
    }
}
