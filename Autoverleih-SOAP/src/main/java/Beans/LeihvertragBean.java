/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Kunde;
import Entity.Leihvertrag;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Raphael
 */
@Stateless
public class LeihvertragBean extends EntityBean<Leihvertrag, Long>{

    public LeihvertragBean() {
        super(Leihvertrag.class);
    }
    
    
    public List<Leihvertrag> getAllLeihvertragForKunde(long idKunde){
       return em.createQuery("SELECT l FROM Leihvertrag l WHERE l.kunde.id = :id ")
                .setParameter("id", idKunde)
                .getResultList();
    }
    
    public Leihvertrag createLeihvertrag(Leihvertrag leihvertrag){
       List<Leihvertrag> leihverträgeInTime = em.createQuery("SELECT l FROM Leihvertrag l WHERE l.fahrzeug.id = :idFahrzeug AND ((:startDate >= l.beginnDatum AND :startDate <= l.endeDatum) OR (:endDate <= l.endeDatum AND :endDate >= l.beginnDatum) OR (:startDate <= l.beginnDatum AND :endDate >= l.endeDatum))")
                .setParameter("startDate", leihvertrag.getBeginnDatum())
                .setParameter("endDate", leihvertrag.getEndeDatum())
                .setParameter("idFahrzeug", leihvertrag.getFahrzeug().getId())
                .getResultList();
       
       if(!leihverträgeInTime.isEmpty()) return null;
                
       return update(leihvertrag);
             
    }
}
