package com.standard.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    private int admin_id;

    private String password;

    private String name;
    private String email;

    private String avatar;
}
