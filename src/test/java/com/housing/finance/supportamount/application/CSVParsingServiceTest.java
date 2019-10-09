package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.supportamount.SupportAmountRepository;
import com.housing.finance.supportamount.util.CSVParser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Mockito.mock;

public class CSVParsingServiceTest {
    private SupportAmountRepository supportAmountRepository;
    private SupportAmountMapper supportAmountMapper;
    private CSVParsingService csvParsingService;
    private CSVParser csvParser;

    @Before
    public void mockUp() {
        supportAmountRepository = mock(SupportAmountRepository.class);
        supportAmountMapper = mock(SupportAmountMapper.class);
        csvParser = mock(CSVParser.class);

        csvParsingService = new CSVParsingService(supportAmountRepository, supportAmountMapper, csvParser);
    }

    @Test
    public void testUpload() {
        MockMultipartFile file = new MockMultipartFile("file",
                "orig", null, "bar".getBytes());

        csvParsingService.upload(SupportAmountVO.class, file);
    }
}