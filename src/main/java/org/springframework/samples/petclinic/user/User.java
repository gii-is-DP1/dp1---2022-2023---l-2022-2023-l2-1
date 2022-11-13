package org.springframework.samples.petclinic.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



// @PasswordValueMatch.List({
// 		@PasswordValueMatch(	
// 				field = "password",
// 				fieldMatch = "confirmPassword",
// 				message = "Passwords do not match!"
// 			)
// })
	
@AllArgsConstructor
@NoArgsConstructor	
@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	@Id
	String username;
	
	@ValidPassword
	@NotBlank(message = "New password is mandatory")
	@NotEmpty
	String password;

	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
}
