package eda2.main;

/**
 *
 * @author miguel
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    
    static class Product {
        String name;
        char type;
        int value;
        boolean used;

        Product(String name, char type, int value) {
            this.name = name;
            this.type = type;
            this.value = value;
            this.used = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numTestCases = Integer.parseInt(br.readLine().trim());

        for (int t = 0; t < numTestCases; t++) {
            String[] line = br.readLine().split(" ");
            int numProducts1 = Integer.parseInt(line[0]);
            List<Product> products1 = new ArrayList<>();
            for (int i = 0; i < numProducts1; i++) {
                line = br.readLine().split(" ");
                products1.add(new Product(line[0], line[1].charAt(0), Integer.parseInt(line[2])));
            }

            line = br.readLine().split(" ");
            int numProducts2 = Integer.parseInt(line[0]);
            List<Product> products2 = new ArrayList<>();
            for (int i = 0; i < numProducts2; i++) {
                line = br.readLine().split(" ");
                products2.add(new Product(line[0], line[1].charAt(0), Integer.parseInt(line[2])));
            }

            int maxTotalValue = 0;
            int minPairs = 0;

            Map<Character, List<Product>> productsByType1 = groupProductsByType(products1);
            Map<Character, List<Product>> productsByType2 = groupProductsByType(products2);

            for (Map.Entry<Character, List<Product>> entry : productsByType1.entrySet()) {
                char type = entry.getKey();
                List<Product> list1 = entry.getValue();
                List<Product> list2 = productsByType2.getOrDefault(type, Collections.emptyList());
                int numPairs = Math.min(list1.size(), list2.size());

                int totalValue = 0;
                for (int i = 0; i < numPairs; i++) {
                    totalValue += list1.get(i).value + list2.get(i).value;
                    list1.get(i).used = true;
                    list2.get(i).used = true;
                }

                for (Product product : list1) {
                    if (!product.used) {
                        totalValue += product.value;
                    }
                }

                for (Product product : list2) {
                    if (!product.used) {
                        totalValue += product.value;
                    }
                }

                maxTotalValue += totalValue;
                minPairs += numPairs;
            }

            System.out.println(maxTotalValue + " " + minPairs);
        }

        br.close();
    }

    private static Map<Character, List<Product>> groupProductsByType(List<Product> products) {
        Map<Character, List<Product>> map = new HashMap<>();
        for (Product product : products) {
            List<Product> list = map.getOrDefault(product.type, new ArrayList<>());
            list.add(product);
            map.put(product.type, list);
        }
        return map;
    }
}

