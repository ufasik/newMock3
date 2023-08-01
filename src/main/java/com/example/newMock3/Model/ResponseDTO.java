package com.example.newMock3.Model;

import lombok.*;
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
    private String maxLimit;
}
