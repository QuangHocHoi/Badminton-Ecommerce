package com.apexmotion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank(message = "Email khong duoc de trong")
	@Email(message = "Email khong dung format")
	private String email;
	@NotBlank(message = "Mat khau khong duoc de trong")
	private String password;
}
