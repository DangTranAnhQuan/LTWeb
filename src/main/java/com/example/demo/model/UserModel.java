package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {
	private Integer id;

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;

	@NotBlank(message = "Full name is required")
	@Size(max = 100, message = "Full name must not exceed 100 characters")
	private String fullName;

	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	private Boolean isEdit = false;
}