package org.springframework.samples.petclinic.partidas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "partida")
public class Partida extends NamedEntity {
    @NotNull
    @JoinColumn(name = "user_id")
    private Integer userId; 

    @NotNull
    @Column(name = "tiempo_de_juego")
    /*Hora final - Hora inicio */
    private Integer tiempoDeJuego;

    @OneToOne
    @NotNull
    @JoinColumn(name = "dificultad_id")
    private Dificultad dificultad; 

    @NotNull
    @JoinColumn(name = "tipo_partida_id")
    private Integer tipo;

    @JoinColumn(name = "id_invitado", nullable = true)
    private Integer idInvitado;

    @Column(name = "privada")
    @NotNull
    private Boolean privada;

    @Column(name = "contrasenia")
    private String contrasenia;
}
