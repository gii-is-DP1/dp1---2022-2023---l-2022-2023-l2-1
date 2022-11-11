package org.springframework.samples.petclinic.tablero;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tablero")
public class Tablero extends BaseEntity{

    @Column(name = "columnas")
    @NotEmpty
    private Integer columnas;

    @Column(name = "filas")
    @NotEmpty
    private Integer filas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="tablero")
    private List<Casilla> casillas;

    
}
