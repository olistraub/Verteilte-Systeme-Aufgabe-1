/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.jpa;

/**
 *
 * @author OL_SR
 */
public enum PriceType {
    FIXEDPRICE, BASIS_FOR_NEGOTIATION;
    public String getLabel(){
        switch(this){
            case FIXEDPRICE:
                return "Festpreis";
            case BASIS_FOR_NEGOTIATION:
                return "Verhandulngsbasis";
            default:
                return this.toString();
        }
    }
    
}
