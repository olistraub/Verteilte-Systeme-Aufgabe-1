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
public class Kategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long slug;
    
    private String name;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public Long getSlug() {
        return slug;
    }

    public void setSlug(Long slug) {
        this.slug = slug;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slug != null ? slug.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorie)) {
            return false;
        }
        Kategorie other = (Kategorie) object;
        if ((this.slug == null && other.slug != null) || (this.slug != null && !this.slug.equals(other.slug))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.kleinanzeigen.Kategorie[ slug=" + slug + " ]";
    }
    
}
