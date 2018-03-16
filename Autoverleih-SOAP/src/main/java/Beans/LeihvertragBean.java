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
       return em.createQuery("SELECT l FROM Leihvertrag l WHERE l.kunde = :id ")
                .setParameter("id", idKunde)
                .getResultList();
    }
}
