package com.secondlife_ua.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "volunteer")
@NoArgsConstructor
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Column(nullable = false, length = 80)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private String surname;

    @Enumerated(EnumType.ORDINAL)
    @Getter
    @Setter
    private Sex sex;

    @Column(nullable = false, length = 150, unique = true)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false, length = 8)
    @Getter
    @Setter
    private String password;

    @Column(nullable = false, length = 10)
    @Getter
    @Setter
    private String phoneNumber;

    @Column
    @Getter
    @Setter
    private Long chatId;

    @Column(nullable = false)
    @Getter
    @Setter
    private Calendar registrationDate;

    @Enumerated(EnumType.ORDINAL)
    @Getter
    @Setter
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_volunteer_organization")
    @Getter
    @Setter
    private Organization organization;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    @Getter
    private List<Item> itemList;

    public Volunteer(String name, String surname, Sex sex, String email, String password, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = Role.NONE;
        registrationDate = Calendar.getInstance();
//        itemList = new ArrayList<>();
    }

    public enum Sex{
        MALE, FEMALE, OTHER
    }

    public enum Role{
        NONE, MEMBER, ADMIN
    }
}
