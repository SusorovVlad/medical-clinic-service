package org.learning.domain.model;

import static java.time.LocalTime.of;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public enum Schedule {

    FULL_TIME(of(8, 0), of(17, 0)),
    PART_TIME(of(8, 0), of(13, 0));

    private LocalTime startTime;
    private LocalTime endTime;

    Schedule(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
