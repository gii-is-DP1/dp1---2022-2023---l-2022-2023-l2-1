<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="registered_users">
    <h2>Usuarios Registrados</h2>

    <table id="registeredUsersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Usuario</th>
            <th style="width: 150px;">Email</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${data}" var="registeredUser">
            <tr>
                <td>
                    <c:out value="${registeredUser.name}"/>
                </td>

                <td>
                    <c:out value="${registeredUser.user.username}"/>
                </td>

                <td>
                    <c:out value="${registeredUser.email}"/>
                </td>

                <td>
                    
                </td>

                <td> 
                    <a href="/registeredUser/${registeredUser.id}">Ver perfil
                           
                    </a>
                    <a href="/registeredUser/${registeredUser.id}/edit"> 
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                    </a>       

                    <a href="/registeredUser/${registeredUser.id}/delete"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a> 
                </td>
 
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
  
    <c:if test="${totalPages > 1}">
            <ul class="pagination">
                <c:forEach begin="0" end="${totalPages-1}" var="page">
                    <li class="page-item">
                        <c:url value = "/registeredUser/userPages/${page}" var = "pagiURL">
                        <c:param name = "search" value = "${search}"/>
                        </c:url>
                        <a href="${pagiURL}" class="page-link">${page}</a>
                    </li>
                </c:forEach>
            </ul>
    </c:if>

</petclinic:layout>
