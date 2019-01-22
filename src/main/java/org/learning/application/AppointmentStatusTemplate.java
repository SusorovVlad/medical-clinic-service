package org.learning.application;

import java.time.LocalDateTime;

public final class AppointmentStatusTemplate {

    public final LocalDateTime dateTime;
    public final boolean available;

    public AppointmentStatusTemplate(LocalDateTime dateTime, boolean available) {
        this.dateTime = dateTime;
        this.available = available;
    }
}
