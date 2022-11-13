<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="registered_users">
    <h2>
        <th><c:out value="${registeredUser.user.username}"/></th>
    </h2>

    <form:form modelAttribute="registeredUser" class="form-horizontal" id="add-registeredUser-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="New Username" name="user.username"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Description" name="description"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
    
                <button class="btn btn-default" type="submit">Guardar cambios</button>
                
            </div>
        </div>
    </form:form>
</petclinic:layout>
