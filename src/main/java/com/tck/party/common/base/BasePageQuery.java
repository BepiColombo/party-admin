package com.tck.party.common.base;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BasePageQuery implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    @NotNull(message = "每页记录数不能为空")
    private Integer pageSize;
    @NotNull(message = "页码不能为空")
    private Integer pageNum;
}
