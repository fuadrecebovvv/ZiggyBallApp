package com.example.ziggyballapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedUserDto {
    private String email;
    private String username;
    private String password;
    private Long bestScore;
}
