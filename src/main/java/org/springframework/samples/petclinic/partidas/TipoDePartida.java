package org.springframework.samples.petclinic.partidas;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;



@Entity
@Table(name= "tipo_de_partida")
public class TipoDePartida extends NamedEntity{

}