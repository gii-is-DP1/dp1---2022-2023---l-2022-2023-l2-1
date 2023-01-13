<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                        <petclinic:layout pageName="estadisticas">
                            <h2>Ranking de jugadores</h2>

                            <c:set var="num" value="0"></c:set>
                            <table id="estadisticasTable" class="table table-striped">
                                <tbody>
                                    <c:forEach items="${historicos}" var="h">
                                        <tr>
                                            <th>
                                                <c:set var="num" value="${num+1}"></c:set>
                                                <c:out value="${num}" />
                                                -- Puntos: 
                                                <c:out value="${h.puntuacion}" /> -- Usuario:
                                                 <c:forEach items="${users}" var="u">
                                                    <c:if test="${h.registeredUserId==u.id}">
                                                        <c:out value="${u.name}"></c:out>
                                                    </c:if>
                                                </c:forEach> 
                                            </th>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </petclinic:layout>