package org.learning.domain.model;

public class DoctorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7667504113824659012L;

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
