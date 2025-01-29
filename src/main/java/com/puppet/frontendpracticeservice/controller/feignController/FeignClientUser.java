package com.puppet.frontendpracticeservice.controller.feignController;

import com.puppet.frontendpracticeservice.domain.request.RequisitesDto;
import com.puppet.frontendpracticeservice.handler.ControllerExceptionHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "client-with-role-user",
        url = "http://localhost:8080/frontend-practice/requisites",
        configuration = ControllerExceptionHandler.class)
public interface FeignClientUser {

    @GetMapping("/{user_id}")
    ResponseEntity<RequisitesDto> getRequisites(@PathVariable UUID user_id);
}