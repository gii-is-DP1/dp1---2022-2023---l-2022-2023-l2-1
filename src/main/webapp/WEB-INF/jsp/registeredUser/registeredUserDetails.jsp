<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="registered_users">
  <h2>
    <th><c:out value="${registeredUser.name}" /></th>

    <spring:url value="{registeredUserId}/edit" var="editUrl">
      <spring:param name="registeredUserId" value="${registeredUser.id}" />
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}"
      ><span class="glyphicon glyphicon-cog" aria-hidden="true"></span
    ></a>
  </h2>

  <table class="table table-striped">
    <tr>
      <td>
        <a
          class="btn btn-default"
          type="submit"
          href="/registeredUser/${registeredUser.id}/myLogros"
          >Logros
        </a>
      </td>
    </tr>
    <tr>
      <td>
        <a
          class="btn btn-default"
          type="submit"
          href="/registeredUser/${registeredUser.id}/estadisticas"
          >Estadisticas</a
        >
      </td>
    </tr>
    <tr>
      <td>
        <a
          class="btn btn-default"
          type="submit"
          href="/registeredUser/${registeredUser.id}/partidasJugadas"
          >Historial de partidas</a
        >
      </td>
    </tr>

    <tr>
      <th>Descripcion</th>
      <td><c:out value="${registeredUser.description}" /></td>
    </tr>
  </table>
</petclinic:layout>
