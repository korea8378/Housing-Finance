package com.housing.finance.housingfinance.application;

import com.housing.finance.housingfinance.domain.HousingFinanceRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Mockito.mock;

public class CSVParsingServiceTest {
    private HousingFinanceRepository housingFinanceRepository;
    private HousingFinanceMapper housingFinanceMapper;
    private CSVParsingService csvParsingService;

    @Before
    public void mockUp() {
        housingFinanceRepository = mock(HousingFinanceRepository.class);
        housingFinanceMapper = mock(HousingFinanceMapper.class);

        csvParsingService = new CSVParsingService(housingFinanceRepository, housingFinanceMapper);
    }

    @Test
    public void testUpload() {
        MockMultipartFile file = new MockMultipartFile("file",
                "orig", null, "bar".getBytes());

        csvParsingService.upload(HousingFinanceVO.class, file);
    }
}