package com.project.wisdompet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "services")
@ToString
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
}
