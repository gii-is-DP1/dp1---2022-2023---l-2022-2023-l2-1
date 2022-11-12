package org.springframework.samples.petclinic.partidas;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "dificultad")
public class Dificultad extends NamedEntity{

}