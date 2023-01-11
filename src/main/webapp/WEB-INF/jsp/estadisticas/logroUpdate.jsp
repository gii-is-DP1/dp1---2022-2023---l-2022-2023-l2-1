<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="petclinic"
tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="Editar Logro">
    <form:form modelAttribute="logro" method="post" action="/logros/${logroId}/edit"
     class="form-horizontal" id="add-logro-form">
        <div class="form has-feedback">       
            <petclinic:inputField label="Titulo" name="logro.titulo"/>
            <petclinic:inputField label="Descripcion" name="logro.descripcion"/>
            <petclinic:inputField label="Condicion" name="logro.condicion"/>
        </div>
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default" type="submit">Añadir Logro</button>
        </div>
    </form:form>
</petclinic:layout>