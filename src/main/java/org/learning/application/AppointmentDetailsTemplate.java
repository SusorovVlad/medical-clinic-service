package org.learning.application;

import org.learning.configuration.Location;
import org.learning.domain.model.Doctor;
import org.learning.domain.model.Money;

import java.time.LocalDateTime;

public final class AppointmentDetailsTemplate {

    public final Doctor doctor;
    public final LocalDateTime dateTime;
    public final Money consultationPrice;
    public final Location clinicLocation;

    public AppointmentDetailsTemplate(Doctor doctor, LocalDateTime dateTime, Money consultationPrice, Location clinicLocation) {
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.consultationPrice = consultationPrice;
        this.clinicLocation = clinicLocation;
    }
}
