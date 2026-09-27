package com.apexmotion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateProfileRequest {
	@Size (max = 100, message = "Ho ten toi da 100 ki tu")
	private String fullName;
	@Size (max=20, message = "So dien thoai toi da 20 ki tu")
	private String phone;
	@Size(max=500)
	private String avatar_url;
}
