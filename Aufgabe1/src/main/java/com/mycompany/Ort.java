/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kleinanzeigen;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author StudiumMax
 */
@Entity
public class Ort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private long plz;
    private String name;
    private String land;
    private boolean aktivkennzeichen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public long getPlz(){
        return plz;
    }
    
    public void setPlz(long plz){
        this.plz = plz;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getLand(){
        return land;
    }
    
    public void setLand(String land){
        this.land = land;
    }    
    
    public boolean getAktivkennzeichen(){
        return aktivkennzeichen;
    }
    
    public void setAktivkennzeichen(boolean aktivkennzeichen){
        this.aktivkennzeichen = aktivkennzeichen;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ort)) {
            return false;
        }
        Ort other = (Ort) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.kleinanzeigen.Ort[ id=" + id + " ]";
    }
    
}
