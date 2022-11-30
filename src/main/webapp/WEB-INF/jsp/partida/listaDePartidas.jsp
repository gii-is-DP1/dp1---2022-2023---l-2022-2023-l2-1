<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2>Lista de Partidas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
         <th>Id</th>
         <th>Dificultad</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
            <tr>
                <td>
                    <c:out value="${partida.id}"/>
                </td>
                <td>
                    <c:out value=" ${partida.dificultad}">

                    </c:out>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
