package org.springframework.samples.petclinic.tablero;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partidas.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tablero")
public class Tablero extends BaseEntity {

    @Column(name = "columnas")
    @NotEmpty
    private Integer columnas;

    @Column(name = "filas")
    @NotEmpty
    private Integer filas;

    @Column(name = "num_minas")
    @NotEmpty
    private Integer minas;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partidaId;
}
