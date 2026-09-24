package com.apexmotion.config;

import java.util.List;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Đánh dấu là file này có cấu hình spring
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
public class CorsConfig {
	// "Spring, hãy tạo và quản lý CorsFilter này"
	@Bean
	public CorsFilter corsFilter() {

	// Tạo object khởi tạo
	CorsConfiguration config = new CorsConfiguration();
	//Ai được gọi, chỉ cho phép frontend được gọi
	config.setAllowedOrigins(List.of("http://localhost:5173"));
	//Phương thức nào thì được dùng
	config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
	//Header nào được gửi
	config.setAllowedHeaders(List.of("*"));
	  // ========== 4. CÓ GỬI COOKIE/CREDENTIALS KHÔNG? ==========
	config.setAllowCredentials(true);
	//Áp dụng cho URL
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/api/**", config);
	return new CorsFilter(source);
	}
}
