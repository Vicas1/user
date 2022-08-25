/**
 * 
 */
package com.zek.doorstep.pojos.dtos;

import java.io.Serializable;
import java.util.Set;

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
public class UsersDtos implements Serializable{

	private long userId;
	private long userRoleId;
	private String userName;
	private String userMobileNumber;
	private String userEmail;
//	private String userPassword;
	private Boolean userBlockedFlag;
	private String otp;
	private long otpUpdatedTime;
	private Boolean userAuthenticatedFlag;
	private Boolean loginFlag;
	private long createdByUserId;
	private long updatedByUserId;
	private long createdTime;
	private long updatedTime;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean aredentialsNonExpired;

	private boolean enabled;

	private String password;
	
	private Set<RoleDtos> role;
	
	private Set<AuthorityDtos> authority;
	
	
	
}
