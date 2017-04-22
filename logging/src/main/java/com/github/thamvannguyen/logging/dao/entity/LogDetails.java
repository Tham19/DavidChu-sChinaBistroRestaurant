package com.github.thamvannguyen.logging.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by huynhduychuong on 10/9/2016.
 */
@Entity
@Table(name = "log_details")
public class LogDetails implements Serializable {

    private static final long serialVersionUID = 4699726771995086787L;
    @Id
    private String id = UUID.randomUUID().toString();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    private Date creationTime = Calendar.getInstance().getTime();
    @Column(name = "log_record_id")
    private String logRecordId;
    private String content;
    @Enumerated(EnumType.STRING)
    private LogLevel level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getLogRecordId() {
        return logRecordId;
    }

    public void setLogRecordId(String logRecordId) {
        this.logRecordId = logRecordId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }
}
