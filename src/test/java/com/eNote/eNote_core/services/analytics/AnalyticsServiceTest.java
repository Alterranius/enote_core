package com.eNote.eNote_core.services.analytics;

import com.eNote.eNote_core.dto.AccountStatsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alterranius
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyticsServiceTest {
    @Autowired
    private AnalyticsService analyticsService;

    @Test
    public void getAccountStats_json_test() {
        int id = 51;
        ObjectMapper jsonMapper = new ObjectMapper();
        String await = """
                {
                    "completed":"2",
                    "failed":"1",
                    "inWork":"3",
                    "speed":"2102.01",
                    "delegationsCompleted":"0",
                    "effectivency":"0.67",
                    "taskAccounts":{
                        "7 - Игровая платформа РОБЛОКС":"6"
                    }
                }""";
        try {
            AccountStatsDTO accountData = jsonMapper.readValue(await, AccountStatsDTO.class);
            Assertions.assertEquals( jsonMapper.writeValueAsString(accountData), jsonMapper.writeValueAsString(analyticsService.getAccountStats(id).get()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}