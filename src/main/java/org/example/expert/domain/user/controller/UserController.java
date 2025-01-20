package org.example.expert.domain.user.controller;

import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<UserResponse> getUser(HttpServletRequest request,
		@AuthenticationPrincipal Long userId) {
		return ResponseEntity.ok(userService.getUser(userId));
	}

	@PutMapping("/users")
	public void changePassword(
		@AuthenticationPrincipal Long userId,
		@RequestBody UserChangePasswordRequest userChangePasswordRequest) {
		userService.changePassword(userId, userChangePasswordRequest);
	}
}
