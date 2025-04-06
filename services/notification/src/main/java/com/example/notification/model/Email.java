package com.example.notification.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "email")
@DiscriminatorColumn(name = "email_type")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private Long emailId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @OneToOne(mappedBy = "email")
    private Notification notification;
}
