package com.puppet.frontendpracticeservice.controller.impl;

import com.puppet.frontendpracticeservice.controller.RequisitesController;
import com.puppet.frontendpracticeservice.domain.request.RequisitesDto;
import com.puppet.frontendpracticeservice.domain.request.UserRequestDto;
import com.puppet.frontendpracticeservice.domain.response.UserResponseDto;
import com.puppet.frontendpracticeservice.service.RequisitesService;
import com.puppet.frontendpracticeservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/frontend-practice/requisites")
@RequiredArgsConstructor
public class RequisitesControllerImpl implements RequisitesController {
    private final RequisitesService requisitesService;

    private final UserService userService;

    @GetMapping("/{user_id}")
    @Override
    public ResponseEntity<RequisitesDto> getRequisites(@PathVariable UUID user_id) {
        return ResponseEntity.ok(requisitesService.findRequisites(user_id));
    }
    @GetMapping("/user/{user_id}")
    public ResponseEntity<UserResponseDto> getUser (@PathVariable UUID user_id) {
        return ResponseEntity.ok(userService.findById(user_id));
    }
    @PostMapping("/user")
    public void postUser (@RequestBody UserRequestDto userRequestDto) {
        userService.save(userRequestDto);
    }
}