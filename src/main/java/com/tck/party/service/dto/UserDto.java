package com.tck.party.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tck.party.entity.Org;
import com.tck.party.entity.Role;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 3484519802944044622L;
    private Integer userId;

    private String username;

    @JsonIgnore
    private String password;

    private String avatar;

    private String nickname;

    private Integer sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;


    private Integer isValid;

    private String phone;

    private String address;

    private Org org;

    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT")
    private Date updateTime;
}
