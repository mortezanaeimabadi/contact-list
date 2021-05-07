package com.kn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalErrorMessage {
    private int code;
    private String message;
    private Date timestamp;
}
