<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="logros">
    <h2>Lista de logros</h2>

    <table id="Logros" class="table table-striped">
        <thead>
        <tr>
         <th>Id</th>
         <th>Título</th>
         <th>Descripcion</th>
         <th>Condicion</th>
         <th>Editar Logro</th>
         <th>Eliminar Logro</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros}" var="logro">
            <tr>
                <td>
                    <c:out value="${logro.id}"/>
                </td>
                <td>
                    <c:out value=" ${logro.titulo}"/>
                </td>
                <td>
                    <c:out value=" ${logro.descripcion}"/>
                </td>
                <td>
                    <c:out value="${logro.condicion}"/>
                </td>
                <td>
                    <div>
                        <a href="logros/${logro.id}/edit"><button class="btn btn-sm btn-primary"> Editar Logro </button></a>
                    </div>
                </td>
                <td>
                    <div>
                        <a href="logros/${logro.id}/delete"><button class="btn btn-sm btn-primary"> Eliminar Logro </button></a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="form-group">
        <div>
            <a href="logros/new"><button class="btn btn-link btn-lg btn-block" style="background-color: black; color: aliceblue;"> Añadir Logro </button></a>
        </div>
    </div>

</petclinic:layout>
