/**
 * 
 */
package com.zek.doorstep.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author EK
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@ToString
@Entity
@Table(name="company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long companyId;
	
	@NotEmpty(message = "Name cannot be blank")
	private String companyName;
	
	@OneToMany(mappedBy = "company")
//	@JsonManagedReference
	@JsonBackReference
	private Set<Users> users;
	
	private String mobile;
	private Boolean isActive;
	private Long createdBy;
	private Long updatedBy;
	private Long createdOn;
	private Long updatedOn;

	
}
