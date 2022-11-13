package com.cluboat.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class user
{
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
