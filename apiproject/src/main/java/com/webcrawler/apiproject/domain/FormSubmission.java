package com.webcrawler.apiproject.domain;

import com.webcrawler.apiproject.annotation.ValidForm;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({FormSubmission.class})
@ValidForm(groups = Default.class)
@Data
public class FormSubmission {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String customerEmail;
    private String flightOrigin;
    private String flightDestination;
    private String frequency;
    @DateTimeFormat(pattern = "MMMM-yy-dd")
    private String travelDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FormSubmission{");

        if (firstName != null) {
            sb.append("firstName='" + firstName + "', ");
        }
        if (lastName != null) {
            sb.append("lastName=" + lastName + ", ");
        }
        if (phoneNumber != null) {
            sb.append("phoneNumber=" + phoneNumber  + ", ");
        }
        if (customerEmail != null) {
            sb.append("customerEmail='" + customerEmail + "', ");
        }
        if (flightOrigin != null) {
            sb.append("flightOrigin=" + flightOrigin + ", ");
        }
        if (flightDestination != null) {
            sb.append("flightDestination=" + flightDestination + ", ");
        }
        if (travelDate != null) {
            sb.append("travelDate=" + travelDate + ", ");
        }
        if (frequency != null) {
            sb.append("frequency=" + frequency );
        }
        sb.append("}");

        return sb.toString();
    }
}
