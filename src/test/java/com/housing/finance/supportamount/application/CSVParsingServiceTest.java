package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.supportamount.SupportAmountRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Mockito.mock;

public class CSVParsingServiceTest {
    private SupportAmountRepository housingFinanceRepository;
    private SupportAmountMapper housingFinanceMapper;
    private CSVParsingService csvParsingService;

    @Before
    public void mockUp() {
        housingFinanceRepository = mock(SupportAmountRepository.class);
        housingFinanceMapper = mock(SupportAmountMapper.class);

        csvParsingService = new CSVParsingService(housingFinanceRepository, housingFinanceMapper);
    }

    @Test
    public void testUpload() {
        MockMultipartFile file = new MockMultipartFile("file",
                "orig", null, "bar".getBytes());

        csvParsingService.upload(SupportAmountVO.class, file);
    }
}