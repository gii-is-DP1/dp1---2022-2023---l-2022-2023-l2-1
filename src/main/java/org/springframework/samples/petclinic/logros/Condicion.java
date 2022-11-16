package org.springframework.samples.petclinic.logros;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "condiciones")
public class Condicion extends NamedEntity {

}
