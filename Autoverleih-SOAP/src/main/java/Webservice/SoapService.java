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
import javax.jws.WebParam;
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
    public List<Leihvertrag> getAllLeihvertragForKunde(@WebParam(name = "idKunde")long idKunde){
        return leihvertragBean.getAllLeihvertragForKunde(idKunde);
    }
    
    @WebMethod
    @WebResult(name = "Kunde")
    public Kunde registerKunde( @WebParam(name = "vorname")String vorname, @WebParam(name = "nachname")String nachname, 
            @WebParam(name = "Straße")String straße, @WebParam(name = "Hausnummer")String hausnr,
            @WebParam(name = "PLZ")String plz, @WebParam(name = "Ort")String ort, @WebParam(name = "Land")String land){
        return kundeBean.update(new Kunde(vorname, nachname, straße, hausnr, plz, ort, land));
    } 
    
    @WebMethod
    @WebResult(name = "Fahrzeug")
    public Fahrzeug registerFahreug(@WebParam(name = "hersteller")String hersteller, @WebParam(name = "modell")String modell, @WebParam(name = "baujahr")Date baujahr){
        return fahrzeugBean.update(new Fahrzeug(hersteller, modell, baujahr));
    }
    
    
    @WebMethod
    @WebResult(name = "Fahrzeug")
    public List<Fahrzeug> listAllFahrzeug(){
        return fahrzeugBean.findAll();
    }
    
    @WebMethod
    @WebResult(name = "Leihvertrag")
    public Leihvertrag createLeihvertrag(@WebParam(name = "idKunde")long idKunde,@WebParam(name = "idFahrzeug") long idFahrzeug,@WebParam(name = "startDate") Date startDate,@WebParam(name = "endDate") Date endDate){
        return leihvertragBean.createLeihvertrag(new Leihvertrag(kundeBean.findById(idKunde), fahrzeugBean.findById(idFahrzeug), startDate, endDate));
    }
    
}
