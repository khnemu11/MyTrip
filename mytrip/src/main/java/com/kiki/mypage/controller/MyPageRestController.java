package com.kiki.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.mypage.model.service.MyPageService;
import com.kiki.user.model.UserDto;

@RestController
@RequestMapping("mypages")
public class MyPageRestController {

	@Autowired
	MyPageService myPageService;
	
	@GetMapping("/withdrawal")
	public ResponseEntity<?> withdrawUser(HttpSession session) {
		try {
			String id = ((UserDto) session.getAttribute("userInfo")).getId();
			int valid = myPageService.withdrawUser(id);
			if (valid == 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 탈퇴 실패");
			} else {
				return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}