package com.antipattrn.ambassador.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String type;
    private String name;

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
