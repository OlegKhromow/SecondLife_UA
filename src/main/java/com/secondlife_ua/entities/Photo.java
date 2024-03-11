package com.secondlife_ua.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "photo")
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String path;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    @Getter
    @Setter
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @Getter
    @Setter
    private Item item;

    public Photo(String path, Organization organization) {
        this.path = path;
        this.organization = organization;
    }

    public Photo(String path, Item item) {
        this.path = path;
        this.item = item;
    }
}