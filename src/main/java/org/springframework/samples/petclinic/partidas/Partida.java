package org.springframework.samples.petclinic.partidas;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "partida")
public class Partida extends BaseEntity {


    @JoinColumn(name = "registered_user_id")
    private Integer registeredUserId; 

    
    @Column(name = "tiempo_de_juego")
    private LocalTime tiempoDeJuego;

    @Column(name = "tiempo_de_juego_invitado")
    private LocalTime tiempoDeJuegoInvitado;

    @OneToOne
    @JoinColumn(name = "dificultad_id")
    private Dificultad dificultad; 

    @OneToOne
    @JoinColumn(name = "tipo_de_partida_id")
    private TipoDePartida tipo;

    @JoinColumn(name = "id_invitado", nullable = true)
    private Integer idInvitado;

    @Column(name = "privada")
    private Boolean privada;

    @Column(name = "contrasenia", nullable = true)
    private String contrasenia;
    
    //True si gana el jugador que creo la sala y false si pierde el jugador que creo o gana el jugador invitado
    @Column(name = "resultado", nullable = true)
    private Boolean resultado;
}
