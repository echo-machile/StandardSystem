package com.standard.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "standards")
public class Standard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "standard_category", nullable = false)
    private String standardCategory;

    // Getters and setters
}
