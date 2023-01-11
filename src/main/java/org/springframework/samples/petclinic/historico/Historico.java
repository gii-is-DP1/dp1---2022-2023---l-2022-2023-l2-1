package org.springframework.samples.petclinic.historico;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "historicos")
public class Historico extends BaseEntity {

    @NotNull
    @Column(name = "partidas_totales")
    private Integer partidasTotales;

    @NotNull
    @Column(name = "partidas_ganadas")
    private Integer partidasGanadas;

    @NotNull
    @Column(name = "minas_encontradas")
    private Integer minasEncontradas;

    @NotNull
    @Column(name = "puntuacion")
    private Integer puntuacion;

    @NotNull
    @Column(name = "tiempo_total_juego")
    @DateTimeFormat(pattern = "hh:mm:ss", iso = ISO.TIME)
    private LocalTime tiempoTotalJuego;

    @NotNull
    @Column(name = "tiempo_medio_partida")
    @DateTimeFormat(pattern = "hh:mm:ss", iso = ISO.TIME)
    private LocalTime tiempoMedioPartida;

    @NotNull
    @JoinColumn(name = "registered_user_id")
    private Integer registeredUserId;

}
