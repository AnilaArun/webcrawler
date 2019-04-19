package com.webcrawler.apiproject.domain;

import com.webcrawler.apiproject.annotation.ValidForm;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({FormSubmission.class})
@ValidForm(groups = Default.class)
@Data
public class FormSubmission {
    private String customerEmail;
    private String origin;
    private String destination;
    private String frequency;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FormSubmission{");

        if (customerEmail != null) {
            sb.append("customerEmail='" + customerEmail + "', ");
        }
        if (origin != null) {
            sb.append("origin=" + origin + ", ");
        }
        if (destination != null) {
            sb.append("destination=" + destination  + ", ");
        }
        if (frequency != null) {
            sb.append("frequency=" + frequency );
        }
        sb.append("}");

        return sb.toString();
    }
}
