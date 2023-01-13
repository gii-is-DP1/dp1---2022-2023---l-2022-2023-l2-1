<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="petclinic"
tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="estadisticas">
  <h2>Estadisticas de <c:out value="${user.name}" /></h2>

  <table id="estadisticasTable" class="table table-striped">
    <tbody>
      <tr>
        <th>
          Partidas totales: <c:out value="${historico.partidasTotales}" />
        </th>
      </tr>
      <tr>
        <th>
          Partidas Ganadas: <c:out value="${historico.partidasGanadas}" />
        </th>
      </tr>
      <tr>
        <th>Minas: <c:out value="${historico.minasEncontradas}" /></th>
      </tr>
      <tr>
        <th>Puntuacion: <c:out value="${historico.puntuacion}" /></th>
      </tr>      
      <tr>
        <th>
          Tiempo Total de Juego:
          <c:out value="${historico.tiempoTotalJuego}" />
        </th>
      </tr>
      <tr>
        <th>
          Tiempo medio de partida:
          <c:out value="${historico.tiempoMedioPartida}" />
        </th>
        <tr>
          <th><a href="/registeredUser/{registeredUserId}/ranking">Ver Ranking de usuarios</a></th>
        </tr>
      </tr>
    </tbody>
  </table>
</petclinic:layout>
