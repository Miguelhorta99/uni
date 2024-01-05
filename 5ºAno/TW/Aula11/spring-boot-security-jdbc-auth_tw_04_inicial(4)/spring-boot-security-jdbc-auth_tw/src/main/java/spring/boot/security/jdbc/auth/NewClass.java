/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spring.boot.security.jdbc.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author miguel
 */
public class NewClass {

    public static void main(String[] args) {
        BCryptPasswordEncoder benc = new BCryptPasswordEncoder();
        String rawPassword = "user3Novapass";
        String ep = benc.encode(rawPassword.subSequence(0, rawPassword.length()));
        System.out.println("PWD: " + ep);
    }
}
