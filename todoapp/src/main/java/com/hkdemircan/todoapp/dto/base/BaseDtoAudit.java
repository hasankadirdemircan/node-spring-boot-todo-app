package com.hkdemircan.todoapp.dto.base;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public abstract class BaseDtoAudit extends BaseDto{

    private String createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
