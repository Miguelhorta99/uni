/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.t2;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author miguel
 */

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{
    
    List<Anuncio> findAll();
    
    //Anuncio findOneByEventName(String eventName);
    
    List<Anuncio> findAllByAnuncioID(String tipo);
    
    //List<Anuncio> findAllByAnuncioDetalhes(Integer id);
    
    //List<Event> findAllByEventID(Integer eventID);
    
    //Anuncio findOneByEventID(Integer eventID);
}

