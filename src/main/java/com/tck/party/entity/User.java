package com.tck.party.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    @NotNull
    private String userId;
    @NotNull
    private String username;
    @NotNull
    private String password;

    private String name;
    private Integer sex;
    private Date birthday;
    private Integer age;
    private String idcard;
    private String phone;
    private String address;
}
