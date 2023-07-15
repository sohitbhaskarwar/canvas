package com.example.test.canvas.entity;

import lombok.Data;

@Data
public class Enrollment {
    private String type;
    private String role;
    private int roleId;
    private int userId;
    private String enrollmentState;

}
