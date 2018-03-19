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
    public Kunde registerKunde(@WebParam(name = "kunde") Kunde kunde){
        return kundeBean.update(kunde);
    } 
    
    @WebMethod
    @WebResult(name = "Fahrzeug")
    public Fahrzeug registerFahrzeug(@WebParam(name = "fahrzeug") Fahrzeug fahrzeug, @WebParam(name = "baujahrAsLong") long baujahr){ // Workaround for Date
        Date dateBaujahr = new Date(baujahr);
        fahrzeug.setBaujahr(dateBaujahr);
        return fahrzeugBean.update(fahrzeug);
    }
    
    
    @WebMethod
    @WebResult(name = "Fahrzeug")
    public List<Fahrzeug> listAllFahrzeug(){
        return fahrzeugBean.findAll();
    }
    
    @WebMethod
    @WebResult(name = "Leihvertrag")
    public Leihvertrag createLeihvertrag(@WebParam(name = "leihvertrag") Leihvertrag leihvertrag, @WebParam(name = "startDatumAsLong") long startDatum, @WebParam(name = "endDatumAsLong") long endDatum){
        Date startDate = new Date(startDatum);
        Date endDate = new Date(endDatum);
        leihvertrag.setBeginnDatum(startDate);
        leihvertrag.setEndeDatum(endDate);
        
        return leihvertragBean.createLeihvertrag(leihvertrag);
    }
    
}
