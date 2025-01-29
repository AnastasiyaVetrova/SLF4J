package com.puppet.frontendpracticeservice.controller.feignController;

import com.puppet.frontendpracticeservice.domain.request.RequisitesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeignServiceUser {
    private final FeignClientUser feignClientUser;

    public ResponseEntity<RequisitesDto> getReq(UUID user_id) {
        return feignClientUser.getRequisites(user_id);
    }
}