/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.t2;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author miguel
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    List<Client> findByFirstName(String firstName);
    
    Client findOneByUsername(String username);
    
    Client findOneByParticipantID(Integer participantID);
    
}