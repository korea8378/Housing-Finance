package com.housing.finance.housingfinance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.finance.housingfinance.service.HousingFinanceService;
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
@WebMvcTest(HousingFinanceController.class)
public class HousingFinanceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    HousingFinanceService housingFinanceService;

    @Test
    public void testUploadHousingFinanceCSV() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                "orig", null, "bar".getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/housing-finance")
                .file(file))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetHousingFinanceTotalAmount() throws Exception {
        this.mockMvc.perform(get("/housing-finance/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMaximumOfAllBank() throws Exception {
        this.mockMvc.perform(get("/housing-finance/maximum")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

}