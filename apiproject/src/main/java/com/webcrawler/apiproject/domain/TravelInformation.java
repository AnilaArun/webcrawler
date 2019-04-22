package com.webcrawler.apiproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This is to store the customer TravelInfomation data. This can be used to refer if email has already sent to customer
 *
 */
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
    private LocalDate travelDate;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDate emailSendDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TravelInformation{");

        if ((Integer) customerId != null) {
            sb.append("customerId='" + customerId + "', ");
        }
        if ((Integer) flightInformationId != null) {
            sb.append("flightInformationId=" + flightInformationId + ", ");
        }
        if ((Float) price != null) {
            sb.append("price=" + price  + ", ");
        }
        if (travelDate != null) {
            sb.append("travelDate='" + travelDate + "', ");
        }
        if (flightOrigin != null) {
            sb.append("flightOrigin=" + flightOrigin + ", ");
        }
        if (flightDestination != null) {
            sb.append("flightDestination=" + flightDestination + ", ");
        }
        if (emailSendDate != null) {
            sb.append("emailSendDate=" + emailSendDate + ", ");
        }
        if ((Boolean)  sendEmail!= null) {
            sb.append("sendEmail=" + sendEmail );
        }
        sb.append("}");

        return sb.toString();
    }
}
