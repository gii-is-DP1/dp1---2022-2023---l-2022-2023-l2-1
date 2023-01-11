
package org.springframework.samples.petclinic.logro;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="condicion")
public class Condicion extends NamedEntity{
    
    @NotNull
    @Column(name = "predicado") // atributo
    String predicado;

    @NotNull
    @Column(name = "comparador") // mayor o igual o menor
    String comparador;

}
