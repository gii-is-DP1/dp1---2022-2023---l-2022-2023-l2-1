<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="Editar Logro">
    <form:form modelAttribute="logro" method="post" action="/logros/new">
        <div class="mb-3">
            <petclinic:inputField label="Título" name="titulo"/>
            <br>
            <petclinic:inputField label="Descripción  " name="descripcion"/>
            <br>
            <div>
                <petclinic:selectField label="Condición" size="1" name="condicion" names ="${condiciones}"/>
                <petclinic:inputField label="Atributo" name="valor"/>
            </div>
            <br>
        <br>
        </div>
            <button type="submit" class="btn btn-primary">Crear Logro</button>
    </form:form>
</petclinic:layout>