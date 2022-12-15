function pintarTablero(numFilas, numColumnas) {
  var tablero = document.getElementById("tablero");
  for (var f = 0; f < numFilas; f++) {
    // Filas
    var divFila = document.createElement("div");
    divFila.setAttribute("id", "fila");
    tablero.appendChild(divFila);
    for (var c = 0; c < numColumnas; c++) {
      // Casillas
      var divCasilla = document.createElement("div");
      divCasilla.setAttribute("id", "casilla");
      divCasilla.setAttribute("fila", f);
      divCasilla.setAttribute("columna", c);
      //Eventos de casilla
      divCasilla.addEventListener("contextmenu", marcar);
      divCasilla.addEventListener("click", destapar);
      divFila.appendChild(divCasilla);
    }
  }
}

function marcar(evento) {
  if (evento.type == "contextmenu") {
    console.log(evento);
  }
}

function destapar() {
  alert("casilla pulsada");
}
