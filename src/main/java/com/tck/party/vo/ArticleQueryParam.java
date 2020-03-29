package com.tck.party.vo;

import com.tck.party.common.base.BasePageQuery;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleQueryParam extends BasePageQuery implements Serializable {

    private static final long serialVersionUID = -1513282272689967679L;

    private String keyword;

    private Date startTime;

    private Date endTime;
}
