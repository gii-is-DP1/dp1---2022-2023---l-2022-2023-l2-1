package org.springframework.samples.petclinic.historico;


import java.util.Set;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.samples.petclinic.logro.Logro;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "historicos")
public class Historico extends BaseEntity implements Comparable{

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

    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "logros_id",
        joinColumns = @JoinColumn(name = "historico_id"), 
        inverseJoinColumns = @JoinColumn(name = "logro_id")
    )
    private Set<Logro> logros;

    @JoinColumn(name = "registered_user_id")
    private Integer registeredUserId;

    
}
