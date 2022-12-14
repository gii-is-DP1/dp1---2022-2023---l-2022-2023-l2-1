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
         <th>Oponente</th>
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
                        <c:when test="${partida.idInvitado==null || partida.idInvitado==registeredUser.id}">
                            <c:if test="${partida.idInvitado==registeredUser.id}">
                                <c:forEach items="${compis}" var="user">
                                <c:if test="${user.id==partida.registeredUserId}">
                                 <c:out value="${user.user.username}"/>
                                </c:if>
                              </c:forEach>
                            </c:if>
                            <c:if test="${partida.idInvitado==null}">Ninguno</c:if>
                        </c:when>
                    <c:otherwise>
                        <c:forEach items="${compis}" var="user">
                          <c:if test="${user.id==partida.idInvitado}">
                           <c:out value="${user.user.username}"/>
                          </c:if>
                        </c:forEach>
                    </c:otherwise>
                    </c:choose>                  
                </td>
                <td>
                    <c:if test="${partida.tiempoDeJuego==null}">En Curso</c:if>
                    <c:if test="${partida.registeredUserId==registeredUser.id}"><c:out value="${partida.tiempoDeJuego}"/></c:if>
                    <c:if test="${partida.idInvitado==registeredUser.id}"><c:out value="${partida.tiempoDeJuegoInvitado}"/></c:if>
                </td>
                <td> 
                <c:choose>
                    <c:when test="${partida.tiempoDeJuego!=null}">
                    <c:if test="${partida.resultado==null}">Empate</c:if>
                    <c:if test="${partida.resultado==true}">Victoria</c:if>
                    <c:if test="${partida.resultado==false}">Derrota</c:if> 
                    </c:when>
                    <c:otherwise>
                        En curso
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
