package com.webcrawler.apiproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "FLIGHTINFORMATION")
public class FlightInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String flightName;
    private String flightNumber;
    private String phoneNumber;
    private String customerEmail;
    private String flightOriginCode;
    private String flightDestinationCode;
    private String frequency;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomerProfile{");

        if (flightName != null) {
            sb.append("flightName='" + flightName + "', ");
        }
        if (flightNumber != null) {
            sb.append("flightNumber=" + flightNumber + ", ");
        }
        if (flightOriginCode != null) {
            sb.append("flightOriginCode=" + flightOriginCode + ", ");
        }
        if (flightDestinationCode != null) {
            sb.append("flightDestinationCode=" + flightDestinationCode);
        }

        sb.append("}");

        return sb.toString();
    }
}
