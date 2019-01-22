package org.learning.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class AppointmentCreationCommand {

    @NotNull
    public final Long doctorId;
    @NotNull
    public final LocalDateTime dateTime;
    @NotBlank
    public final String patientName;
    @NotNull
    public final LocalDate patientBirth;

    public AppointmentCreationCommand(Long doctorId, LocalDateTime dateTime, String patientName, LocalDate patientBirth) {
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.patientName = patientName;
        this.patientBirth = patientBirth;
    }
}
