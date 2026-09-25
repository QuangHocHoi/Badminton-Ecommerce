package com.apexmotion.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity // "Class này mapping với 1 bảng DB"
@Table(name = "users")   // Tên bảng = "users" (vì "user" là từ khóa
@Data // Lombok: tự tạo getter, setter, toString, 
@Builder // Lombok: cho phép tạo object bằng User.builder().email("...").build()
@NoArgsConstructor // Lombok: tạo constructor rỗng User()
@AllArgsConstructor // Lombok: tạo constructor đầy đủ User()
public class User {
	@Id //Đánh dấu đấy là khóa chính
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	@Column(name="password_hash", nullable = false)
	private String passwordHash;
	@Column(name = "full_name",nullable = false, length = 100)
	private String fullName;
	@Column(length = 20)
	private String phone;
	@Column(name="avatar_url", length = 500)
	private String avatarUrl;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Role role = Role.CUSTOMER;
	@Column(name="is_active")
	private boolean isActive = true;
	@Column(name="created_at", updatable = false)
	private LocalDateTime createAt;
	@Column (name="update_at")
	private LocalDateTime updateAt;
	
	@PrePersist //chạy trước khi insert
	protected void onCreate() {
		createAt = LocalDateTime.now();
		updateAt = LocalDateTime.now();
		if (role == null) role = Role.CUSTOMER;
	}
}
