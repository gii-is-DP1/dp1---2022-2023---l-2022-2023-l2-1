<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->
<style>

  #cabecera_tablero{
    padding: 10px;
    width: 99%;
    margin: auto;
    height: 50px;
    background-color: rgb(255, 208, 122);
    margin-bottom: 10px;
  }

  #img_bandera, #img_mina{
    width: 30px;
    height: 30px;
  }

  #num_bandera, #num_mina{
    font-size: 25px;
    color: rgb(8, 3, 83);
  }

  #img_bandera{
    float: left;
  }

  #num_bandera{
    float: left;
    margin-left: 10px;
  }

  #img_mina{
    float: right;
  }

  #num_mina{
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
  #casilla {
    border: 1px solid black;
    width: 40px;
    height: 40px;
    margin: 2px;
    background-color: rgb(130, 130, 228);
  }
  h1{
    text-align: center;
  }

</style>

<petclinic:layout pageName="tablero">
  <c:url value="/resources/images/bandera.png" var="img_bandera"/>
  <c:url value="/resources/images/minesweeper.png" var="img_mina"/>
  <c:url value="/js/algTablero.js" var="algTablero"/>
  
  <!-- Datos del tablero para el js -->
  <input type="hidden" id="numFilas" value="${tablero.filas}"/>
  <input type="hidden" id="numColumnas" value="${tablero.columnas}"/>
  <!--  -->

  <h1>Tablero JS</h1>
  <div id="tablero">
    <div id="cabecera_tablero">
    <img id="img_bandera" src="${img_bandera}"/>
    <span id="num_bandera"></span>
    <img id="img_mina" src="${img_mina}"/>
    <span id="num_mina"></span>
</div>
</div>

<br>
<br>

<h1>Tablero inicial</h1>
    <div
      class="tablero"
      style="
        border: 2px solid black;
        margin: auto;
        width: max-content;
        padding: 10px;
      "
    >
      <c:forEach begin="1" step="1" end="${tablero.filas}">
        <div class="fila" style="display: flex; margin: 1px">
          <c:forEach begin="1" step="1" end="${tablero.columnas}">
            <div
              class="casilla"
              style="
                border: 1px solid black;
                width: 40px;
                height: 40px;
                margin: 2px;
                background-color: rgb(130, 130, 228);
              "
            ></div>
          </c:forEach>
        </div>
      </c:forEach>
    </div>

<script type="text/javascript" src="${algTablero}"></script>
</petclinic:layout>
