<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<style>
  #tablero {
    border: 2px solid black;
    border-radius: 10px;
    max-width: fit-content;
    padding-left: 10px;
    padding-bottom: 10px;
    padding-right: 10px;
    margin: auto;
    background-color: rgb(255, 149, 61);
  }
  
  #cabecera_tablero {
    padding: 10px;
    min-width: 400px;
    min-height: 50px;
    width: 99%;
    margin: auto;
    height: 50px;
    margin-bottom: 10px;
    display: flex;
    align-content: center;
  }
  #tablero div div {
    border-style: outset;
    width: 35px;
    height: 35px;
    margin: 2px;
    background-color: rgb(180, 180, 180);
    font-size: 20px;
    text-align: center;
    line-height: 40px;
  }

  #img_bandera{
    font-size: 25px;
  }

  #img_mina {
    width: 30px;
    height: 30px;
  }

  #num_bandera,
  #num_mina,
  #temporizador {
    font-size: 25px;
    color: rgb(8, 3, 83);
  }

  #num_bandera{
    margin-left: 10px;
  }
  #num_mina {
    margin-right: 10px;
  }
  #temporizador{
    margin: auto;
  }

  #fila {
    display: flex;
  }
  h1 {
    text-align: center;
  }

  /*  */

  .icon-bandera:before {
    content: "\2691";
    color: rgb(187, 0, 0);
    font-size: 25px;
  }

  .icon-mina:before {
    content: "\1F4A3";
    font-size: 25px;
  }

  .icon-escudo:before{
    content: "\1F6E1";
    font-size: 25px;
  }

  .bandera-erronea:before {
    content: "\2612";
    color: red;
    font-size: 25px;
  }
  .bandera-correcta:before {
    content: "\2611";
    color: green;
    font-size: 25px;
  }

  #tablero div div.destapada {
    border-style: none;
  }

  #tablero div div.c1 {
    color: darkcyan;
  }
  #tablero div div.c2 {
    color: blue;
  }
  #tablero div div.c3 {
    color: green;
  }
  #tablero div div.c4 {
    color: greenyellow;
  }
  #tablero div div.c5 {
    color: steelblue;
  }
  #tablero div div.c6 {
    color: brown;
  }
  #tablero div div.c7 {
    color: blueviolet;
  }
  #tablero div div.c8 {
    color: darkgoldenrod;
  }

  .alerta-final{
    background-color: white;
    border: 10px solid red;
    border-style: outset;transform: translate(calc(50vw - 50%));
    border-radius: 10px;
    padding: 20px;
    font-size: 20px;
    text-align: center;
    position: fixed;
    top: 0;
    left: 0;
    transform: translate(calc(50vw - 50%), calc(50vh - 50%));
  }
</style>

<head>
  <c:url value="/resources/images/bandera.png" var="img_bandera" />
  <c:url value="/resources/images/minesweeper.png" var="img_mina" />
  <c:url value="/js/algTablero.js" var="algTablero" />
  <c:url value="/css/style.css" var="estilo" />
  <c:set value="" var="conUsuario"/>
  <c:set value="" var="sinUsuario"/>
  <!-- <link href="${estilo}" rel="stylecheet" type="text/css" /> -->
</head>

<petclinic:layout pageName="tablero">
  <!-- Datos del tablero para el js -->
  <input type="hidden" id="numFilas" value="${tablero.filas}" />
  <input type="hidden" id="numColumnas" value="${tablero.columnas}" />
  <input type="hidden" id="numMinas" value="${tablero.minas}" />
  <!--  -->

  <div id="tablero">
    <div id="cabecera_tablero">
      <span id="img_bandera">&#9971;</span>
      <span id="num_bandera"></span>
      <span id="temporizador"></span>
      <span id="num_mina"></span>
      <img id="img_mina" src="${img_mina}" />
    </div>
  </div>

  <div id="alert_parent">
    <div id="alert_children">
      <Span id="alert_mensaje_general"></Span>
      <br>
      <Span id="alert_mensaje"></Span>
      <br>
          <!-- <input type="text" id="minas_encontradas_final" value=""/>
          <input type="text" id="tiempo_empleado_final" value=""/> -->
          <!-- <input type="hidden" id="alert_boton" value="Continuar" /> -->
          <button onclick="enlacePostTablero();">Continuar</button>
          <script type="text/javascript">
            function enlacePostTablero(){
              window.location.href="/postTablero/"+tablero.numMinasEncontradas+"/"+tablero.tiempoEmpleado+"/"+tablero.esVictoria;
            }
            </script>
    </div>
  </div>
  <script type="text/javascript" src="${algTablero}"></script>
</petclinic:layout>
