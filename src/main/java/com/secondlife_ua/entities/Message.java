package com.secondlife_ua.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Entity
@Table(name = "message")
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String text;

    @Column(nullable = false, length = 150)
    @Getter
    @Setter
    private String clientName;

    @Column(nullable = false, length = 10)
    @Getter
    @Setter
    private String clientPhoneNumber;

    @Column(length = 80)
    @Getter
    @Setter
    private String telegramNickname;

    @Column(nullable = false)
    @Getter
    private Calendar creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    @Getter
    @Setter
    private Organization organization;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @Getter
    @Setter
    private Item item;

    public Message(String text, String clientName, String clientPhoneNumber, String telegramNickname, Organization organization, Item item) {
        this.text = text;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.telegramNickname = telegramNickname;
        this.organization = organization;
        this.item = item;
        creationDate = Calendar.getInstance();
    }
}
