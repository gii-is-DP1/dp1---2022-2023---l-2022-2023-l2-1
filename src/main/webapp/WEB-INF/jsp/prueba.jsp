<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

const buscaminas{
    numMinasTotales: 30,
    numMinasEncontradas: 0,
    numFilas: 15,
    numColumnas: 15,
    aCampoMinas: []
}

function pintarTablero(numFilas, numColumnas){
    let tablero = document.querySelector("#tablero");
 
    document.querySelector("html").style.setProperty("--num-filas",numFilas);
    document.querySelector("html").style.setProperty("--num-columnas",numColumnas);
 
    for(let f=0; f<numFilas; f++){
        for(let c=0; c<numColumnas; c++){
            let newDiv = document.createElement("div");
            tablero.appendChild(newDiv);
        }
    }
}

<petclinic:layout pageName="Pruebas">


    <spring:url value="/resources/images/pets.png" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
