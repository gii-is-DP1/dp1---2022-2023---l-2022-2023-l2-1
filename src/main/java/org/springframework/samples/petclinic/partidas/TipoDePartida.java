package org.springframework.samples.petclinic.partidas;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;


/*
INSERT INTO tipos VALUES (1, 'Individual');
INSERT INTO tipos VALUES (2, 'Competitivo');
*/
@Getter

@Entity
@Table(name= "tipos")
public class TipoDePartida extends NamedEntity{

}