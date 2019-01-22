package org.learning.application;

import static java.lang.String.format;

import org.learning.domain.model.Appointment;
import org.learning.domain.model.AppointmentNotFoundException;
import org.learning.domain.model.AppointmentRepository;
import org.learning.domain.model.Doctor;
import org.learning.domain.model.DoctorNotFoundException;
import org.learning.domain.model.DoctorRepository;
import org.learning.domain.model.Schedule;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private static final int LUNCH_HOUR = 13;
    private static final long APPOINTMENT_PATIENT_MINUTES = 15;

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void createAppointment(@Validated AppointmentCreationCommand command) {

        Doctor doctor = doctorRepository.findById(command.doctorId)
                                        .orElseThrow(() -> new DoctorNotFoundException(format("Doctor with id %d not found", command.doctorId)));

        if (!isValidAppointmentTime(command.dateTime.toLocalTime(), doctor.getSchedule())) {
            throw new IllegalArgumentException("Appointment time is not valid");
        }

        Optional<Appointment> appointment =
                        appointmentRepository.findAppointmentByDoctorAndDateAndTime(doctor, command.dateTime.toLocalDate(), command.dateTime.toLocalTime());

        if (appointment.isPresent()) {
            throw new IllegalArgumentException("Appointment is already taken");
        }

        appointmentRepository.save(new Appointment(doctor, command.dateTime, command.patientName, command.patientBirth));
    }

    public void removeAppointmentById(Long appointmentId) {

        Appointment appointment = appointmentById(appointmentId);

        appointmentRepository.delete(appointment);
    }

    public Appointment appointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                             .orElseThrow(() -> new AppointmentNotFoundException(format("Appointment with id %d not found", appointmentId)));
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public AppointmentStatusTemplate appointmentByDate(Long doctorId, LocalDateTime dateTime) {

        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new DoctorNotFoundException(format("Doctor with id %d not found", doctorId)));

        LocalTime time = dateTime.toLocalTime();

        if (!isValidAppointmentTime(time, doctor.getSchedule())) {
            throw new IllegalArgumentException("Appointment time is not valid");
        }

        Optional<Appointment> appointment =
                        appointmentRepository.findAppointmentByDoctorAndDateAndTime(doctor, dateTime.toLocalDate(), time);

        return new AppointmentStatusTemplate(dateTime, !appointment.isPresent());
    }

    public List<LocalTime> allAvailableAppointmentsByDate(Long doctorId, LocalDate date) {

        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new DoctorNotFoundException(format("Doctor with id %d not found", doctorId)));

        List<LocalTime> appointments = appointmentRepository.findAllByDoctorAndDate(doctor, date).stream()
                                                            .map(appointment -> appointment.getDateTime().toLocalTime())
                                                            .collect(Collectors.toList());

        List<LocalTime> receptions = generateScheduleReceptionTime(doctor.getSchedule());

        return receptions.stream()
                         .filter(reception -> !appointments.contains(reception))
                         .collect(Collectors.toList());
    }

    private static boolean isValidAppointmentTime(LocalTime localTime, Schedule schedule) {

        return generateScheduleReceptionTime(schedule).contains(localTime);
    }

    private static List<LocalTime> generateScheduleReceptionTime(Schedule schedule) {

        final LocalTime startTime = schedule.getStartTime();
        final LocalTime endTime = schedule.getEndTime();

        List<LocalTime> receptions = new ArrayList<>();

        for (LocalTime time = startTime;  time.isBefore(endTime) ; time = time.plusMinutes(APPOINTMENT_PATIENT_MINUTES)) {
            if (!isLunchTime(time)) {
                receptions.add(time);
            }
        }
        return receptions;
    }

    private static boolean isLunchTime(LocalTime time) {
        return time.getHour() == LUNCH_HOUR;
    }

}
