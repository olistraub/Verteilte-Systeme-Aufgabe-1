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
public class Bild implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String bezeichnung;
    private String bilddaten;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBezeichnung(){
        return bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }
    
    public String getBilddaten(){
        return bilddaten;
    }
    
    public void setBilddaten(String bilddaten){
        this.bilddaten = bilddaten;
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
        if (!(object instanceof Bild)) {
            return false;
        }
        Bild other = (Bild) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.kleinanzeigen.Bild[ id=" + id + " ]";
    }
    
}
