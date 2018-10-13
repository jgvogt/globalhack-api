package com.antipattrn.ambassador.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ambassador_reviews")
public class AmbassadorReview {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ambassador_id", nullable=false)
    @JsonIgnore
    private Ambassador ambassador;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "REVIEW")
    private String review;

    public AmbassadorReview() {
    }

    public AmbassadorReview(Ambassador ambassador, int rating, String review) {
        this.ambassador = ambassador;
        this.rating = rating;
        this.review = review;
    }

    public Ambassador getAmbassador() {
        return ambassador;
    }


    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
