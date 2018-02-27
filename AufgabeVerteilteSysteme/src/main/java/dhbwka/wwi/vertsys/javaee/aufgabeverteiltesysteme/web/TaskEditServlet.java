/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.web;

import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.ejb.CategoryBean;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.ejb.TaskBean;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.jpa.Task;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.jpa.AdvertType;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.jpa.PriceType;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Seite zum Anlegen oder Bearbeiten einer Aufgabe.
 */
@WebServlet(urlPatterns = "/app/task/*")
public class TaskEditServlet extends HttpServlet {

    @EJB
    TaskBean taskBean;

    @EJB
    CategoryBean categoryBean;

    @EJB
    UserBean userBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("statuses", AdvertType.values());
        request.setAttribute("priceType", PriceType.values());
        
        // Zu bearbeitende Aufgabe einlesen
        HttpSession session = request.getSession();

        Task task = this.getRequestedTask(request);
        
        boolean readonly = true;
        
        if(task.getOwner().getUsername().equals(userBean.getCurrentUser().getUsername()))
                readonly = false;
        
        request.setAttribute("readonly", readonly);
        request.setAttribute("edit", task.getId() != 0);
                                
        if (session.getAttribute("task_form") == null) {
            // Keine Formulardaten mit fehlerhaften Daten in der Session,
            // daher Formulardaten aus dem Datenbankobjekt übernehmen
            request.setAttribute("task_form", this.createTaskForm(task));
        }

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/task_edit.jsp").forward(request, response);

        session.removeAttribute("task_form");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Angeforderte Aktion ausführen
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "save":
                this.saveTask(request, response);
                break;
            case "delete":
                this.deleteTask(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Neue oder vorhandene Aufgabe speichern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void saveTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        List<String> errors = new ArrayList<>();

        String taskCategory = request.getParameter("task_category");
        String taskStatus = request.getParameter("task_status");
        String taskShortText = request.getParameter("task_short_text");
        String taskLongText = request.getParameter("task_long_text");
        String priceType = request.getParameter("priceType");
        String priceField = request.getParameter("price_field");
        Task task = this.getRequestedTask(request);    PriceType.valueOf(priceType);
        
        
        if (taskCategory != null && !taskCategory.trim().isEmpty()) {
            try {
                task.setCategory(this.categoryBean.findById(Long.parseLong(taskCategory)));
            } catch (NumberFormatException ex) {
                // Ungültige oder keine ID mitgegeben
            }
        }

task.setcreatedOnDate(new Date(System.currentTimeMillis()));
task.setcreatedOnTime(new Time(System.currentTimeMillis()));

        try {
            task.setType(AdvertType.valueOf(taskStatus));
        } catch (IllegalArgumentException ex) {
            errors.add("Der ausgewählte Status ist nicht vorhanden.");
        }
        
        if(priceField == "")
            priceField = "0";
        
       try{
       task.setPrice(Double.parseDouble(priceField));
        }catch(NumberFormatException ex){
            errors.add("Ungültiger Preis");
        }
       
           try {
              task.setPriceType(PriceType.valueOf(priceType));
        } catch (IllegalArgumentException ex) {
            errors.add("Der ausgewählte Preistyp ist nicht vorhanden.");
        }
           
        task.setShortText(taskShortText);
        task.setLongText(taskLongText);

        this.validationBean.validate(task, errors);

        // Datensatz speichern
        if (errors.isEmpty()) {
            this.taskBean.update(task);
        }

        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/tasks/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("task_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
    }

    /**
     * Aufgerufen in doPost: Vorhandene Aufgabe löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datensatz löschen
        Task task = this.getRequestedTask(request);
        this.taskBean.delete(task);

        // Zurück zur Übersicht
        response.sendRedirect(WebUtils.appUrl(request, "/app/tasks/"));
    }

    /**
     * Zu bearbeitende Aufgabe aus der URL ermitteln und zurückgeben. Gibt
     * entweder einen vorhandenen Datensatz oder ein neues, leeres Objekt
     * zurück.
     *
     * @param request HTTP-Anfrage
     * @return Zu bearbeitende Aufgabe
     */
    private Task getRequestedTask(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        Task task = new Task();
        task.setOwner(this.userBean.getCurrentUser());
        task.setcreatedOnDate(new Date(System.currentTimeMillis()));
        task.setcreatedOnTime(new Time(System.currentTimeMillis()));

        // ID aus der URL herausschneiden
        String taskId = request.getPathInfo();

        if (taskId == null) {
            taskId = "";
        }

        taskId = taskId.substring(1);

        if (taskId.endsWith("/")) {
            taskId = taskId.substring(0, taskId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            task = this.taskBean.findById(Long.parseLong(taskId));
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return task;
    }

    /**
     * Neues FormValues-Objekt erzeugen und mit den Daten eines aus der
     * Datenbank eingelesenen Datensatzes füllen. Dadurch müssen in der JSP
     * keine hässlichen Fallunterscheidungen gemacht werden, ob die Werte im
     * Formular aus der Entity oder aus einer vorherigen Formulareingabe
     * stammen.
     *
     * @param task Die zu bearbeitende Aufgabe
     * @return Neues, gefülltes FormValues-Objekt
     */
    private FormValues createTaskForm(Task task) {
        Map<String, String[]> values = new HashMap<>();

        values.put("task_owner", new String[]{
            task.getOwner().getUsername()
        });

        if (task.getCategory() != null) {
            values.put("task_category", new String[]{
                task.getCategory().toString()
            });
        }

        values.put("task_due_date", new String[]{
            WebUtils.formatDate(task.getcreatedOnDate())
        });

        values.put("task_due_time", new String[]{
            WebUtils.formatTime(task.getcreatedOnTime())
        });

  /*      values.put("task_status", new String[]{
            task.getType().toString()
        });
*/
        values.put("task_short_text", new String[]{
            task.getShortText()
        });

        values.put("task_long_text", new String[]{
            task.getLongText()
        });
        /*
               ${task_form.values["name"][0]}
                 ${task_form.values["address"][0]}
                  ${task_form.values["ort"][0]}
                   ${task_form.values["telefon"][0]}
                    ${task_form.values["email"][0]}
        */
        
        values.put("name", new String[]{
        task.getOwner().getName()
        });
        
              values.put("address", new String[]{
        task.getOwner().getAnschrift()
        });

                    values.put("ort", new String[]{
        task.getOwner().getOrt()
        });
           
                          values.put("telefon", new String[]{
        task.getOwner().getTel()
        });
                    
                                values.put("email", new String[]{
        task.getOwner().getEmail()
        });
                                
                                 values.put("price_field", new String[]{
                                     
        Double.toString(task.getPrice())
        }); 
                                             
                                 
        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }

}
