package com.zek.doorstep.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="authorities")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "authority")
@ToString(of = "authority")
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String authority;
	
	@ManyToMany
	@JoinTable(
			name = "role_authority",
			joinColumns = @JoinColumn(name="authority_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	@JsonIgnore
	private Set<Role> roles;
	
	public Set<Role> getRoles() {
		if(this.roles == null) {
			this.roles = new HashSet<>();
		}
		return this.roles;
	}

}
