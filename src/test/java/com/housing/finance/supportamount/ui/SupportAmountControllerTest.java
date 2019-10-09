package com.housing.finance.supportamount.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.finance.supportamount.application.BankService;
import com.housing.finance.supportamount.application.SupportAmountService;
import com.housing.finance.user.dto.ReqUserDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private BankService bankService;

    @Test
    public void testGetBanks() throws Exception {
        this.mockMvc.perform(get("/banks")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

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
        this.mockMvc.perform(get("/banks/korea-exchange-bank/support-amount/avg")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPredictionOfBank() throws Exception {
        this.mockMvc.perform(get("/support-amount/prediction")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("bank", "국민은행")
                .param("month", "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testNullFieldReqPredictionAmountDto() throws Exception {

        this.mockMvc.perform(get("/support-amount/prediction")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}