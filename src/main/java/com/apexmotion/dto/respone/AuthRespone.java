package com.apexmotion.dto.respone;

import com.apexmotion.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRespone {
	private String accessToken;
	private String refreshToken;
	private User user;
}
