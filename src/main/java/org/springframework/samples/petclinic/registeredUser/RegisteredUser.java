package org.springframework.samples.petclinic.registeredUser;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registered_users")
public class RegisteredUser extends NamedEntity {

    @Column(name = "description")
    String description;


    // boolean isAdmin;

    @Column(name = "email")
	@NotNull
	@Email
    String email;

    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

    
}
