package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    private String id;
    private String name;
    private String password;
    private String phone;
}
