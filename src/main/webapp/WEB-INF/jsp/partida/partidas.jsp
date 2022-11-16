<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

                    <petclinic:layout pageName="Partidas">
                        <h2>
                            Listado de Partidas

                            <c:forEach items="${partidas}" var="partida">
                                <br>
                                <c:out value="${partida.id} ${partida.registeredUserId} ${partida.dificultad}" />
                            </c:forEach>

                        </h2>
                        <div class="row">

                        </div>
                    </petclinic:layout>