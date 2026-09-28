package com.apexmotion.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apexmotion.entity.User;
import com.apexmotion.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User khong ton tai: " +email));
		// Chuyển User entity → UserDetails (object mà Spring Security hiểu)
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPasswordHash(),
				Collections.singletonList(new SimpleGrantedAuthority("Role" + user.getRole().name()))
				);
	}
	public User loadUserById (long id) {
		return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User khong ton tai: " + id));
	}
	
}
