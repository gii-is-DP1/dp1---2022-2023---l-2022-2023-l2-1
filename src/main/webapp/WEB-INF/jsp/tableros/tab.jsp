<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="tablero">
    <h2><fmt:message key="Buscaminas"/></h2>
    <div class="row">
        <h2>tablero ${tablero.id}</h2>
        <p><h2>partida ${partida.id}</h2></p>
        <p><ul>
                <li>${tablero.filas},  ${tablero.columnas}</li>
        </ul></p>
        <div class="col-md-12">
            <spring:url value="/resources/images/minesweeper.png" htmlEscape="true" var="image"/>
            <img class="img-responsive" src="${image}"/>
        </div>
    </div>
</petclinic:layout>
