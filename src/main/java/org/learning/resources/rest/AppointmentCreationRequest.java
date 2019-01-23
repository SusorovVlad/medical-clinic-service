package org.learning.resources.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.learning.application.AppointmentCreationCommand;
import org.learning.application.AppointmentDetailsTemplate;
import org.learning.application.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class AppointmentCreationRequest {

    private final Long doctorId;
    private final LocalDateTime dateTime;
    private final String patientName;
    private final LocalDate patientBirth;

    @JsonCreator
    public AppointmentCreationRequest(@JsonProperty("doctorId") Long doctorId,
                                      @JsonProperty("dateTime") LocalDateTime dateTime,
                                      @JsonProperty("patientName") String patientName,
                                      @JsonProperty("patientBirth") LocalDate patientBirth) {
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.patientName = patientName;
        this.patientBirth = patientBirth;
    }

    public AppointmentDetailsTemplate dispatchTo(AppointmentService appointmentService) {
        return appointmentService.createAppointment(new AppointmentCreationCommand(doctorId, dateTime, patientName, patientBirth));
    }
}
