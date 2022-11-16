package org.springframework.samples.petclinic.historico;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partidas.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "historicos")
public class Historico extends BaseEntity {

    @NotNull
    @JoinColumn(name = "user_id")
    private Integer userId;
    @NotNull
    @Column(name = "partidas_totales")
    private Integer partidasTotales;
    @NotNull
    @Column(name = "partidas_ganadas")
    private Integer partidasGanadas;

<<<<<<< HEAD
<<<<<<< HEAD
    // @Column(name = "historial_partidas")
    // private List<Partida> historialPartidas;
=======
    /* HISTORIAL DE PARTIDAS: DA PROBLEMAS EL TIPO List<Partida> */
>>>>>>> davgavser

=======
>>>>>>> davgavser
    @NotNull
    @Column(name = "minas_encontradas")
    private Integer minasEncontradas;

    @NotNull
    @Column(name = "puntuacion")
    private Integer puntuacion;

    @NotNull
    @Column(name = "tiempo_total_juego")
    private Double tiempoTotalJuego;

    @NotNull
    @Column(name = "tiempo_medio_partida")
    private Double tiempoMedioPartida;

    @NotNull
    @Column(name = "tiempo_minimo")
    private Double tiempoMinimo;

    @NotNull
    @Column(name = "tiempo_maximo")
    private Double tiempoMaximo;

}
