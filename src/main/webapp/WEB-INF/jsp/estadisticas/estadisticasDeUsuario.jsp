<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="estadisticas">                        
    <h2>Estadisticas</h2>
        <table id="estadisticasTable" class="table table-striped">
                <thead>
                    <tr>
                        <th>Partidas ganadas</th>
                        <th>Minas encontradas</th>
                        <th>Puntuacion total</th>
                        <th>Tiempo Total de Juego </th>
                        <th>Tiempo medio de partida</th>
                        <th>Tiempo maximo</th>
                        <th>Tiempo minimo</th>
                    </tr>
                </thead>
                <tbody>
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
</petclinic:layout>