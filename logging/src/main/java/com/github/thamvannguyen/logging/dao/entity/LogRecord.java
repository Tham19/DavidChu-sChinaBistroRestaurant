package com.github.thamvannguyen.logging.dao.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "log_record")
@EntityListeners(AuditingEntityListener.class)
public class LogRecord implements Serializable {
    private static final long serialVersionUID = 2806433176985651356L;
    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "clustered_node_id")
    private String clusteredNodeId;
    @Column(name = "message_code")
    private String messageCode;
    @Column(name = "message_info")
    private String messageInfo;
    private String channel;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "service_url")
    private String serviceUrl;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    private Date creationTime = Calendar.getInstance().getTime();
    @Enumerated(EnumType.STRING)
    private LogLevel level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getClusteredNodeId() {
        return clusteredNodeId;
    }

    public void setClusteredNodeId(String clusteredNodeId) {
        this.clusteredNodeId = clusteredNodeId;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }
}
