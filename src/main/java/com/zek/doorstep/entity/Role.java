package com.zek.doorstep.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.ObjDoubleConsumer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "roleName")
@ToString(of = "roleName")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String roleName;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Authority> authorities;
	
	@OneToMany(mappedBy = "role")
//	@JsonManagedReference
	@JsonBackReference
	private Set<Users> users;
	
	public void addAuthority(Authority authority) {
		if(Objects.isNull(authorities)) {
			this.authorities = new HashSet<>();
		}
		this.authorities.add(authority);
		authority.getRoles().add(this);
	}

}