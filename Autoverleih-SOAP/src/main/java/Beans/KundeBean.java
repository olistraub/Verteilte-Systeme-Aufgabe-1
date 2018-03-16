/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Kunde;
import javax.ejb.Stateless;


/**
 *
 * @author OL_SR
 */
@Stateless
public class KundeBean extends EntityBean<Kunde, Long>{

    public KundeBean(){
        super(Kunde.class);
    }
    
}
