package com.kiki.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.user.model.service.UserService;

@RestController
@RequestMapping("user")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@GetMapping("/login/idCheck/{id}")
	public ResponseEntity<?> checkId(@PathVariable("id") String id) {
		try {
			int valid = userService.checkId(id);
			if (valid == 0) {
				return new ResponseEntity<Integer>(0, HttpStatus.OK);
			}
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("다시 눌러주세요", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
