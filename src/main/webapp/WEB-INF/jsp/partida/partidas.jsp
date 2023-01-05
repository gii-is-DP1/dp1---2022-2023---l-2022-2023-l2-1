<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8"%>

              <petclinic:layout pageName="Partidas">
                <script type="text/javascript">
                    function checkPass(contra2, idPartida) {
                       
                        if (document.getElementById(idPartida).value==contra2.toString()){
                          window.location.href= "/partida/"+idPartida.toString()+"/join";
                        }else{
                            alert("Contraseña incorrecta");
                        }
                      }                    
                    </script>
                    <c:forEach items="${tipoDePartidas}" var="tipos">
                        <c:set var="Competitivo" value="${tipos.name}"/>
                    </c:forEach>
            
                        <h2>Listado de Partidas Actuales</h2>
                          <table id="estadisticasTable" class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Id de Partida</th>
                                    <th>Dificultad</th>
                                    <th>Modo de Juego</th>
                                    <th>Jugador host</th>
                                    <th>Jugador Invitado</th>
                                    <th>Sala Privada</th>
                                    <th>(Contraseña) Unirse a la partida</th>
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
                                        <c:out value="${partida.tipo}"/>
                    
                                    </td>
                                    <td>
                                    <c:choose>
                                        <c:when test ="${partida.registeredUserId==null}">
                                            Usuario Invitado
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${usuarios}" var="user">
                                                 <c:if test="${user.id==partida.registeredUserId}">
                                                 <c:out value="${user.user.username}"/>
                                                 </c:if>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
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
                                    </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${partida.tipo!=Competitivo}"> 
                                                En Solitario
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${partida.privada==false}">Publica</c:if>
                                                <c:if test="${partida.privada==true}">Privada</c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                    <c:choose>
                                        <c:when test="${partida.privada==true}">
                                            <c:if test="${partida.idInvitado==null}">
                                                    <input type="text" id="${partida.id}"/>
                                                    <button onclick="checkPass('${partida.contrasenia}','${partida.id}');">Entrar</button>
                                            </c:if>                                         
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${partida.tipo==Competitivo}">
                                            <c:if test="${partida.idInvitado==null}">
                                                <a href="/partida/${partida.id}/join">Entrar</a>
                                            </c:if>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>                                                                   
                                </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        
                    </petclinic:layout>