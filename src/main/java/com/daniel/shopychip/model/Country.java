package com.daniel.shopychip.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @Id
    @Column(length = 2, nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;
}