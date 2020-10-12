package com.zellur.brainstation23.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOCATION", nullable = false, length = 200)
    private String location;
}
