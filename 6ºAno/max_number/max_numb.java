import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class max_numb {
    
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int children = Integer.parseInt(reader.readLine());
        int maxNumber = Integer.MIN_VALUE;

        for(int i =0; i<children; i++){

            String[] child = reader.readLine().split(" ");
            int numero = Integer.parseInt(child[0]);

            for(int j =0; j<numero; j++){
                if( Integer.parseInt(child[j]) > maxNumber){
                    maxNumber = Integer.parseInt(child[j]);
                }
            }
        }


        System.out.println(maxNumber);

    }
    
}
