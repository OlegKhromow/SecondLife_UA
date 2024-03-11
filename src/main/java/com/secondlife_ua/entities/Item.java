package com.secondlife_ua.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "item")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Column(length = 120)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    @Getter
    private List<Photo> photos;

    @Column(name = "addition_date")
    @Getter
    @Setter
    private Calendar additionDate;

    @Column(name = "is_active")
    @Getter
    private boolean isActive;

    @Column(name = "given_date")
    @Getter
    private Calendar givenDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_item_organization", nullable = false)
    @Getter
    @Setter
    private Organization organization;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_item_category", referencedColumnName = "id")
    @Getter
    @Setter
    private Category category;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Volunteer volunteer;

    public Item(String name, String description, List<Photo> photos, Organization organization, Category category, Volunteer volunteer) {
        this.name = name;
        this.description = description;
        this.photos = photos;
        this.organization = organization;
        this.category = category;
        this.volunteer = volunteer;
        additionDate = Calendar.getInstance();
        setActive(true);
    }

    public void setActive(boolean active) {
        isActive = active;
        if (active)
            givenDate = null;
        else
            givenDate = Calendar.getInstance();
    }
}