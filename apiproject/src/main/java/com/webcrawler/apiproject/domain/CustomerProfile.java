package com.webcrawler.apiproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CUSTOMERPROFILE")
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String customerEmail;
    private String flightOrigin;
    private String flightDestination;
    private String frequency;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomerProfile{");

        if (firstName != null) {
            sb.append("firstName='" + firstName + "', ");
        }
        if (lastName != null) {
            sb.append("lastName=" + lastName + ", ");
        }
        if (phoneNumber != null) {
            sb.append("phoneNumber=" + phoneNumber + ", ");
        }
        if (customerEmail != null) {
            sb.append("customerEmail='" + customerEmail + "', ");
        }
        if (flightOrigin != null) {
            sb.append("flightOrigin=" + flightOrigin + ", ");
        }
        if (flightDestination != null) {
            sb.append("flightDestination=" + flightDestination  + ", ");
        }
        if (frequency != null) {
            sb.append("frequency=" + frequency );
        }
        sb.append("}");

        return sb.toString();
    }
}
