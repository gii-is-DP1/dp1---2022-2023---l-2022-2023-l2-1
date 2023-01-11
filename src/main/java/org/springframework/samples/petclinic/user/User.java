package org.springframework.samples.petclinic.user;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	
@AllArgsConstructor
@NoArgsConstructor	
@Getter
@Setter
@Audited
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User{
	@Id
	String username;
	
	String password;

	boolean enabled;

	@CreatedBy            
	private String creator; 
	@CreatedDate         
	private LocalDateTime createdDate; 
	@LastModifiedBy 	    
	private String modifier;
	@LastModifiedDate 
	private LocalDateTime lastModifiedDate; 
	
	
	@NotAudited
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
}
