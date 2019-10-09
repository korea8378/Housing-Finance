package com.housing.finance.supportamount.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.finance.supportamount.application.SupportAmountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SupportAmountController.class)
public class SupportAmountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SupportAmountService supportAmountService;

    @Test
    public void testUploadSupportAmountCSV() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                "orig", null, "bar".getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/support-amount")
                .file(file))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetSupportAmountTotalAmount() throws Exception {
        this.mockMvc.perform(get("/support-amount/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMaxOfAllBank() throws Exception {
        this.mockMvc.perform(get("/support-amount/max")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void testGetAvgOfBank() throws Exception {
        this.mockMvc.perform(get("/support-amount/avg")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }
}