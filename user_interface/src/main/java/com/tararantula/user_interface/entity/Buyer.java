package com.tararantula.user_interface.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "buyerId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Buy> receiptSet;

    public Buyer(String name) {
        this.name = name;
    }
}
