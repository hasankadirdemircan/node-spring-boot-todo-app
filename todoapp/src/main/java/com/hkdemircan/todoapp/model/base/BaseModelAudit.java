package com.hkdemircan.todoapp.model.base;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseModelAudit extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8317636893017678965L;

	@Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @PrePersist
    public void prePersist() {
        this.createBy = SecurityContextHolder.getContext().getAuthentication().getName();
        this.createDate = new Date();
    }

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
