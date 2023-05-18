package com.example.app.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String TC;
    private String diagnosis;
    private String detail;
//    private byte[] Image ;
}
