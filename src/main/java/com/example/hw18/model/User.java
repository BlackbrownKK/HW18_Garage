package com.example.hw18.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int userId;
    private String name;
}
