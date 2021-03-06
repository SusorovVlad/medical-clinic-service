package org.learning.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "APPOINTMENT")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for JPA
public class Appointment {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "APPOINTMENT_ID")
    private Doctor doctor;

    @Getter
    @Column(name = "BOOKING_DATE", nullable = false)
    private LocalDate date;

    @Getter
    @Column(name = "BOOKING_TIME", nullable = false)
    private LocalTime time;

    @Getter
    @Column(name = "PATIENT_NAME", nullable = false)
    private String patientName;

    @Getter
    @Column(name = "PATIENT_BIRTH", nullable = false)
    private LocalDate patientBirth;

    public Appointment(Doctor doctor, LocalDate date, LocalTime time, String patientName, LocalDate patientBirth) {
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.patientBirth = patientBirth;
    }
}
