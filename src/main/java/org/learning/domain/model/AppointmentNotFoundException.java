package org.learning.domain.model;

public class AppointmentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5790681761040268641L;

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
