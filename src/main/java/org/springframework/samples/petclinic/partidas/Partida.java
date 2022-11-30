package org.springframework.samples.petclinic.partidas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "partida")
public class Partida extends BaseEntity {

    @NotNull
    @JoinColumn(name = "registered_user_id")
    private Integer registeredUserId; 

    
    @Column(name = "tiempo_de_juego")
    /*Si tiempo_de_juego == null; la partida sigue en curso.
    Cuando finalice:
    Hora final - Hora inicio */
    private Integer tiempoDeJuego;

    @OneToOne
    @JoinColumn(name = "dificultad_id")
    private Dificultad dificultad; 

    @OneToOne
    @JoinColumn(name = "tipo_de_partida_id")
    private TipoDePartida tipo;

    @JoinColumn(name = "id_invitado", nullable = true)
    private String idInvitado;

    @Column(name = "privada")
    private Boolean privada;

    @Column(name = "contrasenia", nullable = true)
    private String contrasenia;
}
