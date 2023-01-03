const tablero = {
  numMinasTotales: document.getElementById("numMinas").value,
  numFilas: document.getElementById("numFilas").value,
  numColumnas: document.getElementById("numColumnas").value,
  numMinasEncontradas: 0,
  numBanderas: 0,
  campoMinas: [],
};

window.onload = buscaminas();

function buscaminas() {
  pintaCabecera();
  pintaTablero();
  creaCampoMinasVacio();
  esparcirMinas();
  contarMinas();
}

// --------------------------------------------------------

function pintaCabecera() {
  num_bandera.innerHTML = tablero.numBanderas;
  num_mina.innerHTML = tablero.numMinasTotales - tablero.numMinasEncontradas;
}

function pintaTablero() {
  let divTablero = document.getElementById("tablero");
  for (let f = 0; f < tablero.numFilas; f++) {
    // Filas
    let divFila = document.createElement("div");
    divFila.setAttribute("id", "fila");
    divTablero.appendChild(divFila);
    for (let c = 0; c < tablero.numColumnas; c++) {
      // Casillas
      let divCasilla = document.createElement("div");
      divCasilla.setAttribute("id", "f" + f + "_c" + c);
      divCasilla.dataset.fila = f;
      divCasilla.dataset.columna = c;
      //Eventos de casilla
      divCasilla.addEventListener("contextmenu", accionMarcar);
      divCasilla.addEventListener("click", accionDestapar);
      divFila.appendChild(divCasilla);
    }
  }
}

// --------------------------------------------------------

function accionMarcar(evento) {
  if (evento.type === "contextmenu") {
    console.log(evento);

    let casilla = evento.currentTarget;
    evento.stopPropagation();
    evento.preventDefault();

    let fila = parseInt(casilla.dataset.fila);
    let columna = parseInt(casilla.dataset.columna);
    if (
      fila >= 0 &&
      columna >= 0 &&
      fila < tablero.numFilas &&
      columna < tablero.numColumnas
    ) {
      if (casilla.classList.contains("icon-bandera")) {
        casilla.classList.remove("icon-bandera");
        tablero.numBanderas--;
      } else if (casilla.classList.length == 0) {
        casilla.classList.add("icon-bandera");
        tablero.numBanderas++;
      }
    }
  }
  pintaCabecera();
}

function accionDestapar(evento) {
  if (evento.type === "click") {
    let casilla = evento.currentTarget;
    let fila = parseInt(casilla.dataset.fila, 10);
    let columna = parseInt(casilla.dataset.columna, 10);
    destaparCasilla(fila, columna);
  }
  pintaCabecera();
}

function destaparCasilla(fila, columna) {
  console.log(
    "Detapamos casilla con Fila: " + fila + ", y Columna: " + columna
  );
  if (
    fila > -1 &&
    fila < tablero.numFilas &&
    columna > -1 &&
    columna < tablero.numColumnas
  ) {
    let casilla = document.querySelector("#f" + fila + "_c" + columna);
    if (!casilla.classList.contains("destapada")) {
      if (!casilla.classList.contains("icon-bandera")) {
        casilla.classList.add("destapada");
        casilla.innerHTML = tablero.campoMinas[fila][columna];
        casilla.classList.add("c" + tablero.campoMinas[fila][columna]);
        if (tablero.campoMinas[fila][columna] != "mina") {
          if (tablero.campoMinas[fila][columna] == 0) {
            destaparCasilla(fila - 1, columna - 1);
            destaparCasilla(fila - 1, columna);
            destaparCasilla(fila - 1, columna + 1);
            destaparCasilla(fila, columna - 1);
            destaparCasilla(fila, columna + 1);
            destaparCasilla(fila + 1, columna - 1);
            destaparCasilla(fila + 1, columna);
            destaparCasilla(fila + 1, columna + 1);
            casilla.innerHTML = "";
          }
          finDePartida("");
        } else if (tablero.campoMinas[fila][columna] == "mina") {
          casilla.innerHTML = "";
          casilla.classList.add("icon-mina");
          casilla.classList.add("sinmarcar");
          finDePartida("error");
        }
      }
    }
  }
}

// --------------------------------------------------------

function creaCampoMinasVacio() {
  tablero.campoMinas = new Array(tablero.numFilas);
  for (let fila = 0; fila < tablero.numFilas; fila++) {
    tablero.campoMinas[fila] = new Array(tablero.numColumnas);
  }
}

function esparcirMinas() {
  let numMinasEsparcidas = 0;
  while (numMinasEsparcidas < tablero.numMinasTotales) {
    let fila = Math.floor(Math.random() * tablero.numFilas);
    let columna = Math.floor(Math.random() * tablero.numColumnas);
    if (tablero.campoMinas[fila][columna] != "mina") {
      tablero.campoMinas[fila][columna] = "mina";
      numMinasEsparcidas++;
    }
  }
}

// --------------------------------------------------------

function contarMinasAlrededorCasilla(fila, columna) {
  let numMinas = 0;
  for (let zFila = fila - 1; zFila <= fila + 1; zFila++) {
    for (let zColumna = columna - 1; zColumna <= columna + 1; zColumna++) {
      if (
        zFila > -1 &&
        zFila < tablero.numFilas &&
        zColumna > -1 &&
        zColumna < tablero.numColumnas
      ) {
        if (tablero.campoMinas[zFila][zColumna] == "mina") {
          numMinas++;
        }
      }
    }
  }
  tablero.campoMinas[fila][columna] = numMinas;
}

function contarMinas() {
  for (let fila = 0; fila < tablero.numFilas; fila++) {
    for (columna = 0; columna < tablero.numColumnas; columna++) {
      if (tablero.campoMinas[fila][columna] != "mina") {
        contarMinasAlrededorCasilla(fila, columna);
      }
    }
  }
}

// --------------------------------------------------------

function listaCasillas() {
  let casillas = [];
  let filas = document.getElementById("tablero").children;
  for (let zfila = 1; zfila < filas.length; zfila++) {
    for (
      let zcasilla = 0;
      zcasilla < filas[zfila].children.length;
      zcasilla++
    ) {
      casillas.push(filas[zfila].children[zcasilla]);
    }
  }
  return casillas;
}

function todasDestapadas() {
  let res = true;
  let casillas = listaCasillas();
  for (let i = 0; i < casillas.length; i++) {
    let casilla = casillas[i];
    if (
      !casilla.classList.contains("destapada") &&
      !casilla.classList.contains("icon-bandera")
    ) {
      res = false;
    }
  }
  return res;
}

function finDePartida(msg) {
  let s = "Fin de partida: ";
  if (msg == "error") {
    resolverTablero();
    alert(s + "Mala suerte!!!");
  } else {
    if (todasDestapadas()) {
      let estado = resolverTablero();
      if (estado == "banderas_erroneas") {
        alert(s + "Hay banderas incorrectas");
      } else if (estado == "minas_sin_destapar") {
        alert(s + "Hay minas sin destapar");
      } else if (estado == "todas_banderas_correctas") {
        alert(s + "Enhorabuena!!!");
      }
    }
  }
}

function resolverTablero() {
  let casillas = listaCasillas();
  let bandera_erronea = 0;
  let bandera_correcta = 0;
  let mina_sin_destapar = 0;
  for (let i = 0; i < casillas.length; i++) {
    casillas[i].removeEventListener("click", accionDestapar);
    casillas[i].removeEventListener("contextmenu", accionMarcar);

    let fila = parseInt(casillas[i].dataset.fila, 10);
    let columna = parseInt(casillas[i].dataset.columna, 10);

    if (casillas[i].classList.contains("icon-bandera")) {
      casillas[i].classList.remove("icon-bandera");
      casillas[i].classList.add("destapada");
      if (tablero.campoMinas[fila][columna] == "mina") {
        casillas[i].classList.add("bandera-correcta");
        bandera_correcta++;
      } else {
        casillas[i].classList.add("bandera-erronea");
        bandera_erronea++;
      }
    } else if (!casillas[i].classList.contains("destapada")) {
      casillas[i].classList.add("destapada");
      if (tablero.campoMinas[fila][columna] == "mina") {
        casillas[i].classList.add("icon-mina");
        mina_sin_destapar++;
      }
    }
  }
  if (bandera_erronea > 0) {
    return "banderas_erroneas";
  } else if (mina_sin_destapar > 0) {
    return "minas_sin_destapar";
  } else if (bandera_correcta == tablero.numMinasTotales) {
    return "todas_banderas_correctas";
  }
}
