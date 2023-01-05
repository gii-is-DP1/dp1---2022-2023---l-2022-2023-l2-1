<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidas">
    <h2>Lista de Partidas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
         <th>Id</th>
         <th>Dificultad</th>
         <th>Modo de Juego</th>
         <th>Jugador Invitado</th>
         <th>Tiempo de juego</th>
         <th>Resultado</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
            <tr>
                <td>
                    <c:out value="${partida.id}"/>
                </td>
                <td>
                    <c:out value=" ${partida.dificultad}"/>
                </td>
                <td>
                    <c:out value=" ${partida.tipo}"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${partida.idInvitado==null}">
                            Ninguno
                        </c:when>
                    <c:otherwise>
                        <c:forEach items="${usuarios}" var="user">
                          <c:if test="${user.id==partida.idInvitado}">
                           <c:out value="${user.user.username}"/>
                          </c:if>
                        </c:forEach>
                    </c:otherwise>
                                        
                </td>
                <td>
                    <c:out value="${partida.tiempoDeJuego}"/>
                </td>
                <td> 
                    <c:if test="${partida.resultado==null}">En Curso</c:if>
                    <c:if test="${partida.resultado==true}">Victoria</c:if>
                    <c:if test="${partida.resultado==false}">Derrota</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
