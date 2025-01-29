package com.puppet.frontendpracticeservice.service.impl;

import com.puppet.frontendpracticeservice.domain.request.RequisitesDto;
import com.puppet.frontendpracticeservice.exception.UserNotFoundException;
import com.puppet.frontendpracticeservice.repository.RequisitesRepository;
import com.puppet.frontendpracticeservice.service.RequisitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {
    private final RequisitesRepository requisitesRepository;

    @Override
    public RequisitesDto findRequisites(UUID user_id) {
        return requisitesRepository.findRequisitesDtoById(user_id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }
}