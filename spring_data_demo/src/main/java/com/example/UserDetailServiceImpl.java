package com.example;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.entity.JPUser;
import com.example.repository.JPUserRepository;

@Configuration
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	JPUserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		JPUser user;
		try {
			user = userRepository.findByUsernameIgnoreCase(userId);
			if (user == null)
				throw new UsernameNotFoundException("user name not found");

		} catch (Exception e) {
			throw new UsernameNotFoundException("database error ");
		}
		return buildUserFromUserEntity(user);
	}

	@SuppressWarnings("deprecation")
	private UserDetails buildUserFromUserEntity(JPUser userEntity) {
		// convert model user to spring security user
		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		GrantedAuthorityImpl[] authorities = new GrantedAuthorityImpl[1];
		authorities[0] = new GrantedAuthorityImpl(userEntity.getRole());

		User springUser = new User(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, Arrays.asList(authorities));
		return springUser;
	}
}