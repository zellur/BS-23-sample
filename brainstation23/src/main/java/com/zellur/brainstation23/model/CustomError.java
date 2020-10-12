package com.zellur.brainstation23.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    String statusCode;
    String message;
    ZonedDateTime localDateTime;
}
