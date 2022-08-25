package com.zek.doorstep.repo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		
		return this.userRepository.findByEmail(emailAddress)
								 					.orElseThrow(() -> new UsernameNotFoundException("bad credentials"));
	}

}
