<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="nuevaIndividual">
    <h2> Crear nueva partida </h2>

            <h3>Elija dificultad</h3> <br/>    
            <c:choose>
                <c:when test="${registeredUser==null}">
                    <br/>
                    <a href="/partida/noUser/facil"><button class="btn btn-default">Facil</button></a>
                    <br/>
                    <a href="/partida/noUser/intermedio"><button class="btn btn-default">Intermedio</button></a>
                    <br/>
                    <a href="/partida/noUser/dificil"><button class="btn btn-default">Dificil</button></a>
                </c:when>
                <c:otherwise>
                 <form:form modelAttribute="partida" method="post" action="/registeredUser/${registeredUserId}/partidas/nuevaIndividual"
                  class="form-horizontal" id="add-partida-form">
                 <div class="form has-feedback">  
                    <form:hidden path = "tipo" value = "Individual" />
                    <petclinic:selectField name="dificultad" label="Dificultad" names="${dificultades}" size="1"/>
                    <br/>
                    <button class="btn btn-default" type="submit">Crear partida</button>
                     </div> 
                 </form:form>
                </c:otherwise>
            </c:choose>
       
  

</petclinic:layout>
