package org.springframework.samples.petclinic.userData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "userdata")
public class UserData extends NamedEntity{

    @Column(name = "description")
    String description;


    boolean isAdmin;

    @Column(name = "email")
	@NotNull
	@Email
    String email;
    
}
