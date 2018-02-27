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
 * Statuswerte einer Aufgabe.
 */
public enum AdvertType {
    OFFER, SEARCH;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case OFFER:
                return "Biete";
            case SEARCH:
                return "Suche";
            default:
                return this.toString();
        }
    }
}
