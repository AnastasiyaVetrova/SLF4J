package com.puppet.frontendpracticeservice.controller.feignController;

import com.puppet.frontendpracticeservice.domain.request.RequisitesDto;
import com.puppet.frontendpracticeservice.service.JwtService;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeignController.class)
class FeignControllerTest {
    private static final String urlFeign = "http://localhost:8080/frontend-practice/feign/7220e843-6558-4750-9c3e-b4444324ca3e";
    private static final String jsonResponse = """
                {
                "name": "Masha",
                "surname": "Masheva",
                "currentAccount": "77777777777777777777",
                "kbk": "77777777777777777777"
                }
            """;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeignServiceUser feignServiceUser;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser
    void getRequisites() throws Exception {
        RequisitesDto requisitesDto = new RequisitesDto("Masha", "Masheva",
                "77777777777777777777","77777777777777777777");

        when(feignServiceUser.getReq(any())).thenReturn(ResponseEntity.ok(requisitesDto));

        mockMvc.perform(get(urlFeign))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(jsonResponse))
                .andDo(print());
    }
}