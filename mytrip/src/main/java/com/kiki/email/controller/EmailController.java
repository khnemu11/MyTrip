package com.kiki.email.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Value("${EXPIRED_TIME}")
    private int EXPIRED_TIME;
    
    @Value("${CLOUD_FRONT_PREFIX}")
    private String imgPrefix;
    
    @GetMapping("/send")
    public ResponseEntity<Object> send(@RequestParam(name="email") String email, HttpSession session) throws MessagingException {
        // 이메일 발신될 데이터 적재
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("MyTrip");
        helper.setTo(email);
        helper.setSubject("[MyTrip] 인증 코드 메일");
        
        String code = String.valueOf((int) (Math.random()*1000));
        
        while(code.length()<4) {
        	code = String.valueOf((int) (Math.random()*10))+code;
        }
        
        System.out.println("code : "+code);
        
        StringBuilder sb = new StringBuilder();
        sb.append("<div>");
        sb.append("<img src=\""+imgPrefix+"/logo.png\" height=\"24\">");
        sb.append("<br>");
        sb.append("<br>");
        sb.append("<span style='white-space:nowrap'>안녕하세요.&nbsp;</span>");
        sb.append("<span style='font-weight:bold;white-space:nowrap'>MyTrip</span><span> 입니다.</span>");
        sb.append("<br>");
        sb.append("<br>");
        sb.append("<span>아래  인증코드를 회원가입 창으로 돌아가 입력해주세요</span>");
        sb.append("<br>");
        sb.append("<br>");
        sb.append("<span style='color:#39CCCC;font-size:24px;font-weight:bold'>"+code + "</span><br>"); // 메일에 인증번호 넣기
        sb.append("<br>");
        sb.append("<br>");
        sb.append("<span style='font-weight:bold'>※주의 : </span><span>인증번호는 "
        		+ "</span><span style='font-weight:bold'>30분 이후에 만료&nbsp;</span>"
        		+ "<span>되므로 꼭 30분 이내에 입력해주시길 바랍니다.</span>");
        sb.append("<br>");
        sb.append("<br>");
        sb.append("<p>감사합니다!<p>");

        helper.setText(sb.toString(), true);
        
        // 이메일 발신
        javaMailSender.send(message);

        System.out.println(sb.toString());
        
        LocalDateTime publishedTime = LocalDateTime.now();
        
        session.setAttribute(code,publishedTime);

        // 결과 반환
        return ResponseEntity.ok(true);
    }
    
    @GetMapping("/authenticate")
    public ResponseEntity<Object> authenticate(HttpSession session,@RequestParam(name="code") int code) {
        //인증번호가 올바른지 확인
    	System.out.println("들어온 코드 : "+code);
    	LocalDateTime publishedTime = (LocalDateTime) session.getAttribute(String.valueOf(code));
    	System.out.println("발행 시간 : "+publishedTime);
    	//인증번호가 올바르지 않다면
    	if(publishedTime == null) {
            return ResponseEntity.ok(false);
    	}
    	System.out.println("제한 시간 : "+EXPIRED_TIME);
    	LocalDateTime now = LocalDateTime.now();
    	publishedTime = publishedTime.plusMinutes(EXPIRED_TIME);
    	System.out.println("만료 시간 : "+publishedTime);
    	//인증번호 기한이 만료되었다면
    	if(now.isAfter(publishedTime)) {
    		return ResponseEntity.ok(false);
    	}
    	
    	session.removeAttribute(String.valueOf(code));
        // 결과 반환
        return ResponseEntity.ok(true);
    }
}
 