<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="registered_users">

    <h2>
        <th><c:out value="${registeredUser.user.username}"/></th>
        <a href="/registeredUser/myProfile/edit"> 
            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
        </a>
    </h2>

    <table class="table table-striped">

        <tr>
            <th>Name</th>
            <td><c:out value="${registeredUser.name}"/></td>
        </tr>
        
        <tr>
            <th>Description</th>
            <td><c:out value="${registeredUser.description}"/></td>
        </tr>

    </table>


</petclinic:layout>