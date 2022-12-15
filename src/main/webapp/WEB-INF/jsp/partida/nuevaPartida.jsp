<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="nuevaPartida">
    <h2> Crear nueva partida </h2>

    <form:form modelAttribute="partida" action = "tablero/${partida_id}}" class="form-horizontal" id="add-partida-form">
        <div class="form-group has-feedback">
       
        </select>        
        <body>Modo de juego</body>
        <form:select path="tipo">
                <form:option value="1">Individual</form:option>
                <form:option value="2">Competitivo</form:option>
        </form:select>
        <br />
        <input type="checkbox" name="privada" path="privada"/>  Sala privada
        <br />
        <body>Contraseña</body>
        <form:input label="contrasenia" path="contrasenia"/>
        <br />
        <body>Seleccione dificultad:</body>
        <form:select path="dificultad" name="dificultad_id">
                <form:option value="1">Facil</form:option>
                <form:option value="2">Intermedio</form:option>
                <form:option value="3">Dificil</form:option>
        </form:select>

        </div>
        <div class="col-sm-offset-2 col-sm-10">
    
            <button class="btn btn-default" type="submit">Crear partida</button>
          
        </div>
    </form:form>

</petclinic:layout>
