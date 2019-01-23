package org.learning.resources.rest;

import org.learning.application.AppointmentDetailsTemplate;
import org.learning.application.AppointmentService;
import org.learning.application.AppointmentStatusTemplate;
import org.learning.domain.model.Appointment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
class AppointmentController {

    private final AppointmentService appointmentService;

    AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "/doctors/{doctorId}/dates/{date}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<LocalTime> allAvailableAppointmentsByDate(@PathVariable("doctorId") Long doctorId,
                                                   @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return appointmentService.allAvailableAppointmentsByDate(doctorId, date);
    }

    @GetMapping(path = "/doctors/{doctorId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    AppointmentStatusTemplate allAvailableAppointmentsByDateTime(@PathVariable("doctorId") Long doctorId,
                                                                 @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {

        return appointmentService.appointmentByDate(doctorId, dateTime);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Appointment> appointments() {

        return appointmentService.findAll();
    }

    @GetMapping(path = "/{appointmentId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Appointment appointmentById(@PathVariable("appointmentId") Long appointmentId) {

        return appointmentService.appointmentById(appointmentId);
    }

    @DeleteMapping(path = "/{appointmentId}")
    public void removeAppointmentById(@PathVariable("appointmentId") Long appointmentId) {

        appointmentService.removeAppointmentById(appointmentId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppointmentDetailsTemplate createAppointment(@RequestBody AppointmentCreationRequest request) {

        return request.dispatchTo(appointmentService);
    }
}
