/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.t2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author miguel
 */
@Controller
public class RoleController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/role-controller")
    public String listProduct(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Client client = clientRepository.findOneByUsername(auth.getName());

        switch (client.getRole()) {

            case ("USER"):
                System.out.println("HELLOOOOO USER");
                return "redirect:/main_page_user";

            case ("ADMIN"):
                System.out.println("HELLOOOOO ADMIN");
                return "redirect:/main_page_admin";
        }

        return "";
    }

}
