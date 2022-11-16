<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

              <petclinic:layout pageName="Partidas">
                        <h2>Listado de Partidas Jugadas</h2>
                          <table id="estadisticasTable" class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Id de Partida</th>
                                    <th>Dificultad</th>
                                </tr>
                            </thead>
                            <tbody>
                                <td>
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
                            </td>
                            </tbody>
                        
                        <div class="row">

                        </div>
                    </petclinic:layout>