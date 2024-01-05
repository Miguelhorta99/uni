package com.example.servingwebcontent;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    //@GetMapping("/greeting")
    //public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
    //    model.addAttribute("name", name);
    //    System.out.println(" \n OLA CONTROLADOR \n");
    //    return "greeting";
    //}

    @PostMapping("/resposta")
    public String atendimento(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            @RequestParam(name = "cidade", required = false, defaultValue = "Paris") String cidade,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("cidade", cidade);
        System.out.println("RECEBEMOS: " + name);
        System.out.println("RECEBEMOS: " + cidade);
        return "resposta";  // nome do template usado para processar a vista devolvida
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model, HttpSession session) {
        //model.addAttribute("name", name);
        
        List<String> lnomes = (List<String>) session.getAttribute("x");
        
        if(lnomes == null){
            lnomes = new LinkedList<String>();
            session.setAttribute("x", lnomes);
        }
        lnomes.add(name);
        
        model.addAttribute("allNames", lnomes.toString());
        System.out.println(" SESSION " + session.getAttribute("x"));
        return "greeting";
    }
}
