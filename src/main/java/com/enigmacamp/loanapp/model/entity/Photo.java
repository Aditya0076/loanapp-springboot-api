package com.enigmacamp.loanapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    private String name;

    private String type;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
