/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.t2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author miguel
 */
@Controller
public class AnuncioController {

    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private ClientRepository clientRepository;

    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/addNewAnuncio")
    public String addNewAnuncio(@RequestParam(name = "anuncianteAnuncio", required = false, defaultValue = "") String anuncianteAnuncio,
            @RequestParam(name = "zonaAnuncio", required = false, defaultValue = "") String zonaAnuncio,
            @RequestParam(name = "estadoAnuncio", required = false, defaultValue = "") String estadoAnuncio,
            @RequestParam(name = "detalhesAnuncio", required = false, defaultValue = "") String detalhesAnuncio,
            @RequestParam(name = "tipoAnuncio", required = false, defaultValue = "") String tipoAnuncio,
            @RequestParam(name = "generoAnuncio", required = false, defaultValue = "") String generoAnuncio,
            @RequestParam(name = "contactoAnuncio", required = false, defaultValue = "") Integer contactoAnuncio,
            @RequestParam(name = "idAnuncio", required = false, defaultValue = "") Integer idAnuncio,
            @RequestParam(name = "precoAnuncio", required = false, defaultValue = "") Integer precoAnuncio,
            @RequestParam(name = "year", required = false, defaultValue = "") Integer year,
            @RequestParam(name = "month", required = false, defaultValue = "") Integer month,
            @RequestParam(name = "day", required = false, defaultValue = "") Integer day,
            Model model) {

        String monthS, dayS;

        monthS = month.toString();
        dayS = day.toString();

        if (month < 10) {
            monthS = '0' + month.toString();
        }
        if (day < 10) {
            dayS = '0' + day.toString();
        }

        String dateAnuncio = year.toString().concat("-").concat(monthS).concat("-").concat(dayS);

        Anuncio anuncio = new Anuncio(anuncianteAnuncio, zonaAnuncio, estadoAnuncio, detalhesAnuncio, tipoAnuncio, generoAnuncio, dateAnuncio, contactoAnuncio, idAnuncio, precoAnuncio);

        anuncioRepository.save(anuncio);

        return "redirect:/main_page_admin";
    }

    @PostMapping("/searchAnuncios")
    @ResponseBody
    public JSONObject searchAnuncios(@ModelAttribute("tipo") String tipo) throws ParseException {

        List<Anuncio> list;
        List<Anuncio> anotherList;
        anotherList = new ArrayList<>();
        JSONObject json = new JSONObject();

        list = anuncioRepository.findAll();

        if (tipo.equals("procura")) {

            for (int i = 0; i < list.size(); i++) {

                anotherList.add(list.get(i));

            }

            json.put("list", anotherList);

            return json;

        } else if (tipo.equals("oferta")) {

            for (int i = 0; i < list.size(); i++) {

                anotherList.add(list.get(i));

            }

            json.put("list", anotherList);

            return json;
        }

        list = anuncioRepository.findAllByAnuncioID(tipo);

        json.put("list", list);

        return json;
    }
    /*
    @PostMapping("/searchAnunciosDetalhes")
    @ResponseBody
    public JSONObject searchAnunciosDetalhes(@ModelAttribute("id") Integer id) throws ParseException {

        List<Anuncio> list;
        List<Anuncio> anotherList;
        anotherList = new ArrayList<>();
        JSONObject json = new JSONObject();

        list = anuncioRepository.findAll();

        if (id.equals("procura")) {

            for (int i = 0; i < list.size(); i++) {

                anotherList.add(list.get(i));

            }

            json.put("list", anotherList);

            return json;

        } 

        list = anuncioRepository.findAllByAnuncioDetalhes(id);

        json.put("list", list);

        return json;
    }

*/

}
