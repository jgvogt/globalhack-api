package com.antipattrn.ambassador.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<Ambassador> ambassadors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<Agency> agencies = new HashSet<>();

    public Tag() {
        //No Arg Constructor
    }

    public Tag(String type, String name) {
        this(null, type, name);
    }

    public Tag(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
