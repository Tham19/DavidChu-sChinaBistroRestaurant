package com.github.thamvannguyen.core.jpa.auditing;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by huynhduychuong on Oct 2, 2016.
 */
@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditableEntity<PK extends Serializable> implements Serializable {

    @Id
    private PK id;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @LastModifiedDate
    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @CreatedBy
    @Column(name = "created_by")
    private String createBy;
    @LastModifiedBy
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Version
    private Integer versionNo;
    private boolean deleted;

    @Override
    public String toString() {
        return "AbstractAuditableEntity{" +
                "id=" + id +
                ", created=" + created +
                ", lastUpdated=" + lastUpdated +
                ", createBy='" + createBy + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", versionNo=" + versionNo +
                ", deleted=" + deleted +
                '}';
    }

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


}
