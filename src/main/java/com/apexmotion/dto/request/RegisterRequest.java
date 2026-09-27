package com.apexmotion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank(message = "Ho ten khong duoc de rong")
	@Size(max=100, message = "Ho ten toi da 100 ki tu")
	private String fullName;
	@NotBlank(message = "Email khong duoc de rong")
	@Email(message = "Email khong hop le")
	private String email;
	@NotBlank(message = "Mat khau khong duoc de rong")
	@Size(min = 8, message = "Mat khau it nhat 8 ki tu")
	private String password;
	@Size(max=20, message = "So dien thoai toi da 20 ki tu")
	private String phone;
}
