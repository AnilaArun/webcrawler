package com.webcrawler.apiproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * To store the flightinformation data to FLIGHTINFORMATION where a customer data can be compared against to send the notification
 */
@Entity
@Data
@Table(name = "FLIGHTINFORMATION")
public class FlightInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String flightName;
    private String flightNumber;
    private String flightOriginCode;
    private String flightDestinationCode;
    private LocalDateTime flightDateAndTime;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private float price;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FlightInformation{");

        if (flightName != null) {
            sb.append("flightName='" + flightName + "', ");
        }
        if (flightNumber != null) {
            sb.append("flightNumber=" + flightNumber + ", ");
        }
        if ((Float) price != null) {
            sb.append("price=" + price + ", ");
        }
        if (flightDateAndTime != null) {
            sb.append("flightDateAndTime=" + flightDateAndTime + ", ");
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
