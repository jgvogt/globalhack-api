package com.antipattrn.ambassador.representation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Review implements Serializable {
    private int rating;
    private String review;

    @JsonCreator
    public Review (@JsonProperty("rating") int rating, @JsonProperty("review") String review) {
        this.rating = rating;
        this.review = review;
    }

    public int getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }
}
