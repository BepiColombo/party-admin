package com.tck.party.vo;

import com.tck.party.common.base.BasePageQuery;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrgQueryParam extends BasePageQuery implements Serializable {
    private static final long serialVersionUID = 8236211529683770310L;

    private String keyword;
}
