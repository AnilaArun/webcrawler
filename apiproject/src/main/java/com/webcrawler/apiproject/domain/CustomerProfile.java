package com.webcrawler.apiproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String customerEmail;
    private String flightOrigin;
    private String flightDestination;
}
