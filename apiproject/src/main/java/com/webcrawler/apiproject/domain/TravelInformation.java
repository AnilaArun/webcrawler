package com.webcrawler.apiproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "TRAVELINFORMATION")
public class TravelInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int customerId;
    private String flightOrigin;
    private String flightDestination;
    private int flightInformationId;
    private float price;
    private boolean sendEmail;
    private Date travelDate;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDate emailSendDate;

    /*@Override
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
    }*/
}
