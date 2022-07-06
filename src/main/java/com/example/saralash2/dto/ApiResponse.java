package com.example.saralash2.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {
    private String message;

    @Builder.Default
    private boolean success = false;

    private T object;
}
