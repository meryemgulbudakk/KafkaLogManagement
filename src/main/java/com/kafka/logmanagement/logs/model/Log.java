package com.kafka.logmanagement.logs.model;

import jakarta.persistence.*;

/**
 * Entity representing a log entry.
 */
@Entity
@Table(name = "logs", indexes = {
        @Index(name = "idx_timestamp", columnList = "timestamp"),
        @Index(name = "idx_topic_partition_offset", columnList = "topic, partition, offset"),
        @Index(name = "idx_key", columnList = "`key`"),
        @Index(name = "idx_userId", columnList = "userId"),
        @Index(name = "idx_status", columnList = "status")
})

public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`timestamp`", nullable = false)
    private String timestamp;

    @Column(name = "`topic`", nullable = false, length = 255)
    private String topic;

    @Column(name = "`partition`", nullable = false)
    private Integer partition;

    @Column(name = "`offset`", nullable = false)
    private Integer offset;

    @Column(name = "`key`", nullable = false, length = 255)
    private String key;

    @Column(name = "`value`", nullable = false)
    private String value;

    @Column(name = "`status`", nullable = false, length = 50)
    private String status;

    @Column(name = "`userId`", nullable = false)
    private Integer userId;

    @Column(name = "`server`", nullable = false, length = 39)
    private String server;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", topic='" + topic + '\'' +
                ", partition=" + partition +
                ", offset=" + offset +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", server='" + server + '\'' +
                '}';
    }
}
