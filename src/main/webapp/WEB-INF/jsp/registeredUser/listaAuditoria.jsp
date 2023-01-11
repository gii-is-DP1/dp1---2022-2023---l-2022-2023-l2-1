<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %> <%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="listaAuditoria">
    <script type="text/javascript">
        function checkCreat(dateModified, dateCreated) {
           
            if (dateCreated==false){
              return false;
            }else if(dateCreated.until(dateModified, ChronoUnit.SECONDS)<2){
                return true;
            }else{
                return false;
            }
          }                    
        </script>
  <h2>Cambios recientes</h2>

    <table id="tabla auditora" class="table table-striped">
        <thead>
            <tr>
                <th>Operacion</th>
                <th>Fecha de la operación</th>
                <th>Modificador</th>                
                <th>Fecha de creación</th>
                <th>Usuario creador</th>
            </tr>
        </thead>
        <tbody>
        
        <c:choose> 
            <c:when test="${registeredUsers!=null}">
                <c:forEach items="${registeredUsers}" var="registeredUser">
                <tr>
                <td>
                <c:choose>
                    <c:when test="checkCreat('${registeredUser.user.lastModifiedDate}','${registeredUser.user.createdDate}');">
                    Creación

                </c:when>
                <c:otherwise>
                    Modificación
                </c:otherwise>
                       </c:choose>
                </td>
                <td>
                  
                    <c:out value="${registeredUser.user.lastModifiedDate}"/>
                    
                </td>
                <td>
                    
                    <c:out value="${registeredUser.user.modifier}"/>
                    
                </td>
                <td>
                    <c:if test="${registeredUser.user.createdDate==null}"><c:out value="Sin Datos"/></c:if>
                    <c:if test="${registeredUser.user.createdDate!=null}"><c:out value="${registeredUser.user.createdDate}"/></c:if>
                    
                </td>
                <td>
                    <c:if test="${registeredUser.user.createdDate==null}"><c:out value="Sin Datos"/></c:if>
                    <c:if test="${registeredUser.user.createdDate!=null}"><c:out value="${registeredUser.user.creator}"/></c:if>
                </td>
                </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
            <tr>
                <td>
                    <c:out value="Sin Datos"/>
                </td>
                <td>
                    <c:out value="Sin Datos"/>
                </td>
                <td>
                    <c:out value="Sin Datos"/>

                </td>
                <td>
                    <c:out value="Sin Datos"/>

                </td>
                <td>
                    <c:out value="Sin Datos"/>

                </td>
            </c:otherwise>
        </c:choose>
        
        </tbody>
      </table>
</petclinic:layout>
