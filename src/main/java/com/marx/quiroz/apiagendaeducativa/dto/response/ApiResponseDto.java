package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDto {
    private String message;
    private int status;

    public ApiResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
