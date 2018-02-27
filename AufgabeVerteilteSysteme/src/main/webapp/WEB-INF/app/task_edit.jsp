<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        <c:choose>
            <c:when test="${edit}">
                Angebot anzeigen
            </c:when>
            <c:otherwise>
                Angebot anlegen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/tasks/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}" ${readonly ? 'readonly="readonly"' : ''}>

                <%-- Eingabefelder --%>
                
                      <label for="task_category">Kategorie:</label>
                <div class="side-by-side">
                    <select name="task_category" ${readonly ? 'disabled="readonly"' : ''}>
                        <option value="">Keine Kategorie</option>

                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" ${task_form_values[1] == category.name ? 'selected' : ''}>
                                <c:out value="${category.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                      
                      
                       <label for="task_status">
                    Art des Angebots:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side margin">
                    <select name="task_status" ${readonly ? 'disabled="readonly"' : ''}>
                        <c:forEach items="${statuses}" var="status">
                            <option value="${status}" ${task_form_values[12] == status ? 'selected' : ''}>
                                <c:out value="${status.label}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <label for="task_short_text">
                    Bezeichnung:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="task_short_text" value="${task_form_values[4]}" ${readonly ? 'readonly="readonly"' : ''}>
                </div>
      
                      
                            <label for="task_long_text">
                    Beschreibung:
                </label>
                <div class="side-by-side">
                    <textarea name="task_long_text" ${readonly ? 'readonly="readonly"' : ''}><c:out value="${task_form_values[5]}"/></textarea>
                </div>  
                      
                      
                <label for="task_price">
                    Preis:
              
                </label>
                <div class="side-by-side">
                         <select name="priceType" ${readonly ? 'disabled="readonly"' : ''}>
                        <c:forEach items="${priceType}" var="price">
                            <option value="${price}" ${task_form_values[13] == price ? 'selected' : ''}>
                                <c:out value="${price.label}"/>
                            </option>
                        </c:forEach>
                            
                         </select>
                    
                    <input type="text" name="price_field" value="${task_form_values[11]}" ${readonly ? 'readonly="readonly"' : ''}>
                </div>
                         
                <label for="lblAngelegt">
                Angelegt Am: 
                </label>
                <div class="side-by-side">
                ${task_form_values[2]}
                ${task_form_values[3]}
                </div>
                
                       <label for="lblAngelegt">
                Anbieter: 
                </label>
                <div class="side-by-side">
                    ${task_form_values[6]} <br>
                 ${task_form_values[7]} <br>
                  ${task_form_values[8]} <br>
                   ${task_form_values[9]}<br>
                    ${task_form_values[10]}<br>
                </div>
        <c:if test="${readonly == false}">
      

                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save">
                        Sichern
                    </button>

                    <c:if test="${edit}">
                        <button class="icon-trash" type="submit" name="action" value="delete">
                            Löschen
                        </button>
                    </c:if>
                </div>
            </div>
</c:if>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty task_form.errors}">
                <ul class="errors">
                    <c:forEach items="${task_form.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>