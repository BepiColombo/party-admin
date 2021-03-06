package com.tck.party.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@JsonIgnoreProperties(value = { "handler" })
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3484519802944044622L;
    private Integer userId;

    @Size(min = 2, max = 10, message = "用户名长度应为2-10个字符")
    private String username;

    @Size(min = 6, max = 10, message = "密码长度应为6-10个字符")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private  String avatar;

    private String nickname;

    @NotNull(message = "性别不能为空")
    private Integer sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;


    private Integer isValid;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    private String address;

    private Org org;

    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT")
    private Date updateTime;
}
