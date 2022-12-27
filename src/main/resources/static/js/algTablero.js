
const tablero = {
  numMinasTotales : 30,
  numMinasEncontradas : 0,
  numBanderas: 0,
  filas: document.getElementById("numFilas").value,
  columnas: document.getElementById("numColumnas").value,
  aCampoMinas:[]
}

buscaminas();

function buscaminas(){
  pintaCabecera();
  pintaTablero();
  creaCampoMinasVacio();
}

function pintaCabecera(){
  num_mina.innerHTML = tablero.numMinasTotales;
  num_bandera.innerHTML = tablero.numBanderas;
}    

function pintaTablero() {
  var divTablero = document.getElementById("tablero");
  for (var f = 0; f < tablero.filas; f++) {
    // Filas
    var divFila = document.createElement("div");
    divFila.setAttribute("id", "fila");
    divTablero.appendChild(divFila);
  for (var c = 0; c < tablero.columnas; c++) {
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

function marcar(evento){
  tablero.numBanderas = tablero.numBanderas + 1;
  pintaCabecera();
}

function destapar(){
  tablero.numMinasTotales = tablero.numMinasTotales - 1;
  tablero.numMinasEncontradas = tablero.numMinasEncontradas + 1;
  pintaCabecera();  
}

function creaCampoMinasVacio(){
  tablero.aCampoMinas = new Array(tablero.filas);
}