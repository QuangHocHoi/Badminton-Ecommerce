package com.apexmotion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.apexmotion.dto.respone.UserRespone;
import com.apexmotion.entity.User;

@Mapper
public interface UserMapper {
    // Chuyển User entity → UserResponse DTO
    // MapStruct tự match: user.id → response.id, user.email → response.email...
	@Mapping(target = "role", expression = "java(user.getRole().name())")
	 // role trong User là Enum (Role.CUSTOMER)
    // role trong UserResponse là String ("CUSTOMER")
    // → Cần chỉ dẫn: gọi .name() để chuyển Enum → String
	UserRespone toRespone(User user);
}
