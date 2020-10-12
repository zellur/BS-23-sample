package com.zellur.brainstation23.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_ID", nullable = false, length = 20)
    private Long userId;
    @Column(name = "LOCATION_ID", nullable = false, length = 20)
    private Long locationId;
    @Column(name = "MESSAGE", nullable = false)
    private String message;
    @Column(name = "IS_PUBLIC", nullable = false)
    private Boolean isPublic;
}
