<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                        <!-- <petclinic:layout pageName="estadisticas" class="table">
                            Estadisticas
                            <thead>
                            <tr>
                                    <th>
                                        Partidas ganadas
                                    </th>
                                    <th>
                                        Minas encontradas
                                    </th>
                                    <th>
                                        Partidas totales
                                    </th>
                                    <th>
                                        Puntuacion
                                    </th>
                                    <th>
                                        Tiempo total de juego
                                    </th>
                                    <th>
                                        Tiempo medio de partida
                                    </th>
                                    <th>
                                        Tiempo minimo
                                    </th>
                                    <th>
                                        Tiempo maximo
                                    </th>


                                </tr>

                            </thead>


                            <tbody>

                                <tr>


                                    <td>

                                        <c:out value="${historico.partidasGanadas}" />

                                    </td>
                                    <td>

                                        <c:out value="${historico.minasEncontradas}" />

                                    </td>
                                    <td>

                                        <c:out value="${historico.puntuacion}" />

                                    </td>
                                    <td>

                                        <c:out value="${historico.tiempoTotalJuego}" />

                                    </td>
                                    <td>

                                        <c:out value="${historico.tiempoMedioPartida}" />

                                    </td>
                                    <td>

                                        <c:out value="${historico.tiempoMinimo}" />

                                    </td>
                                    <td>

                                        <c:out value="${historico.tiempoMaximo}" />

                                    </td>
                                </tr>
                            </tbody>
                            </table>
                        </petclinic:layout> -->

                        <petclinic:layout pageName="estadisticas">
                            <h2>Estadisticas</h2>

                            <table id="estadisticasTable" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Minas</th>
                                        <th>Partidas Ganadas</th>
                                        <th>Tiempo Total de Juego </th>
                                        <th>Name</th>
                                        <th>Dificultad</th>
                                        <th>Name</th>
                                        <th>Dificultad</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${historicoPartidas}" var="partida">
                                        <tr>
                                            <td>
                                                <c:out value="Id: ${partida.id}" />
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