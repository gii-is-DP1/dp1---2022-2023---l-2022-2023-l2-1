<%@ page session="false" trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
          <!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

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