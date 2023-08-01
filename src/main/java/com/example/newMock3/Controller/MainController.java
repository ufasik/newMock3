package com.example.newMock3.Controller;


import com.example.newMock3.Model.RequestDTO;
import com.example.newMock3.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger((MainController.class));

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            String currency;



            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
                currency = "US";
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
                currency = "EU";
            } else {
                maxLimit = new BigDecimal(10000.00);
                currency = "RUB";
            }

            String RqUID = requestDTO.getRqUID();
            String account = requestDTO.getAccount();

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(RqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            BigDecimal balance = getRandomBalance(maxLimit);
            responseDTO.setBalance(balance.setScale(2, RoundingMode.HALF_UP).toString());
            responseDTO.setMaxLimit(maxLimit);

            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("***** Ответ *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((e.getMessage()));
        }
    }

    private BigDecimal getRandomBalance(BigDecimal maxLimit) {
        Random random = new Random();
        double balance = random.nextDouble() * maxLimit.doubleValue();
        return new BigDecimal(balance).setScale(2, RoundingMode.HALF_UP);
    }
}
