<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<petclinic:layout pageName="elegir modo">
    <h2>Elija un modo de juego</h2>

    <a href="/registeredUser/${registeredUser.id}/partidas/nuevaIndividual"><button class="btn btn-default">Individual</button></a>

    <a href="/registeredUser/${registeredUser.id}/partidas/new"><button class="btn btn-default">Competitivo</button></a>

</petclinic:layout>
