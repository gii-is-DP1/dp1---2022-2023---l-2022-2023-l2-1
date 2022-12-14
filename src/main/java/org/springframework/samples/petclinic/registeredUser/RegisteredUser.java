package org.springframework.samples.petclinic.registeredUser;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Audited
@Table(name = "registered_users")
public class RegisteredUser extends BaseEntity implements Comparable<RegisteredUser> {

	@Column(name = "name")
	String name;

    @Column(name = "description")
    //@Size(min = 0,max = 250)
    String description;
    
    @Column(name = "email")
	@NotNull
	@Email
    String email;
	
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

    @Override
	public String toString() {
		return new ToStringCreator(this)
				.append("id", this.getId())
				.append("new", this.isNew())
				.append("username", this.getUser())
				.append("name", this.getName())
				.append("description", this.getDescription())
				.append("email", this.getEmail())
				.toString();
	}

    @Override
	public int compareTo(RegisteredUser objectRegisteredUser) {
		return this.getUser().getUsername().compareTo(objectRegisteredUser.getUser().getUsername());
	}

    

    
}
