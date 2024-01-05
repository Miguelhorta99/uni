/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package tw.t2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author miguel
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan ({"tw.t2"})
public class T2 {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(T2.class, args);
        System.out.println("TWeb: aceder a http://localhost:8080/");
    }
}
