package com.antipattrn.ambassador.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ambassadors")
public class Ambassador {

    enum Status {
        ACTIVE,
        INACTIVE,
        PAUSED
    }

    enum Gender {
        MALE,
        FEMALE,
        OTHER,
        UNSPECIFIED
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "POSTALCODE")
    private String postalCode;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;


    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ambassador_tags",
            joinColumns = { @JoinColumn(name = "ambassador_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();

    public Ambassador() {
        //No Arg Constructor
    }

    public Ambassador(String id, String firstName, String lastName, String postalCode, Gender gender, Status status, String phone, String email) {
        this(firstName, lastName, postalCode, gender, status, phone, email);
        this.id = id;
    }

    public Ambassador(String firstName, String lastName, String postalCode, Gender gender, Status status, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.gender = gender;
        this.status = status;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Gender getGender() {
        return gender;
    }
    public Status getStatus() {
        return status;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }

}
