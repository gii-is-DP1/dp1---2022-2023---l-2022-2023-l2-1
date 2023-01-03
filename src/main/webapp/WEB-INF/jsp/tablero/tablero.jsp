<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<style>
  #cabecera_tablero {
    padding: 10px;
    width: 99%;
    margin: auto;
    height: 50px;
    background-color: rgb(255, 208, 122);
    margin-bottom: 10px;
  }

  #img_bandera,
  #img_mina {
    width: 30px;
    height: 30px;
  }

  #num_bandera,
  #num_mina {
    font-size: 25px;
    color: rgb(8, 3, 83);
  }

  #img_bandera {
    float: left;
  }

  #num_bandera {
    float: left;
    margin-left: 10px;
  }

  #img_mina {
    float: right;
  }

  #num_mina {
    float: right;
    margin-right: 10px;
  }

  #tablero {
    border: 2px solid black;
    max-width: fit-content;
    padding-left: 10px;
    padding-bottom: 10px;
    padding-right: 10px;
    margin: auto;
    background-color: rgb(255, 208, 122);
  }
  #fila {
    display: flex;
  }
  #tablero div div {
    border: 2px solid black;
    width: 40px;
    height: 40px;
    margin: 2px;
    background-color: rgb(130, 130, 228);
    font-size: 20px;
    text-align: center;
    line-height: 45px;
  }
  h1 {
    text-align: center;
  }

  /*  */

  .icon-bandera:before {
    content: "\2691";
    color: rgb(187, 0, 0);
    font-size: 30px;
  }

  .icon-mina:before {
    content: "\2699";
    color: black;
    font-size: 30px;
  }

  .bandera-erronea:before {
    content: "\2612";
    color: red;
    font-size: 40px;
  }
  .bandera-correcta:before {
    content: "\2611";
    color: green;
    font-size: 40px;
  }

  #tablero div div.destapada {
    border-style: none;
  }

  #tablero div div.c1 {
    color: aqua;
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
</style>

<head>
  <c:url value="/resources/images/bandera.png" var="img_bandera" />
  <c:url value="/resources/images/minesweeper.png" var="img_mina" />
  <c:url value="/js/algTablero.js" var="algTablero" />
  <c:url value="/css/style.css" var="estilo" />
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
      <img id="img_bandera" src="${img_bandera}" />
      <span id="num_bandera"></span>
      <img id="img_mina" src="${img_mina}" />
      <span id="num_mina"></span>
    </div>
  </div>

  <script type="text/javascript" src="${algTablero}"></script>
</petclinic:layout>
