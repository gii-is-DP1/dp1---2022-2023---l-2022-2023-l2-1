package org.springframework.samples.petclinic.tablero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "casilla")
public class Casilla extends BaseEntity{

    @Column(name="coordX")
    public Integer coordX;

    @Column(name="coordY")
    public Integer coordY;

    @Column(name="es_mina")
    public Boolean esMina;

    @Column(name="numero")
    public Integer numero;

    @Column(name="esta_liberado")
    public Boolean estaLiberado;

    @Column(name="tiene_bandera")
    public Boolean tieneBandera;

    
    @ManyToOne
    @JoinColumn(name = "tablero_id")
    private Tablero tablero;



}
