package com.kiki.mypage.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.mypage.model.service.MyPageService;
import com.kiki.user.model.UserDto;

@RestController
@RequestMapping("mypages")
public class MyPageRestController {

	@Autowired
	MyPageService myPageService;

	@PostMapping("/withdrawal")
	@ResponseBody
	public ResponseEntity<?> withdrawUser(@RequestBody Map<String, String> data, HttpSession session) {
		try {
			int pwValid = myPageService.authenticateUser(data.get("password"));
			if (pwValid == 0) {
				System.out.println("비번 틀렸대요");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("비밀번호를 다시 확인해주세요");
			} else {
				System.out.println("탈퇴실패");
				String id = ((UserDto) session.getAttribute("userInfo")).getId();
				int valid = myPageService.withdrawUser(id);
				if (valid == 0) {
					return ResponseEntity.status(HttpStatus.ACCEPTED).body("회원 탈퇴 실패");
				} else {
					System.out.println("탈퇴성공");
					session.invalidate();
					return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}