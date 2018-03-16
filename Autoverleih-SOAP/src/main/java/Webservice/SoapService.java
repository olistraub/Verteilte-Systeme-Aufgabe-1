/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Webservice;

import Beans.FahrzeugBean;
import Beans.KundeBean;
import Beans.LeihvertragBean;
import Entity.Fahrzeug;
import Entity.Kunde;
import Entity.Leihvertrag;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author OL_SR
 */

@WebService
public class SoapService {
    
    @EJB
    LeihvertragBean leihvertragBean;
    
    @EJB
    KundeBean kundeBean;
    
    @EJB
    FahrzeugBean fahrzeugBean;
    
    @WebMethod
    @WebResult(name = "leihvertrag")
    public List<Leihvertrag> getAllLeihvertragForKunde(long idKunde){
        return leihvertragBean.getAllLeihvertragForKunde(idKunde);
    }
    
    @WebMethod
    @WebResult(name = "Kunde")
    public Kunde registerKunde(String vorname, String nachname, String straße, String hausnr, String plz, String ort, String land){
        return kundeBean.update(new Kunde(vorname, nachname, straße, hausnr, plz, ort, land));
    } 
    
    @WebMethod
    @WebResult(name = "Fahrzeug")
    public Fahrzeug registerFahreug(String hersteller, String modell, Date baujahr){
        return fahrzeugBean.update(new Fahrzeug(hersteller, modell, baujahr));
    }
    
    
    @WebMethod
    @WebResult(name = "Fahrzeug")
    public List<Fahrzeug> listAllFahrzeug(){
        return fahrzeugBean.findAll();
    }
    
    @WebMethod
    @WebResult(name = "Leihvertrag")
    public Leihvertrag createLeihvertrag(long idKunde, long idFahrzeug, Date startDate, Date endDate){
        leihvertragBean.update(new Leihvertrag())
    }
    
}
