<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="logrosDeUsuario">
  <h2>Logros de <c:out value="${user.name}" /></h2>

  <table id="logrosTable" class="table table-striped">
    <tbody>
      <c:forEach items="${logros}" var="logro">
        <tr>
          <th>
            <td>Titulo: <c:out value="${logro.titulo}" /></td>
            <td>Descripcion: <c:out value="${logro.descripcion}" /></td>
          </th>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</petclinic:layout>
