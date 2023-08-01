package com.example.newMock3.Model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private String balance;
    private BigDecimal maxLimit;
}
