package com.housing.finance.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.finance.user.dto.ReqSignUpDto;
import com.housing.finance.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testSignUp() throws Exception {
        ReqSignUpDto reqSignUpDto = new ReqSignUpDto("test", "test");

        this.mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(reqSignUpDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testNullFieldSignUpDto() throws Exception {
        ReqSignUpDto reqSignUpDto = new ReqSignUpDto();

        this.mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(reqSignUpDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}