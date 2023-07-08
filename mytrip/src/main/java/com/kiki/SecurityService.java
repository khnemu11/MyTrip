//package com.kiki;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SecurityService implements UserDetailsService {
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		SecurityUser user = new SecurityUser(); // 여기에 db 다녀온 결과가 필요. 유저클래스는 dto객체
//		UserDetails build = null;
//		
//		try {
//			User.UserBuilder userBuilder = User.withUsername(username).password(user.getPassword());
//			userBuilder.authorities(user.getAuthorities());
//			build = userBuilder.build();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return build;
//	}
//	
//}
