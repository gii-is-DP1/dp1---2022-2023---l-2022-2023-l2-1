package org.springframework.samples.petclinic.tablero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partidas.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tablero")
public class Tablero extends BaseEntity {

    @Column(name = "columnas")
    @NotNull
    private Integer columnas;

    @Column(name = "filas")
    @NotNull
    private Integer filas;

    @Column(name = "num_minas")
    @NotNull
    private Integer minas;

    @ManyToOne
    @JoinColumn(name = "partida")
    private Partida partida;
}
