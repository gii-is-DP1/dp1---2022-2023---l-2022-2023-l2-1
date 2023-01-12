package org.springframework.samples.petclinic.partidas;

import java.time.LocalTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.registeredUser.RegisteredUser;

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

    @JoinColumn(name = "id_invitado")
    private Integer idInvitado;

    @Column(name = "privada")
    private Boolean privada;

    @Column(name = "contrasenia")
    private String contrasenia;
    
    //True si gana el jugador que creo la sala y false si pierde el jugador que creo o gana el jugador invitado
    @Column(name = "resultado")
    private Boolean resultado;

    @Column(name = "minas_de_jugador")
    private Integer minasDeJugador;
}
