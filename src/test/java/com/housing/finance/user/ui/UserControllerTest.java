package com.housing.finance.user.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.finance.user.dto.ReqUserDto;
import com.housing.finance.user.application.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        ReqUserDto ReqUserDto = new ReqUserDto("test", "test");

        this.mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(ReqUserDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSignIn() throws Exception {
        ReqUserDto ReqUserDto = new ReqUserDto("test", "test");

        this.mockMvc.perform(post("/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(ReqUserDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRefreshToken() throws Exception {

        this.mockMvc.perform(get("/user/token")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer Token")
                .param("userId", "test"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testNoneAuthorizationHeaderRefreshToken() throws Exception {

        this.mockMvc.perform(get("/user/token")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("userId", "test"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNullFieldSignUpDto() throws Exception {
        ReqUserDto reqSignUpDto = new ReqUserDto();

        this.mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(reqSignUpDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}