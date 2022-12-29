<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="nuevaPartida">
    <h2> Crear nueva partida </h2>
    
    
    <form:form modelAttribute="partida" class="form-horizontal" id="add-partida-form">
        <div class="form-group has-feedback">       
            
            <petclinic:selectField name="tipo" label="Modo de juego" names="${tipoDePartidas}" size="1"/>
            <br />
            <form:checkbox path="privada"/> Sala privada
            <br />
            <petclinic:inputField label="Contraseña" name="contrasenia"/>
            <br />
            <petclinic:selectField name="dificultad" label="Dificultad" names="${dificultades}" size="1"/>

            <div class="col-sm-offset-2 col-sm-10">
    
                <button class="btn btn-default" type="submit">Crear partida</button>
          
            </div>
        </div>
        
    </form:form>

</petclinic:layout>
