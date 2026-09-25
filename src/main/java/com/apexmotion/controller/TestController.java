package com.apexmotion.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//"Class này là REST Controller — nhận request, trả JSON"
@RestController
//Tất cả URL trong class này đều bắt đầu là /api
@RequestMapping("/api")
public class TestController {
	//Khi client yêu cầu get trả về /api/test
	@GetMapping("/test")
	public ResponseEntity<Map<String,String>> test() {
		return ResponseEntity.ok(Map.of("status","ok",
				"message","ApexMotion is running!"));
	}
}
