<%@ page session="false" trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
          <!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<<<<<<< Updated upstream
          <petclinic:layout pageName="tablero">
            <h2>
              <fmt:message key="welcome" />
            </h2>
            <div class="row">
              <h2>Project ${title}</h2>
              <p>
              <h2>Group ${group}</h2>
              </p>
              <div style="border: 2px solid black; margin: auto; width: max-content; padding: 10px;">
                <c:forEach begin="1" step="1" end="${fil}">
                  <div style="display: flex; margin: 2px;">

                    <c:forEach begin="1" step="1" end="${col}">

                      <div id="casilla"  style=" border:1px solid black ; width: 30px; height: 30px;  margin: 2px;"></div>

                    </c:forEach>

                  </div>
                </c:forEach>

              </div>
              <div class="col-md-12;" style="margin-top: 50px; margin-left: 30% ;">
                <spring:url value="/resources/images/minesweeper.png" htmlEscape="true" var="image" />
                <img class="img-responsive" src="${image}" />
              </div>
            </div>
          </petclinic:layout>
=======
<petclinic:layout pageName="tablero">
  <h2><fmt:message key="welcome" /></h2>
  
    <h2>Project ${title}</h2>
    <p><h2>Group ${group}</h2></p>
      <ul>
        <c:forEach items="${persons}" var="person">
          <li>${person.firstName}, ${person.lastName}</li>
        </c:forEach>
      </ul>

      <div id="tablero" style="border: 2px solid black; margin: auto; width: fit-content;">
      <c:forEach begin="1" step="1" end="${filas}">
        <div id="fila" style="display: grid;">
        <c:forEach begin="1" step="1" end="${columnas}">
          <div
            id="casilla"
            style="
              border: 1px solid black;
              background-color: brown;
              width: 30px;
              height: 30px;
              margin: 5px;
            "
          </div>
          </c:forEach>
        </div>
      </c:forEach>
    </div>
   
    <div class="col-md-12">
      <spring:url
        value="/resources/images/minesweeper.png"
        htmlEscape="true"
        var="image"
      />
      <img class="img-responsive" src="${image}" />
    </div>
  </div>
</petclinic:layout>
>>>>>>> Stashed changes
