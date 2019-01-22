package org.learning.domain.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findAllByDoctorAndDate(Doctor doctor, LocalDate date);

    List<Appointment> findAll();

    Optional<Appointment> findAppointmentByDoctorAndDateAndTime(Doctor doctor, LocalDate date, LocalTime time);
}
