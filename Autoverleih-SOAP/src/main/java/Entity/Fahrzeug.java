/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author OL_SR
 */
@Entity
public class Fahrzeug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String hersteller = "";
    
    private String modell = "";
    
    @Temporal(TemporalType.DATE)
    private Date baujahr;
    
    public Fahrzeug(){
    }
    
    public Fahrzeug(String hersteller, String modell, Date baujahr){
        this.hersteller = hersteller;
        this.modell = modell;
        this.baujahr = baujahr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public Date getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(Date baujahr) {
        this.baujahr = baujahr;
    }
    
    
}