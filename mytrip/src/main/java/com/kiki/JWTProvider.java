//package com.kiki;
//
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JWTProvider {
//	
//	public static String httpKey = "Authorization";
//	private String secretKey = "myJWTKey";
//	private long time = 60 * 60 * 1000L; // 토큰 유효시간 60분
//	private final UserDetailsService userDetailsService;
//	
//	public JWTProvider(UserDetailsService userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}
//	
//	// 객체 초기화, secretKey를 Base64로 인코딩
//	@PostConstruct
//	protected void encodeSecretKey() {
//		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//	}
//	
//	// JWT 토큰 생성
//	public String inssuanceToken(String userPk, List<String> roles) {
//		Claims claims = Jwts.claims().setSubject(userPk); // JWT payload에 저장되는 정보 단위
//		claims.put("roles", roles);
//		Date now = new Date();
//		return Jwts.builder().setClaims(claims) // 정보 저장
//				.setIssuedAt(now) // 토큰 발행 시간
//				.setExpiration(new Date(now.getTime() + time)) // 만료 시간
//				.signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화
//				.compact();
//	}
//	
//	// JWT 토큰에서 인증 정보 조회
//	public Authentication checkPermission(String token) {
//		UserDetails userDetails = userDetailsService.loadUserByUsername(this.checkUserInfo(token));
//		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//	}
//	
//	// 토큰의 유효성 + 만료일자 확인
//	public boolean checkToken(String jwtToken) {
//		try {
//			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//			System.out.println(claims);
//			return !claims.getBody().getExpiration().before(new Date());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	// 토큰에서 회원 정보 추출
//	private String checkUserInfo(String token) {
//		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//	}
//}
