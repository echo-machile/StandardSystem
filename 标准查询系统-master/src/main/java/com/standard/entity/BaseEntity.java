package com.standard.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import com.standard.entity.CustomDateTimeSerialize;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @JsonSerialize(using=CustomDateTimeSerialize.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonSerialize(using=CustomDateTimeSerialize.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    private String remark;
}
