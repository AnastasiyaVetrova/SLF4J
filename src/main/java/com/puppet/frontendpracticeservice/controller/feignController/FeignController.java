package com.puppet.frontendpracticeservice.controller.feignController;

import com.puppet.frontendpracticeservice.domain.request.RequisitesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/frontend-practice/feign")
@RequiredArgsConstructor
public class FeignController {

    private final FeignServiceUser feignServiceUser;

    @GetMapping("/{id}")
    public ResponseEntity<RequisitesDto> getRequisites(@PathVariable UUID id) {
        return feignServiceUser.getReq(id);
    }
}