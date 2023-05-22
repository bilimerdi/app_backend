package com.example.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Long id;
    private Date createdDate;
    private String fullName;
    private String TC;
    private String diagnosis;
    private String detail;
    private String assistant;
//    private byte[] Image ;
}
