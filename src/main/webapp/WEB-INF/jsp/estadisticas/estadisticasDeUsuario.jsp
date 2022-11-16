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
          Partidas totales: <c:out value="${estadistica.partidasTotales}" />
        </th>
      </tr>
      <tr>
        <th>
          Partidas Ganadas: <c:out value="${estadistica.partidasGanadas}" />
        </th>
      </tr>
      <tr>
        <th>Minas: <c:out value="${estadistica.minasEncontradas}" /></th>
      </tr>
      <tr>
        <th>Puntuacion: <c:out value="${estadistica.puntuacion}" /></th>
      </tr>
      <tr>
        <th>
          Tiempo Total de Juego:
          <c:out value="${estadistica.tiempoTotalJuego}" />
        </th>
      </tr>
      <tr>
        <th>
          Tiempo medio de partida:
          <c:out value="${estadistica.tiempoMedioPartida}" />
        </th>
      </tr>
      <tr>
        <th>Tiempo minimo: <c:out value="${estadistica.tiempoMinimo}" /></th>
      </tr>
      <tr>
        <th>Tiempo maximo: <c:out value="${estadistica.tiempoMaximo}" /></th>
      </tr>
    </tbody>
  </table>
</petclinic:layout>
