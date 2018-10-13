package com.antipattrn.ambassador.repository;

import java.util.List;

public class AmbassadorSearchCriteria {
    private List<String> tags;
    private String postalCode;
    private String firstName;
    private String lastName;

    public AmbassadorSearchCriteria(List<String> tags, String postalCode, String firstName, String lastName) {
        this.tags = tags;
        this.postalCode = postalCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
