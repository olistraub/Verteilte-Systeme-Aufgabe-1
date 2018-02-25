/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kleinanzeigen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author StudiumMax
 */
@Entity
public class Benutzer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String benutzername;
    
    private String email;
    private String passwort;
    private String Straße;
    private int hausnummer;
    private String name;
    private String vorname;
    private String telnr;
    
    @ManyToOne
    Ort ort = null;

    @ManyToMany
    List<Artikel> merklist = new ArrayList<>();
    
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getStraße() {
        return Straße;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getTelnr() {
        return telnr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setStraße(String Straße) {
        this.Straße = Straße;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (benutzername != null ? benutzername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Benutzer)) {
            return false;
        }
        Benutzer other = (Benutzer) object;
        if ((this.benutzername == null && other.benutzername != null) || (this.benutzername != null && !this.benutzername.equals(other.benutzername))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.kleinanzeigen.Benutzer[ id=" + benutzername + " ]";
    }
    
}
