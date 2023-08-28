package com.project.wisdompet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vendors")
@Data
@ToString
public class VendorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vendorId;
    @Column(name = "NAME")
    @NotNull
    private String name;
    @Column(name = "CONTACT")
    private String contact;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADDRESS")
    private String address;
}
