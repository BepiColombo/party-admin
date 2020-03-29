package com.tck.party.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Article implements Serializable {
    private static final long serialVersionUID = -6754206925472145195L;

    private Integer articleId;

    private String articleTitle;

    private String articleAuthor;

    private String articleThumbnail;

    private String articleContent;

    private String articleOutline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT")
    private Date createTime;
}
