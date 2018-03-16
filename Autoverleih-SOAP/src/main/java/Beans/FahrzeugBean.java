/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Fahrzeug;

/**
 *
 * @author OL_SR
 */
public class FahrzeugBean extends EntityBean<Fahrzeug, Long>{
    
    public FahrzeugBean(Class<Fahrzeug> entityClass) {
        super(entityClass);
    } 
    
}
