/**
 * 
 */
package com.zek.doorstep.entity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EK
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id"/* , columnDefinition = "serial" */)
	private long userId;
	@NotEmpty(message = "Name cannot be blank")
	private String userName;
	private String mobile;
//	@NotBlank(message = "FirstName should not be blank")
//	@Size(min = 3, message = "FirstName should be at least 3 chars")
//	private String first_name;
//
//	@NotBlank(message = "LastName should not be blank")
//	@Size(min = 3, message = "LastName should be at least 3 chars")
//	private String last_name;

	@Enumerated(EnumType.STRING)
	private com.zek.doorstep.util.enums.Gender gender;

	@NotBlank
	@Email(message = "Email address is not valid")
	private String email;
//	@Transient
//	@JsonIgnore
	private String password;
	private Boolean isActive;
	private String otp;
	private Long otpUpdatedTime;
	private Boolean userAuthenticatedFlag;
	private Boolean isLoggedIn;
	
	@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
	private String ipAddress;
	private Long createdBy;
	private Long updatedBy;
	private Long createdOn;
	private Long updatedOn;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
//	@JsonBackReference
	@JsonManagedReference
	private Role role;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
//	@JsonBackReference
	@JsonManagedReference
	private Company company;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = this.getRole().getRoleName();
		Set<GrantedAuthority> authorities = this.getRole().getAuthorities().stream().map(Authority::getAuthority)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

}
