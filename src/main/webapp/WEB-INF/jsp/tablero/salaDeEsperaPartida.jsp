<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<style></style>

<head>
  <c:url value="/tablero/ + ${partida.id}" var="enlace" />
  <c:url value="/css/style.css" var="estilo" />
</head>

<petclinic:layout pageName="salaDeEsperaPartida">
  <h1>Esperando al segundo jugador...</h1>
  <input type="hidden" id="idInvitado" value="${partida.idInvitado}" />
  <input type="hidden" id="idPartida" value="${partida.id}" />

  <script type="text/javascript">
    var idInvitado = document.getElementById("idInvitado").value;
    var idPartida = document.getElementById("idPartida").value;

    var intervalo = setInterval(function () {
      if (idInvitado == "") {
        window.location.href = "/registeredUser/" + idPartida + "/reload";
      } else {
        clearInterval(intervalo);
        window.location.href = "/tablero/" + idPartida;
      }
    }, 3000);
  </script>

  <script type="text/javascript" src="${algTablero}"></script>
</petclinic:layout>
