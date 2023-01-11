package org.springframework.samples.petclinic.logro;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.historico.Historico;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "logros")
public class Logro extends BaseEntity {

    @NotNull
    @Column(name = "titulo")
    private String titulo;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "condicion")
    @NotNull
    private Condicion condicion;

    @NotNull
    @Column(name = "valor")    // comparador
    Integer valor;

    @ManyToMany(mappedBy = "logros")
    private Set<Historico> historicos;

}
