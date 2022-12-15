<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

              <petclinic:layout pageName="Partidas">
                        <h2>Listado de Partidas Actuales</h2>
                          <table id="estadisticasTable" class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Id de Partida</th>
                                    <th>Dificultad</th>
                                    <th>Jugador host</th>
                                    <th>Sala Privada</th>

                                 </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${partidas}" var="partida">

                                <tr>
                                    <td>
                                        <c:out value="${partida.id}"/>
                                    </td>
                                 
                                    <td>
                                        <c:out value="${partida.dificultad}"/>
                    
                                    </td>
                                    <td>
                                        <c:forEach items="${usuarios}" var="user">
                                        <c:if test="${user.id==partida.registeredUserId}">
                                        <c:out value="${user.user.username}"/>
                                        </c:if>
                                        </c:forEach>
                                        
                                    </td>
                                    <td>
                                        <c:if test="${partida.privada==false}">Publica</c:if>
                                        <c:if test="${partida.privada==true}">Privada</c:if>
                    
                                    </td>
                                 
                                </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        <div class="row">

                        </div>
                    </petclinic:layout>