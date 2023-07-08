//package com.kiki;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//
//@Component
//public class JWTFilter extends GenericFilterBean {
//	private JWTProvider provider;
//	
//	public JWTFilter(JWTProvider provider) {
//		this.provider = provider;
//	}
//	
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		String token = ((HttpServletRequest) request).getHeader(provider.httpKey);
//		// 유효한 토큰인지 확인
//		if (token != null && provider.checkToken(token)) {
//			// 유효하면, 유저정보 받아옴
//			Authentication authentication = provider.checkPermission(token);
//			// SecurityContext에 Authentication 객체 저장
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			System.out.println(authentication);
//		}
//		chain.doFilter(request, response);
//	}
//}
