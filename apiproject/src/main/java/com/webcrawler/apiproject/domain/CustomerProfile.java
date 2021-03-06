package com.webcrawler.apiproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * CustomerProfile class to save the customer provided information to the CUSTOMERPROFILE db
 */
@Entity
@Data
@Table(name = "CUSTOMERPROFILE")
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String customerEmail;
    private String flightOrigin;
    private String flightDestination;
    private LocalDate travelDate;
    private String frequency;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomerProfile{");

        if (firstName != null) {
            sb.append("flightName='" + firstName + "', ");
        }
        if (lastName != null) {
            sb.append("flightNumber=" + lastName + ", ");
        }
        if (phoneNumber != null) {
            sb.append("phoneNumber=" + phoneNumber + ", ");
        }
        if (customerEmail != null) {
            sb.append("customerEmail='" + customerEmail + "', ");
        }
        if (flightOrigin != null) {
            sb.append("flightOriginCode=" + flightOrigin + ", ");
        }
        if (flightDestination != null) {
            sb.append("flightDestinationCode=" + flightDestination  + ", ");
        }
        if (travelDate != null) {
            sb.append("travelDate=" + travelDate  + ", ");
        }
        if (frequency != null) {
            sb.append("frequency=" + frequency );
        }
        sb.append("}");

        return sb.toString();
    }
}
