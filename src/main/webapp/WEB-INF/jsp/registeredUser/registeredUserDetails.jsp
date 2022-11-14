<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="registered_users">

    <h2>
        <th><c:out value="${registeredUser.user.username}"/></th>
        
        <spring:url value="{registeredUserId}/edit" var="editUrl">
            <spring:param name="registeredUserId" value="${registeredUser.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(editUrl)}"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span></a>

    </h2>

    <table class="table table-striped">

        <tr>
            <td>
                <button class="btn btn-default" type="submit">Logros</button>
            </td>
        </tr>
        <tr>
            <td>
                <button class="btn btn-default" type="submit">Estadisticas</button>
            </td>
        </tr>
        <tr>
            <td>
                <button class="btn btn-default" type="submit">Historial de partidas</button>
            </td>
        </tr>
        
        <tr>
            <th>Description</th>
            <td><c:out value="${registeredUser.description}"/></td>
        </tr>

    </table>

    



</petclinic:layout>