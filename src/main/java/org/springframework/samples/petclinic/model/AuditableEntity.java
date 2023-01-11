package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.ModifiedEntityNames;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.LastModifiedBy;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity{
	@CreatedBy            
	private String creator; 
	@CreatedDate         
	private LocalDateTime createdDate; 
	@LastModifiedBy 	    
	private String modifier;
	@LastModifiedDate 
	private LocalDateTime lastModifiedDate; 
	@ModifiedEntityNames
	private String entityName;

}