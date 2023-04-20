package com.standard.entity;

import lombok.Data;

@Data
public class RequestParameters {
    private String id;
    private String content;
    private String documentName;
    private String industry;
    private String current;
    private String pagesize;
    private String standardCategory;
}
