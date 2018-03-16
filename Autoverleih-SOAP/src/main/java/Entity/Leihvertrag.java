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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity für einen Leihvertrag
 */
@Entity
public class Leihvertrag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    private Kunde kunde;
    
    @ManyToOne
    private Fahrzeug fahrzeug;
    
    @Temporal(TemporalType.DATE)
    private Date beginnDatum = new Date();
    
    @Temporal(TemporalType.DATE)
    private Date endeDatum = new Date();
}
