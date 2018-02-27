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

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Eine zu erledigende Aufgabe.
 */
@Entity
public class Advert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "task_ids")
    @TableGenerator(name = "task_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @ManyToOne
    @NotNull(message = "Die Aufgabe muss einem Benutzer geordnet werden.")
    private User owner;

    @ManyToOne
    private Category category;

    @Column(length = 50)
    @NotNull(message = "Die Bezeichnung darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Die Bezeichnung muss zwischen ein und 50 Zeichen lang sein.")
    private String shortText;

    @Lob
    @NotNull
    private String longText;

    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date CreatedOnDate = new Date(System.currentTimeMillis());

    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private Time CreatedOnTime = new Time(System.currentTimeMillis());

    @Enumerated(EnumType.STRING)
    @NotNull
    private PriceType priceType;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private AdvertType type;

    @NotNull(message = "Der Preis darf nicht leer sein")
    private double price;
    
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Advert() {
    }

    public Advert(User owner, Category category, String shortText, String longText, PriceType priceType, AdvertType type, double price) {
        this.owner = owner;
        this.category = category;
        this.shortText = shortText;
        this.longText = longText;
        this.priceType = priceType;
        this.type = type;
        this.price = price;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">

    public Date getCreatedOnDate() {
        return CreatedOnDate;
    }

    public void setCreatedOnDate(Date CreatedOnDate) {
        this.CreatedOnDate = CreatedOnDate;
    }

    public Time getCreatedOnTime() {
        return CreatedOnTime;
    }

    public void setCreatedOnTime(Time CreatedOnTime) {
        this.CreatedOnTime = CreatedOnTime;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public AdvertType getType() {
        return type;
    }

    public void setType(AdvertType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public Date getDueDate() {
        return CreatedOnDate;
    }

    public void setDueDate(Date dueDate) {
        this.CreatedOnDate = dueDate;
    }

    public Time getDueTime() {
        return CreatedOnTime;
    }

    public void setDueTime(Time dueTime) {
        this.CreatedOnTime = dueTime;
    }

    public AdvertType getStatus() {
        return type;
    }

    public void setStatus(AdvertType type) {
        this.type = type;
    }
    //</editor-fold>

}
