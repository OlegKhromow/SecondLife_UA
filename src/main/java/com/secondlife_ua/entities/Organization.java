package com.secondlife_ua.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "organization")
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Column(nullable = false, length = 150)
    @Getter
    @Setter
    private String name;

    @Column(length = 160)
    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private double latitude;

    @Getter
    @Setter
    private double longitude;

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String logotypePath;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    @Getter
    private List<Photo> photos;

    @Column(nullable = false)
    @Getter
    private Calendar creationDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "organization")
    @Getter
    private List<Volunteer> volunteerList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    @Getter
    private List<Item> itemList;

    public Organization(String name, String address, double latitude, double longitude, String city, String logotypePath) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.logotypePath = logotypePath;
        creationDate = Calendar.getInstance();
    }
}
